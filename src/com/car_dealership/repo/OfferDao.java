package com.car_dealership.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.car_dealership.config.PlainTextConnectionUtil;
import com.car_dealership.model.Offer;

public class OfferDao implements DaoContract<Offer, Integer> {

	public List<Offer> findAllFromCustomer(Integer i) {
		List<Offer> offers = new ArrayList<>();
		String sql = "select * from Offer where CustomerID = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				offers.add( new Offer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offers;
	}
	
	@Override
	public List<Offer> findAll() {
		List<Offer> offers = new ArrayList<>();
		String sql = "select * from Offer"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				offers.add( new Offer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return offers;
	}
	
	@Override
	public Offer findById(Integer i) {
		Offer o = null;
		String sql = "select * from Offer where OfferID = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				o = new Offer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return o;
	}
	
	public Offer rejectOtherOffers(Offer t) {
		String sql = "update Offer set status = 'Rejected' where vin = ? and customerid != ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setInt(1, t.getOfferID());
			ps.setString(1, t.getVin());
			ps.setInt(2, t.getCustomerID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public Offer update(Offer t) {
		String sql = "update Offer set status = ? where vin = ? and customerid = ?"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setInt(1, t.getOfferID());
			ps.setString(1, t.getStatus());
			ps.setString(2, t.getVin());
			ps.setInt(3, t.getCustomerID());
//			ps.setInt(3, t.getAmount());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public Offer create(Offer t) {
		String sql = "insert into offer (vin, customerid, amount, status) values (?, ?, ?, ?)"; // this will sanitize the input
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setInt(1, t.getOfferID());
			ps.setString(1, t.getVin());
			ps.setInt(2, t.getCustomerID());
			ps.setInt(3, t.getAmount());
			ps.setString(4, t.getStatus());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public int delete(Integer i) {
		String sql = "delete from offer where offerid = ?"; // this will sanitize the input
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
