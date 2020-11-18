package com.car_dealership;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.car_dealership.controller.CustomerController;
import com.car_dealership.controller.EmployeeController;
import com.car_dealership.controller.OfferController;
import com.car_dealership.controller.PaymentController;
import com.car_dealership.controller.VehicleController;
import com.car_dealership.model.Customer;
import com.car_dealership.model.Offer;
import com.car_dealership.model.Payment;
import com.car_dealership.model.User;
import com.car_dealership.model.Vehicle;
import com.car_dealership.model.Visitor;
import com.car_dealership.ui.CarDealershipUI;


public class CarDealership {
	private static CarDealershipUI cdUI;
	private static VehicleController vc;
	private static CustomerController cc;
	private static EmployeeController ec;
	private static OfferController oc;
	private static PaymentController pc;
	private List<Vehicle> vehicles;
	private List<Offer> offers;
	private List<Payment> payments;
	private User user;
	private Customer customer = null;
	private Scanner scan;
	final static Logger logger = Logger.getLogger(CarDealership.class);
	
	public CarDealership() {
		cdUI = new CarDealershipUI();
		vc = new VehicleController();
		cc = new CustomerController();
		ec = new EmployeeController();
		oc = new OfferController();
		pc = new PaymentController();
		setListofVehicles(findAllVehicles());
		user = new Visitor();
		scan = new Scanner(System.in);
		log4jTest("Testing123");
	}
	
