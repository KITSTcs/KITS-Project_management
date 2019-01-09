package com.example.demo.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Employee;
import com.example.demo.entity.LeaveMismatch;
import com.example.demo.service.EmployeeService;


public class ReadExcelFileHelper {

	private final static String TYPE_XLS = "xls";
	private final static String TYPE_XLSX = "xlsx";
	
	
	
	public void prepareLeaveMismatchReport(EmployeeService empService){
		if(!empService.getAllEmployee().isEmpty()) {
			for(Employee employee:empService.getAllEmployee() ) {
				System.out.println("Employee:"+employee );
			}
		}
	}

	public void readFromUltimatixReport(MultipartFile file) {
		Workbook workbook;
		try {
			workbook = getWorkBook(file);
			if (null != workbook) {
				Sheet sheet = workbook.getSheetAt(0);

				Map<Integer, List<String>> data = new HashMap<>();
				int i = 0;
				for (Row row : sheet) {
					data.put(i, new ArrayList<String>());
					for (Cell cell : row) {
						switch (cell.getCellTypeEnum()) {
						case STRING:
							data.get(new Integer(i)).add(cell.getStringCellValue());
							break;
						case NUMERIC:
							data.get(new Integer(i)).add(cell.getNumericCellValue() + "");
							break;
						default:
							data.get(new Integer(i)).add(" ");
						}
					}
					i++;
				}
			}
		} catch (IOException e) {
			// ultimatx file error
		}
	}

	public void readFromTRSReport(MultipartFile file, Map<Integer, LeaveMismatch> leaveMismatchMap) {
		Workbook workBook;
		try {
			workBook = getWorkBook(file);
			if (null != workBook) {

			}
		} catch (IOException e) {
			// TRS file error
		}
	}

	public void readFromProjectReport(MultipartFile file, Map<Integer, LeaveMismatch> leaveMismatchMap) {
		Workbook workBook;
		try {
			workBook = getWorkBook(file);
			if (null != workBook) {

			}
		} catch (IOException e) {
			// project file error
		}
	}
	
	public void readXL(final MultipartFile file) throws IOException{
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

	private Workbook getWorkBook(final MultipartFile file) throws IOException {
		FileInputStream fis = (FileInputStream) file.getInputStream();
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extension.equals(TYPE_XLS)) {
			return new HSSFWorkbook(fis);
		} else if (extension.equals(TYPE_XLS)) {
			return new XSSFWorkbook(fis);
		}
		return null;
	}

}
