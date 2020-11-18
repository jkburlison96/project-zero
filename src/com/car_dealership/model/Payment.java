package com.car_dealership.model;

public class Payment {
	private int paymentID = 0;
	private int amount = 0;
	private String vin = "";
	private int customerID = 0;
	private String lastPaymentDate;
	private String nextPaymentDate;
	private int numPaymentsLeft = 0;
	
	public Payment(int paymentID, int amount, String vin, int customerID, String lpd, String npd, int npl) {
		this.paymentID = paymentID;
		this.amount = amount;
		this.vin = vin;
		this.customerID = customerID;
		this.lastPaymentDate = lpd;
		this.nextPaymentDate = npd;
		this.numPaymentsLeft = npl;
	}
	
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getNumPaymentsLeft() {
		return numPaymentsLeft;
	}
	public void setNumPaymentsLeft(int numPaymentsLeft) {
		this.numPaymentsLeft = numPaymentsLeft;
	}
	public String getVin() {
		return vin;
	}
	public void setVehicle(String vin) {
		this.vin = vin;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomer(int customerID) {
		this.customerID = customerID;
	}
	public String getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	public String getNextPaymentDate() {
		return nextPaymentDate;
	}
	public void setNextPaymentDate(String nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}
	
	
}
