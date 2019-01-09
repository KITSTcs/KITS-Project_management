package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Holiday;

public interface HolidayRepository extends CrudRepository<Holiday, Integer>{
	
	public Holiday findByHolidayDate(Date holidayDate);

}
