package com.car_dealership.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.car_dealership.config.PlainTextConnectionUtil;
import com.car_dealership.model.Customer;

public class CustomerDao implements DaoContract<Customer, Integer> {

	@Override
	public List<Customer> findAll() {
		List<Customer> customers = new ArrayList<>();
		String sql = "select * from customer";
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customers.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	
	public boolean validatePassword(String password) {
		boolean valid = false;
		String sql = "select * from customer where Passkey = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				valid = true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return valid;
	}
	
	public Customer findByUsername(String username) {
		Customer c = null;
		boolean valid = false;
		String sql = "select * from customer where Username = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3));
				valid = true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(valid) {
			return c;
		}
				
		return null;
	}

	@Override
	public Customer findById(Integer i) {
		// TODO Auto-generated method stub
		Customer c = null;
		String sql = "select * from customer where CustomerID = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Customer(rs.getInt(1), rs.getString(2), com.car_dealership.util.Util.decrypt(rs.getString(3)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Customer update(Customer t) {
		String sql = "update customer set username = ?, passkey = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setInt(1, t.getUserID());
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public Customer create(Customer t) {
		String sql = "insert into customer (Username, Passkey) values (?, ?)"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setInt(1, t.getUserID());
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public int delete(Integer i) {
		String sql = "delete from customer where customerid = ?"; // this will sanitize the input
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
