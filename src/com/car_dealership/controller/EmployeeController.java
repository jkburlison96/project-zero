package com.car_dealership.controller;

import java.util.List;

import com.car_dealership.model.Employee;
import com.car_dealership.repo.EmployeeDao;

public class EmployeeController {
	private EmployeeDao ed;

	public EmployeeController() {
		this.ed = new EmployeeDao();
	}
	
	public boolean validatePassword(String password) {
		return ed.validatePassword(password);
	}
	
	public Employee findByUsername(String username) {
		return ed.findByUsername(username);
	}

	public Employee findById(Integer i) {
		return ed.findById(i);
	}

	public List<Employee> findAll() {
		return ed.findAll();
	}

	public Employee update(Employee t) {
		return ed.update(t);
	}

	public Employee create(Employee t) {
		return ed.create(t);
	}
	
	public int delete(Integer i) {
		return ed.delete(i);
	}
}
