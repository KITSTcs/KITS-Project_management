package com.example.demo.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Holiday;
import com.example.demo.repository.HolidayRepository;

@Service
public class HolidayServiceImpl implements HolidayService{
	
	@Autowired
	private HolidayRepository holidayRepository;

	@Override
	public Holiday getHolidayByHolidayDate(Date holidayDate) {
		return holidayRepository.findByHolidayDate(holidayDate);
	}

}
