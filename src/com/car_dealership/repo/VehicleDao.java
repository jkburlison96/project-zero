package com.car_dealership.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.car_dealership.PaintColor;
import com.car_dealership.VehicleType;
import com.car_dealership.config.PlainTextConnectionUtil;
import com.car_dealership.model.Vehicle;

public class VehicleDao implements DaoContract<Vehicle, Integer> {
	
	public List<Vehicle> findAllFromCustomer(Integer i) {
		List<Vehicle> vehicles = new ArrayList<>();
		String sql = "select * from get_customer_owned_vehicles(?)"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareCall(sql)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vehicles.add(new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), VehicleType.valueOf(rs.getString(5)), 
						PaintColor.valueOf(rs.getString(6)), rs.getInt(7), rs.getInt(8), rs.getInt(9), true));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vehicles;
	}

	@Override
	public List<Vehicle> findAll() {
		List<Vehicle> vehicles = new ArrayList<>();
		String sql = "select * from vehicle where isSold = false"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vehicles.add(new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), VehicleType.valueOf(rs.getString(5)), 
						PaintColor.valueOf(rs.getString(6)), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getBoolean(10)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vehicles;
	}
	
	public Vehicle findById(String s) {
		Vehicle v = null;
		String sql = "select * from Vehicle where vin = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				v = new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), VehicleType.valueOf(rs.getString(5)), 
						PaintColor.valueOf(rs.getString(6)), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getBoolean(10));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return v;
	}

	@Override
	public Vehicle findById(Integer i) {
		Vehicle v = null;
		String sql = "select * from Vehicle where vin = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				v = new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), VehicleType.valueOf(rs.getString(5)), 
						PaintColor.valueOf(rs.getString(6)), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getBoolean(10));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return v;
	}

	@Override
	public Vehicle update(Vehicle t) {
		String sql = "update Vehicle set make = ?, model = ?, made = ?, vehicleType = ?, paintColor = ?, "
				+ "mpg = ?, topSpeed = ?, horsepower = ?, isSold = ? where vin like ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, t.getMake());
			ps.setString(2, t.getModel());
			ps.setInt(3, t.getYear());
			ps.setString(4, t.getTypeOfVehicle().toString());
			ps.setString(5, t.getPaintColor().toString());
			ps.setInt(6, t.getMPG());
			ps.setInt(7, t.getTopSpeed());
			ps.setInt(8, t.getHorsepower());
			ps.setBoolean(9, t.getIsSold());
			ps.setString(10, t.getVin());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public Vehicle create(Vehicle t) {
		String sql = "insert into Vehicle values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, t.getVin());
			ps.setString(2, t.getMake());
			ps.setString(3, t.getModel());
			ps.setInt(4, t.getYear());
			ps.setString(5, t.getTypeOfVehicle().toString());
			ps.setString(6, t.getPaintColor().toString());
			ps.setInt(7, t.getMPG());
			ps.setInt(8, t.getTopSpeed());
			ps.setInt(9, t.getHorsepower());
			ps.setBoolean(10, t.getIsSold());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public String delete(String s) {
		String sql = "delete from vehicle where vin = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, s);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}

	@Override
	public int delete(Integer i) {
		String sql = "delete from vehicle where vin = ?"; // this will sanitize the input
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
