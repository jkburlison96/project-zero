package com.car_dealership.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.car_dealership.config.PlainTextConnectionUtil;
import com.car_dealership.model.Payment;

public class PaymentDao implements DaoContract<Payment, Integer> {

	public List<Payment> findAllFromCustomer(Integer i) {
		List<Payment> payments = new ArrayList<>();
		String sql = "select * from Payment where CustomerID = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				payments.add( new Payment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return payments;
	}
	
	@Override
	public List<Payment> findAll() {
		List<Payment> payments = new ArrayList<>();
		String sql = "select * from Payment"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				payments.add( new Payment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return payments;
	}

	@Override
	public Payment findById(Integer i) {
		Payment payment = null;
		String sql = "select * from Payment where PaymentID = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				payment = new Payment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return payment;
	}

	@Override
	public Payment update(Payment t) {
		String sql = "update payment set paymentid = ?, amount = ?, vin = ?, customerid = ?, lastPayment = ?, nextPayment = ?, paymentsLeft = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, t.getPaymentID());
			ps.setInt(2, t.getAmount());
			ps.setString(3, t.getVin());
			ps.setInt(4, t.getCustomerID());
			ps.setString(5, t.getLastPaymentDate());
			ps.setString(6, t.getNextPaymentDate());
			ps.setInt(7, t.getNumPaymentsLeft());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public Payment create(Payment t) {
		String sql = "insert into payment (amount, vin, customerid, lastpayment, nextpayment, paymentsleft) values (?, ?, ?, ?, ?, ?)"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setInt(1, t.getPaymentID());
			ps.setInt(1, t.getAmount());
			ps.setString(2, t.getVin());
			ps.setInt(3, t.getCustomerID());
			ps.setString(4, t.getLastPaymentDate());
			ps.setString(5, t.getNextPaymentDate());
			ps.setInt(6, t.getNumPaymentsLeft());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public int delete(Integer i) {
		String sql = "delete from payment where paymentid = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, String.valueOf(i));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}

}
