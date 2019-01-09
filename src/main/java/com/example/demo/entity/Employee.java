package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="employee")
public class Employee {
	
	protected Employee() {}
	
	public void Employee(Integer empId,String kitsId,String name) {
		this.employeeId = empId;
		this.kitsId = kitsId;
		this.name = name;
	}
	
	@Id
	@Column(name="emp_id")
	private Integer employeeId;
	
	@Column(name="kits_id",nullable=false)
	private String kitsId;
	
	@Column(name="name",nullable=false)
	private String name;
	
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
