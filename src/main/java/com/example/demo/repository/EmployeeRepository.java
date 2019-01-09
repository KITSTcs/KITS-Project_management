package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	
	public Employee findByKitsId(String kitsId);
	
	public List<Employee> findAll();

}
