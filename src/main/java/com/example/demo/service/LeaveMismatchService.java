package com.example.demo.service;

import java.sql.Date;

import com.example.demo.entity.LeaveMismatch;

public interface LeaveMismatchService {
	     
	     LeaveMismatch getMismatchByWeekBegin(Integer empId,Date weekBegin);
	     
}
