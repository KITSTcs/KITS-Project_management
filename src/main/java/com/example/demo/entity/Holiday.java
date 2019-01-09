package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="holiday_list")
@IdClass(HolidayKey.class)
public class Holiday {
	
	protected Holiday() {}

	@Id
	@Column(name="year")
	private Integer year;
	
	@Id
	@Column(name="week_begin",nullable=false)
	private Date weekBegin;
	
	@Column(name="holiday_date",nullable=false)
	private Date holidayDate;
	
	public Holiday(Integer year, Date weekBegin, Date holidayDate) {
		super();
		this.year = year;
		this.weekBegin = weekBegin;
		this.holidayDate = holidayDate;
	}

	@Override
    public String toString() {
        return String.format(
                "Holiday date[year=%d, weekBegin='%s', holidayDate='%s']",
                year, weekBegin, holidayDate);
    }


}
