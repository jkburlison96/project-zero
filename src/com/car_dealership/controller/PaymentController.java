package com.car_dealership.controller;

import java.util.List;

import com.car_dealership.model.Payment;
import com.car_dealership.repo.PaymentDao;

public class PaymentController {
	private PaymentDao pd;

	public PaymentController() {
		this.pd = new PaymentDao();
	}

	public Payment findById(Integer i) {
		return pd.findById(i);
	}

	public List<Payment> findAllFromCustomer(Integer i) {
		return pd.findAllFromCustomer(i);
	}

	public List<Payment> findAll() {
		return pd.findAll();
	}

	public Payment update(Payment t) {
		return pd.update(t);
	}

	public Payment create(Payment t) {
		return pd.create(t);
	}

	public int delete(Integer i) {
		return pd.delete(i);
	}

}
