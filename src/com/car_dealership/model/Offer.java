package com.car_dealership.model;

public class Offer {
	private int offerID = 0;
	private String vin = "";
	private int customerID = 0;
	private int amount = 0;
	private String status = "Pending";
	
	public Offer(int offerID, String vin, int customerID, int amount) {
		this.offerID = offerID;
		this.vin = vin;
		this.customerID = customerID;
		this.amount = amount;
	}
	
	public Offer(int offerID, String vin, int customerID, int amount, String status) {
		this.offerID = offerID;
		this.vin = vin;
		this.customerID = customerID;
		this.amount = amount;
		this.status = status;
	}
	
	public int getOfferID() {
		return offerID;
	}
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
