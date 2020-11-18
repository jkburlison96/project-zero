package com.car_dealership.repo;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.car_dealership.model.Customer;

public class CustomerDaoTest {
	private CustomerDao cd;

	@Before
	public void setup() {
		cd = new CustomerDao();
	}

	@Test
	public void findAllTest() {
		List<Customer> customers = cd.findAll();
		assertTrue(customers.size() == 4);
	}
}
