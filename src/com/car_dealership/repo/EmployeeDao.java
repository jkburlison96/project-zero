package com.car_dealership.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.car_dealership.config.PlainTextConnectionUtil;
import com.car_dealership.model.Employee;

public class EmployeeDao implements DaoContract<Employee, Integer> {

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = new ArrayList<>();
		String sql = "select * from Employee"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				employees.add( new Employee(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}
	
	public boolean validatePassword(String password) {
		boolean valid = false;
		String sql = "select * from Employee where Passkey = ?"; // this will sanitize the input
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
	
	public Employee findByUsername(String username) {
		Employee employee = null;
		boolean valid = false;
		String sql = "select * from Employee where Username = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
				valid = true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(valid) {
			return employee;
		}
		
		return null;
	}

	@Override
	public Employee findById(Integer i) {
		Employee employee = null;
		String sql = "select * from Employee where EmployeeID = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}

	@Override
	public Employee update(Employee t) {
		String sql = "update employee set employeeid = ?, username = ?, passkey = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, t.getUserID());
			ps.setString(2, t.getUserName());
			ps.setString(3, t.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public Employee create(Employee t) {
		String sql = "insert into employee values (?, ?, ?)"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, t.getUserID());
			ps.setString(2, t.getUserName());
			ps.setString(3, t.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public int delete(Integer i) {
		String sql = "delete from employee where employeeid = ?"; // this will sanitize the input
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
