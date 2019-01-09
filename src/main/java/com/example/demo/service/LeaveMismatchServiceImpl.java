package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LeaveMismatch;
import com.example.demo.repository.LeaveMismatchRepository;

@Service
public class LeaveMismatchServiceImpl implements LeaveMismatchService{
	
	@Autowired
	private LeaveMismatchRepository leaveMismatchRepository;

	@Override
	public LeaveMismatch getMismatchByWeekBegin(Integer empId, Date weekBegin) {
		List<LeaveMismatch> list=leaveMismatchRepository.getLeaveMismatchByWeekBegin(empId, weekBegin);
		return null!=list?list.get(0):null;
	}

}
