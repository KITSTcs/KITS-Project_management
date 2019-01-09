package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="mismatch_report")
@IdClass(LeaveMismatchKey.class)
public class LeaveMismatch {
	
	protected LeaveMismatch() {}
	
	public LeaveMismatch(Integer employeeId, String kitsId, String name, Date weekBegin, Integer ipmsCount,
			Integer trsCount, Integer projectCount, Boolean mismatch, String mismatchDesc) {
		super();
		this.employeeId = employeeId;
		this.kitsId = kitsId;
		this.name = name;
		this.weekBegin = weekBegin;
		this.ipmsCount = ipmsCount;
		this.trsCount = trsCount;
		this.projectCount = projectCount;
		this.mismatch = mismatch;
		this.mismatchDesc = mismatchDesc;
	}



	@Id
	@Column(name="emp_id")
	private Integer employeeId;
	
	@Id
	@Column(name="week_begin",nullable=false)
	private Date weekBegin;
	
	@Column(name="kits_id",nullable=false)
	private String kitsId;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="imps_count")
	private Integer ipmsCount;
	
	@Column(name="trs_count")
	private Integer trsCount;
	
	@Column(name="project_count")
	private Integer projectCount;
	
	@Column(name="mismatch",nullable=false)
	private Boolean mismatch;
	
	@Column(name="mismatch_desc",nullable=false)
	private String mismatchDesc;
	
	@Override
    public String toString() {
        return String.format(
                "Employee Id[tcsId=%d, kitsId='%s', name='%s']",
                employeeId, kitsId, name);
    }

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getKitsId() {
		return kitsId;
	}

	public void setKitsId(String kitsId) {
		this.kitsId = kitsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