	public void log4jTest(String parameter) {
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}

		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}

		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
	}

	public void visitorMenu() {
		printMenu();
		int i = 0;
		boolean isNum = false;
		while(!isNum) {
			try {
				i = scan.nextInt();
				isNum = true;
			} catch(InputMismatchException ime) {
				logger.error("This is error : " + "Invalid input");
				System.out.println("Invalid input, please enter a number!");
				scan.nextLine();
			}
		}
		switch (i) {
		case 1:
			printListOfVehicles(getListOfVehicles());
			visitorMenu();
			break;
		case 2:
			registerAccount();
			visitorMenu();
			break;
		case 3:
			logIn();
			break;
		case 4: // exit program
			System.out.println("Bye!");
			System.exit(1);
		default:
			visitorMenu();
		}
	}

	public void employeeMenu() {
		printMenu();
		int i = 0;
		boolean isNum = false;
		while(!isNum) {
			try {
				i = scan.nextInt();
				isNum = true;
			} catch(InputMismatchException ime) {
				logger.error("This is error : " + "Invalid input");
				System.out.println("Invalid input, please enter a number!");
				scan.nextLine();
			}
		}
		switch (i) {
		case 1:
			printListOfVehicles(getListOfVehicles());
			employeeMenu();
			break;
		case 2: // add vehicle to lot
			addVehicle();
			employeeMenu();
			break;
		case 3: // edit info on a vehicle
			System.out.println("For which vehicle would you like to edit?");
			updateVehicle(findVehicleByID());
			employeeMenu();
			break;
		case 4: // remove vehicle from lot
			System.out.println("Which vehicle would you like to remove?");
			removeVehicle(findVehicleByID());
			employeeMenu();
			break;
		case 5: // view offers
			setListOfOffers(findAllOffers());
			printListofOffers(getListOfOffers());
			employeeMenu();
			break;
		case 6: // accept offer
			System.out.println("Which offer would you like to accept?");
			acceptOffer(findOfferByID());
			employeeMenu();
			break;
		case 7: // reject offer
			System.out.println("Which offer would you like to reject?");
			rejectOffer(findOfferByID());
			employeeMenu();
			break;
		case 8: // view all payments
			setListOfPayments(findAllPayments());
			printListOfPayments(getListOfPayments());
			employeeMenu();
			break;
		case 9: // log out and return to main menu
			System.out.println("\n");
			logOut();
			break;
		case 10: // exit program
			System.out.println("Bye!");
			System.exit(1);
		default:
			employeeMenu();
		}
	}

	public void customerMenu() {
		printMenu();
		int i = 0;
		boolean isNum = false;
		while(!isNum) {
			try {
				i = scan.nextInt();
				isNum = true;
			} catch(InputMismatchException ime) {
				logger.error("This is error : " + "Invalid input");
				System.out.println("Invalid input, please enter a number!");
				scan.nextLine();
			}
		}
		switch (i) {
		case 1: // view all vehicles on lot
			printListOfVehicles(getListOfVehicles());
			customerMenu();
			break;
		case 2: // list user's cars
			printListOfVehicles(findAllVehiclesFromCustomer());
			customerMenu();
			break;
		case 3: // view user's payments
			printListOfPayments(findAllPaymentsFromCustomer());
			customerMenu();
			break;
		case 4: // view user's offers
			printListofOffers(findAllOffersFromCustomer());
			customerMenu();
			break;
		case 5: // make an offer on a vehicle
			makeOffer();
			customerMenu();
			break;
		case 6: // log out and return to main menu
			System.out.println("\n");
			logOut();
			break;
		case 7: // exit program
			System.out.println("Bye!");
			System.exit(1);
			break;
		default:
			customerMenu();
		}
	}

	public <T> void printList(List<T> t) {
		cdUI.printList(t);
	}

	// will print available options based on the type of user
	public void printMenu() {
		cdUI.printMenu(user.getUserType());
	}

	public void logIn() {
		String u = null, p = null;

		System.out.println("Please enter in your Username");
		u = scan.next();
		if ((user = cc.findByUsername(u)) != null) {
			System.out.println("Please enter in your Password");
			p = scan.next();
			while (!cc.validatePassword(p)) {
				System.out.println("Invalid password");
				System.out.println("Please enter in your Password");
				p = scan.next();
			}
			System.out.println("Logged in as customer");
		} else if ((user = ec.findByUsername(u)) != null) {
			System.out.println("Please enter in your Password");
			p = scan.next();
			while (!ec.validatePassword(p)) {
				System.out.println("Invalid password");
				System.out.println("Please enter in your Password");
				p = scan.next();
			}
			System.out.println("Logged in as employee");
		} else {
			System.out.println("Username not found");
			logIn();
		}

		if (user.getUserType() == UserType.CUSTOMER) {
			customerMenu();
		} else {
			employeeMenu();
		}
	}

	public void logOut() {
		// clear user then return to login screen
		user = new Visitor();
		visitorMenu();
	}

	public void registerAccount() {
		// some method to generate ID
		cdUI.printRegister();
		System.out.println("Please enter the username that you would like to use");
		String username = scan.next();
		while(username == null)
		{
			System.out.println("Invalid username");
			System.out.println("Please enter the username that you would like to use");
			username = scan.next();
		}
		System.out.println("Please enter a password for your account");
		String password = scan.next();
		while(password == null) {
			System.out.println("Invalid password");
			System.out.println("Please enter a password for your account");
			password = scan.next();
		}
		//String ePassword = com.car_dealership.util.Util.encrypt(password);
		cc.create(new Customer(0, username, password));
	}

	public void printListofOffers(List<Offer> o) {
		cdUI.printListofOffers(o);
	}

	public List<Offer> findAllOffers() {
		return oc.findAll();
	}

	public void setListOfOffers(List<Offer> o) {
		offers = o;
	}

	public List<Offer> getListOfOffers() {
		return offers;
	}

	public void makeOffer() {
		System.out.println("For which vehicle would you like to make an offer on?");
		int index = Integer.parseInt(scan.next());
		System.out.println("Enter the amount you would like to offer: ");
		int amount = Integer.parseInt(scan.next());
		customer = (Customer) user;
		oc.create(new Offer(9, vehicles.get(index - 1).getVin(), customer.getUserID(), amount));
		setListOfOffers(findAllOffers());
	}

	public void acceptOffer(Offer o) {
		if(!o.getStatus().equals("Accepted")) {
			o.setStatus("Accepted");
			o.setAmount((int) calculateMonthlyPayments(o));
			oc.update(o);
			Vehicle v = vc.findById(o.getVin());
			v.setIsSold(true);
			setListofVehicles(findAllVehicles());
			vc.update(v);
			setListOfOffers(findAllOffers());
			oc.rejectOtherOffers(o);
			createPayment(o);
		}
	}
	

	public void rejectOffer(Offer o) {
		if(!o.getStatus().equals("Accepted")) {
			o.setStatus("Rejected");
			oc.update(o);
			setListOfOffers(findAllOffers());
		}
	}

	public Offer findOfferByID() {
		int i = Integer.parseInt(scan.next());
		return oc.findById(i);
	}

	public List<Offer> findAllOffersFromCustomer() {
		customer = (Customer) user;
		return oc.findAllFromCustomer(customer.getUserID());
	}

	public void createPayment(Offer o) {
		LocalDate ld = LocalDate.now();
		LocalDate ldPlusThirty = ld.plus(30, ChronoUnit.DAYS);
		pc.create(new Payment(0, o.getAmount(), o.getVin(), o.getCustomerID(), ld.toString(), ldPlusThirty.toString(), 36));
	}

	public List<Payment> findAllPaymentsFromCustomer() {
		customer = (Customer) user;
		return pc.findAllFromCustomer(customer.getUserID());
	}

	public List<Payment> findAllPayments() {
		return pc.findAll();
	}

	public void setListOfPayments(List<Payment> p) {
		payments = p;
	}
	

	public List<Payment> getListOfPayments() {
		return payments;
	}

	public void printListOfPayments(List<Payment> p) {
		cdUI.printListOfPayments(p);
	}

	public Payment findPaymentByID() {
		int i = Integer.parseInt(scan.next());
		return pc.findById(payments.get(i).getPaymentID());
	}
	

	public void printListOfVehicles(List<Vehicle> v) {
		cdUI.printListOfVehicles(v);
	}
	

	public List<Vehicle> findAllVehicles() {
		return vc.findAll();
	}

	public void setListofVehicles(List<Vehicle> v) {
		vehicles = v;
	}
	

	public List<Vehicle> getListOfVehicles() {
		return vehicles;
	}

	public List<Vehicle> findAllVehiclesFromCustomer() {
		customer = (Customer) user;
		return vc.findAllFromCustomer(customer.getUserID());
	}
	

	public Vehicle findVehicleByID() {
		int i = Integer.parseInt(scan.next());
		return vc.findById(vehicles.get(i).getVin());
	}
	

	private void addVehicle() {
		System.out.println("Please enter the vehicle's vin");
		String vin = scan.next();
		System.out.println("Please enter the vehicle's  make");
		String make = scan.next();
		System.out.println("Please enter the vehicle's model");
		String model = scan.next();
		System.out.println("Please enter the vehicle's year");
		int year = Integer.parseInt(scan.next());
		System.out.println("Please enter what type the vehicle is");
		String vehicleType = scan.next().toUpperCase();
		System.out.println("Please enter the vehicle's paint color");
		String paintColor = scan.next().toUpperCase();
		System.out.println("Please enter the vehicle's miles per gallon (MPG)");
		int mpg = Integer.parseInt(scan.next());
		System.out.println("Please enter the vehicle's top speed");
		int topSpeed = Integer.parseInt(scan.next());
		System.out.println("Please enter the vehicle's horsepower");
		int horsepower = Integer.parseInt(scan.next());

		vc.create(new Vehicle(vin, make, model, year, VehicleType.valueOf(vehicleType), PaintColor.valueOf(paintColor),
				mpg, topSpeed, horsepower, false));
		setListofVehicles(findAllVehicles());
	}
	

	public void updateVehicle(Vehicle v) {
		cdUI.updateVehicle();
		int i = Integer.parseInt(scan.next());
		switch (i) {
		case 1:
			System.out.println("Please enter the vehicle's  new make");
			String make = scan.next();
			v.setMake(make);
			break;
		case 2:
			System.out.println("Please enter the vehicle's new model");
			String model = scan.next();
			v.setModel(model);
			break;
		case 3:
			System.out.println("Please enter the vehicle's new year");
			int year = Integer.parseInt(scan.next());
			v.setYear(year);
			break;
		case 4:
			System.out.println("Please enter the vehicle's new type");
			String vehicleType = scan.next().toUpperCase();
			v.setTypeOfVehicle(VehicleType.valueOf(vehicleType));
			break;
		case 5:
			System.out.println("Please enter the vehicle's new paint color");
			String paintColor = scan.next().toUpperCase();
			v.setPaintColor(PaintColor.valueOf(paintColor));
			break;
		case 6:
			System.out.println("Please enter the vehicle's new miles per gallon (MPG)");
			int mpg = Integer.parseInt(scan.next());
			v.setMPG(mpg);
			break;
		case 7:
			System.out.println("Please enter the vehicle's new top speed");
			int topSpeed = Integer.parseInt(scan.next());
			v.setTopSpeed(topSpeed);
			break;
		case 8:
			System.out.println("Please enter the vehicle's new horsepower");
			int horsepower = Integer.parseInt(scan.next());
			v.setHorsepower(horsepower);
			break;
		case 9:
			break;
		}

		vc.update(v);
		setListofVehicles(findAllVehicles());
	}
	

	public void removeVehicle(Vehicle v) {
		vc.delete(v.getVin());
		setListofVehicles(findAllVehicles());
	}
	

	// takes vehicle price, payment period, and interest, then breaks it down for
	// the month
	public double calculateMonthlyPayments(Offer o) {
		double amount = 0;
		amount = (o.getAmount() * 1.20);
		amount = ((amount/36) + 1);
		
		return amount;
	}
}
