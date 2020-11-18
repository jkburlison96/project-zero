package com.car_dealership.model;

import com.car_dealership.UserType;

public interface User {
	public int getUserID();
	public String getUserName();
	public String getPassword();
	public UserType getUserType();
}
