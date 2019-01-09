package com.example.demo.service;

import java.sql.Date;

import com.example.demo.entity.Holiday;

public interface HolidayService {
	     
   Holiday getHolidayByHolidayDate(Date holidayDate);
	     
}
