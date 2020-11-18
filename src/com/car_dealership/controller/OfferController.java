package com.car_dealership.controller;

import java.util.List;

import com.car_dealership.model.Offer;
import com.car_dealership.repo.OfferDao;

public class OfferController {
	private OfferDao od;

	public OfferController() {
		this.od = new OfferDao();
	}

	public Offer findById(Integer i) {
		return od.findById(i);
	}

	public List<Offer> findAllFromCustomer(Integer i) {
		return od.findAllFromCustomer(i);
	}

	public List<Offer> findAll() {
		return od.findAll();
	}
	
	public Offer rejectOtherOffers(Offer t) {
		return od.rejectOtherOffers(t);
	}

	public Offer update(Offer t) {
		return od.update(t);
	}

	public Offer create(Offer t) {
		return od.create(t);
	}

	public int delete(Integer i) {
		return od.delete(i);
	}
}
