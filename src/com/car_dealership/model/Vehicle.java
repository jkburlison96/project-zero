package com.car_dealership.model;

import com.car_dealership.PaintColor;
import com.car_dealership.VehicleType;

public class Vehicle {
	
	private PaintColor paintColor = PaintColor.WHITE;
	private VehicleType vehicleType = VehicleType.SEDAN;
	private String vin = "00000";
	private String make;
	private String model;
	private int year = 2020;
	private int mpg = 30;
	private int topSpeed = 90;
	private int horsepower = 150;
	private boolean isSold = false;

	public Vehicle(String vin, String make, String model, int year, VehicleType vehicleType){
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.year = year;
		this.vehicleType = vehicleType;
	}
	
	public Vehicle(String vin, String make, String model, int year, VehicleType vehicleType, PaintColor paintColor){
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.year = year;
		this.vehicleType = vehicleType;
		this.paintColor = paintColor;
	}
	
	public Vehicle(String vin, String make, String model, int year, VehicleType vehicleType, PaintColor paintColor, int mpg, int topSpeed, int horsepower, boolean isSold){
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.year = year;
		this.vehicleType = vehicleType;
		this.paintColor = paintColor;
		this.mpg = mpg;
		this.topSpeed = topSpeed;
		this.horsepower = horsepower;
		this.isSold = isSold;
	}
	
	public PaintColor getPaintColor() {
		return paintColor;
	}
	
	public void setPaintColor(PaintColor paintColor) {
		this.paintColor = paintColor;
	}

	public VehicleType getTypeOfVehicle() {
		return vehicleType;
	}
	
	public void setTypeOfVehicle(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}

	public int getMPG() {
		return mpg;
	}
	
	public void setMPG(int mpg) {
		this.mpg = mpg;
	}

	public int getTopSpeed() {
		return topSpeed;
	}
	
	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}

	public int getHorsepower() {
		return horsepower;
	}
	
	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}
	
	public boolean getIsSold() {
		return isSold;
	}
	
	public void setIsSold(boolean b) {
		isSold = b;
	}

	public void makeSound() {
		System.out.println("Make some vehicle sounds");
	}

}
