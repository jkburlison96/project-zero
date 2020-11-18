package com.car_dealership.model;

import com.car_dealership.UserType;

public class Employee implements User {
	
	private int employeeID = 0;
	private String username;
	private String password;
	
	public Employee(int employeeID, String username, String password) {
		this.employeeID = employeeID;
		this.username= username;
		this.password = password;
	}

	@Override
	public int getUserID() {
		return employeeID;
	}
	
	@Override
	public String getUserName() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public UserType getUserType() {
		return UserType.EMPLOYEE;
	}

}
