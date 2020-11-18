package com.car_dealership.model;

import com.car_dealership.UserType;

public class Visitor implements User {

	@Override
	public int getUserID() {
		return 0;
	}

	@Override
	public String getUserName() {
		return "Visitor";
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public UserType getUserType() {
		return UserType.VISITOR;
	}

}
