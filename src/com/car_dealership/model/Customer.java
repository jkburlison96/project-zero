package com.car_dealership.model;

import java.util.List;

import com.car_dealership.UserType;

public class Customer implements User {
	
	private int customerID;
	private String username;
	private String password;
	private List<Integer> vins;
	
	public Customer(int customerID, String username, String password) {
		this.customerID = customerID;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "CustomerID " + customerID + " Username " + username;
	}
	
	public void setVehicles(List<Integer> vins){
		this.vins = vins;
	}
	
	public List<Integer> getVehicles(){
		return vins;
	}
	
	@Override
	public int getUserID() {
		return customerID;
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
		return UserType.CUSTOMER;
	}

}
