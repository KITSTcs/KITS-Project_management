package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployeeByKitsId(String kitsId) {
		Employee emp= employeeRepository.findByKitsId(kitsId);
		return emp;
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList= employeeRepository.findAll();
		return empList;
	}
		

}
