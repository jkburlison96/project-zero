package com.car_dealership.controller;

import java.util.List;

import com.car_dealership.model.Vehicle;
import com.car_dealership.repo.VehicleDao;

public class VehicleController {
	private VehicleDao vd;

	public VehicleController() {
		this.vd = new VehicleDao();
	}
	
	public Vehicle findById(String s) {
		return vd.findById(s);
	}
	
	public Vehicle findById(Integer i) {
		return vd.findById(i);
	}
	
	public List<Vehicle> findAllFromCustomer(Integer i) {
		return vd.findAllFromCustomer(i);
	}
	
	public List<Vehicle> findAll(){
		return vd.findAll();
	}
	
	public Vehicle update(Vehicle t) {
		return vd.update(t);
	}
	
	public Vehicle create(Vehicle t) {
		return vd.create(t);
	}
	
	public String delete(String s) {
		return vd.delete(s);
	}
	
	public int delete(Integer i) {
		return vd.delete(i);
	}
}
