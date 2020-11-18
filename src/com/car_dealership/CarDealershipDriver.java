package com.car_dealership;

public class CarDealershipDriver {
	public static void main(String[] args) {
		//setup environment
		CarDealership cd = new CarDealership();
		//simple encrypt/decrypt test
//		System.out.println(com.car_dealership.util.Util.encrypt("Pass"));
//		System.out.println(com.car_dealership.util.Util.decrypt(com.car_dealership.util.Util.encrypt("Pass")));
		cd.visitorMenu();
	}
}
