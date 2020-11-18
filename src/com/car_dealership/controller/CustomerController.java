package com.car_dealership.controller;

import java.util.List;

import com.car_dealership.model.Customer;
import com.car_dealership.repo.CustomerDao;

public class CustomerController {
	private CustomerDao cd;

	public CustomerController() {
		this.cd = new CustomerDao();
	}
	
	public boolean validatePassword(String password) {
		return cd.validatePassword(password);
	}
	
	public Customer findByUsername(String username) {
		return cd.findByUsername(username);
	}
	
	public Customer findById(Integer i){
		return cd.findById(i);
	}
	
	public List<Customer> findAll(){
		return cd.findAll();
	}
	
	public Customer update(Customer t){
		return cd.update(t);
	}
	
	public Customer create(Customer t){
		return cd.create(t);
	}
	
	public int delete(Integer i){
		return cd.delete(i);
	}
	
}
