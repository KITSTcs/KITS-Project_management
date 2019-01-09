package com.example.demo.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.UploadFileResponse;
import com.example.demo.filespace.FileStorageService;
import com.example.demo.helper.ReadExcelFileHelper;
import com.example.demo.service.EmployeeService;



@RestController
public class RPController {

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private EmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(RPController.class);

	@GetMapping(value = "/getData/{user}")
	public String getData(@PathVariable("user") String user) {
		
		ReadExcelFileHelper helper = new ReadExcelFileHelper();
		helper.prepareLeaveMismatchReport(employeeService);
		return employeeService.getEmployeeByKitsId(user).toString();
	}

	@PostMapping(value = "/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			readXL(file);
			return getUploadFileResponse(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private UploadFileResponse getUploadFileResponse(final MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	private void readXL(final MultipartFile file) throws IOException{
		FileInputStream fis = (FileInputStream) file.getInputStream();
		Workbook workbook = new HSSFWorkbook(fis);
		
		Sheet sheet = workbook.getSheetAt(0);
		 
		Map<Integer, List<String>> data = new HashMap<>();
		int i = 0;
		for (Row row : sheet) {
		    data.put(i, new ArrayList<String>());
		    for (Cell cell : row) {
		        switch (cell.getCellTypeEnum()) {
		            case STRING: data.get(new Integer(i)).add(cell.getStringCellValue()); break;
		            case NUMERIC: data.get(new Integer(i)).add(cell.getNumericCellValue()+""); break;		            
		            default: data.get(new Integer(i)).add(" ");
		        }
		    }
		    i++;
		}
		
		System.out.println("Map::"+data);
		
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
