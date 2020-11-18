package com.car_dealership.ui;

import java.util.List;

import com.car_dealership.UserType;
import com.car_dealership.model.Customer;
import com.car_dealership.model.Offer;
import com.car_dealership.model.Payment;
import com.car_dealership.model.Vehicle;
import com.car_dealership.util.TextColors;

//This class is used to handle all of the print to console methods
//This class will also use controller classes to handle DAO operations
public class CarDealershipUI {
	
	public void printMenu(UserType userType) {
		System.out.println("Input a number that correspond to the action you wish to make: ");
		System.out.println("(1) View all Vehicles on lot");
		if(userType == UserType.VISITOR) {
			//default menu, used to log in or register for an account
			System.out.println("(2) Register for account");
			System.out.println("(3) Log In");
			System.out.println("(4) Exit");
		}
		else if(userType == UserType.EMPLOYEE) {
			//print employee's list of available actions
			System.out.println("(2) Add a vehicle to the lot");
			System.out.println("(3) Edit info for a vehicle");
			System.out.println("(4) Remove a vehicle from the lot");
			System.out.println("(5) View all offers");
			System.out.println("(6) Accept an offer");
			System.out.println("(7) Reject an offer");
			System.out.println("(8) View all payments");
			System.out.println("(9) Log out");
			System.out.println("(10) Exit");
		}
		else {
			//print customer's list of available actions
			System.out.println("(2) View my vehicles");
			System.out.println("(3) View my payments");
			System.out.println("(4) View my offers");
			System.out.println("(5) Make offer");
			System.out.println("(6) Log out");
			System.out.println("(7) Exit");
		}
	}
	
	public void printRegister() {
		
	}
	
	public void printListofOffers(List<Offer> o) {
		if(!o.isEmpty()) {
			for(Offer offer: o) {
				System.out.println(TextColors.ANSI_BLUE + "------------------");
				System.out.println("OfferID: " + offer.getOfferID());
				System.out.println("For vin:  " + offer.getVin() + " By CustomerID: " + offer.getCustomerID());
				System.out.println("Amount offered: $" + offer.getAmount() + " Status: " + offer.getStatus());
				System.out.println("------------------" + TextColors.ANSI_RESET);
			}
		}
	}
	
	public <T> void printList(List<T> t) {
		for(T element: t) {
			element.toString();
		}
	}
	
	public void printMakeOffer() {
		
	}
	
	public void printListOfPayments(List<Payment> p) {
		if(!p.isEmpty()) {
			for(Payment payment: p) {
				System.out.println(TextColors.ANSI_BLUE + "------------------");
				System.out.println("PaymentID: " + payment.getPaymentID());
				System.out.println("Monthly Amount: $" + payment.getAmount());
				System.out.println("For Vin: " + payment.getVin() + " By CustomerID: " + payment.getCustomerID());
				System.out.println("Last payment date: " + payment.getLastPaymentDate() + " Next payment date: " + payment.getNextPaymentDate());
				System.out.println("Number of payments left: " + payment.getNumPaymentsLeft());
				System.out.println("------------------" + TextColors.ANSI_RESET);
			}
		}
		
	}
	
	public void printCustomerPayments() {
		
	}
	
	public void printAllPayments() {
		
	}
	
	public void printListOfCustomers(List<Customer> customers) {
		if(!customers.isEmpty()) {
			for(Customer c: customers) {
				System.out.println(TextColors.ANSI_BLUE + "------------------");
				System.out.println("User ID: " + c.getUserID() + " Username: " + c.getUserName() + " Password: " +  c.getPassword());
				System.out.println("------------------" + TextColors.ANSI_RESET);
			}
		}
	}
	
	public void printListOfVehicles(List<Vehicle> vehicles) {
		if(!vehicles.isEmpty()) {
			for(Vehicle v: vehicles) {
				System.out.println(TextColors.ANSI_BLUE + "------------------");
				System.out.println("Vehicle number: " + (vehicles.indexOf(v) + 1));
				System.out.println(v.getYear() + " " + v.getMake() + " " +  v.getModel());
				System.out.println("Paint color: " + v.getPaintColor() + " MPG: " + v.getMPG());
				System.out.println("Top Speed: " + v.getTopSpeed() + " Horsepower: " + v.getHorsepower());
				System.out.println("------------------" + TextColors.ANSI_RESET);
			}
		}
	}
	
	public void updateVehicle() {
		System.out.println("Please enter a number that corresponds to the attribute you wish to edit or enter 9 to quit edit");
		System.out.println("(1) Vehicle's  make");
		System.out.println("(2) Vehicle's model");
		System.out.println("(3) Vehicle's year");
		System.out.println("(4) Vehicle's type");
		System.out.println("(5) Vehicle's paint color");
		System.out.println("(6) Vehicle's miles per gallon (MPG)");
		System.out.println("(7) Vehicle's top speed");
		System.out.println("(8) Vehicle's horsepower");
		System.out.println("(9) Quit edit");
	}
}
