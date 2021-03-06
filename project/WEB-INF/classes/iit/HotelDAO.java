package iit;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class HotelDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static HashMap<String, Hotel> hotelsMap = new HashMap<String, Hotel>();
	private static ConnectionUtil connUtil = ConnectionUtil.getInstance();
	
	/**
	 * Get all hotels map
	 */
	public static HashMap<String, Hotel> getAllHotels() {
		if (hotelsMap != null && !hotelsMap.isEmpty()) {
			return hotelsMap;
		}
		
		// get hotels from db.hotels table
		// no users in hashmap, then get user from db
		String sql = "SELECT * FROM hotels";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = connUtil.getConnection();
			System.out.println("getAllHotels conn: " + conn);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
	            String hid  = rs.getString("hid");
	            String name = rs.getString("name");
	            String descp = rs.getString("descp");
	            String address = rs.getString("address");
	            String city = rs.getString("city");
	            Integer zipCodeRange = rs.getInt("zipCodeRange");
	            Float avgRating = rs.getFloat("avgRating");
	            
	            Hotel hotel = new Hotel(id, hid, name, descp, address, city, zipCodeRange, avgRating, null);
	            hotelsMap.put(hid, hotel);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		System.out.println("Get all hotels from db");
		return hotelsMap;
	}
	
	/*
	public static HashMap<String, Hotel> getNameUrl() {
		ArrayList<AutocompleteData> nameUrls = new ArrayList<AutocompleteData>();
		if (hotelsMap != null && !hotelsMap.isEmpty()) {
			for (Hotel hotel : hotelsMap.values()) {
				nameUrls.add(new AutocompleteData(hotel.getName(), "./hotelInfo.jsp?hid=" + hotel.getHid()));
			}
			return nameUrls;
		}
		String sql = "SELECT * FROM hotels";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = connUtil.getConnection();
			System.out.println("getHotelUrlByHid conn: " + conn);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
	            String hid  = rs.getString("hid");
	            String name = rs.getString("name");
	            nameUrls.add(new AutocompleteData(name, "./hotelInfo.jsp?hid=" + hid));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		System.out.println("getHotelUrlByHid from db");
		return nameUrls;
	}
	*/
	
	
	/**
	 * Get hotels by zip code
	 */
	public static ArrayList<Hotel> getHotelsByZipCode(Integer zipCodeRange) {
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		if (hotelsMap != null && !hotelsMap.isEmpty()) {
			for (Hotel hotel : hotelsMap.values()) {
				if (hotel.getZipCodeRange() == zipCodeRange) {
					hotelList.add(hotel);
				}
			}
			return hotelList;
		}
		
		// get hotels from db.hotels table
		String sql = "SELECT * FROM hotels WHERE zipCodeRange = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = connUtil.getConnection();
			System.out.println("getHotelsByZipCode conn: " + conn);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, zipCodeRange);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
	            String hid  = rs.getString("hid");
	            String name = rs.getString("name");
	            String descp = rs.getString("descp");
	            String address = rs.getString("address");
	            String city = rs.getString("city");
	            Float avgRating = rs.getFloat("avgRating");
	            
	            Hotel hotel = new Hotel(id, hid, name, descp, address, city, zipCodeRange, avgRating, null);
//				hotelsMap.put(hid, hotel);
				hotelList.add(hotel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		System.out.println("Get hotels by zip code from db");
		return hotelList;
	}
	
	
	/**
	 * Get hotels by zip code
	 */
	public static ArrayList<Hotel> getHotelsByCity(String city) {
		city = city.toLowerCase();
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		if (hotelsMap != null && !hotelsMap.isEmpty()) {
			for (Hotel hotel : hotelsMap.values()) {
				if (hotel.getCity().toLowerCase().equals(city)) {
					hotelList.add(hotel);
				}
			}
			return hotelList;
		}
		
		// get hotels from db.hotels table
		String sql = "SELECT * FROM hotels WHERE city like ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = connUtil.getConnection();
			System.out.println("getHotelsByCity conn: " + conn);
			ps = conn.prepareStatement(sql);
			ps.setString(1, city);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
	            String hid  = rs.getString("hid");
	            String name = rs.getString("name");
	            String descp = rs.getString("descp");
	            String address = rs.getString("address");
	            Integer zipCodeRange = rs.getInt("zipCodeRange");
	            Float avgRating = rs.getFloat("avgRating");
	            
	            Hotel hotel = new Hotel(id, hid, name, descp, address, city, zipCodeRange, avgRating, null);
//				hotelsMap.put(hid, hotel);
				hotelList.add(hotel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		System.out.println("Get hotels by zip code from db");
		return hotelList;
	}
	
	/**
	 * Manager deletes a hotel from db
	 */
	public static boolean managerDelete(String hid) {
		if (hid == null || hid.length() == 0) {
			return false;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "DELETE FROM hotels WHERE hid = ? and id != 0";
		
		try {
//			conn = MySQLDataStoreUtilities.getConnection();
			conn = connUtil.getConnection();
			System.out.println("manager delete conn: " + conn);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, hid);;
			
			// delete the order from db
			ps.executeUpdate();		
			System.out.println(hid + " removed.");
			hotelsMap.remove(hid);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
//		System.out.println(all.get(id));
	}
	
	
	/**
	 * Manager adds a hotel into db.
	 * Successful insert returns the hotel id, otherwise null
	 */
	public static Integer managerAdd(Hotel hotel) {
		if (hotelsMap.containsKey(hotel.getHid())) {
			return null;
		}
		
		Integer id = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO hotels(hid, name, address, zipCodeRange, avgRating) "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
			conn = connUtil.getConnection();
			System.out.println("managerAdd conn: " + conn);
			
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, hotel.getHid());
			ps.setString(2, hotel.getName());
			ps.setString(3, hotel.getAddress());
			ps.setInt(4, hotel.getZipCodeRange());
			ps.setDouble(5, hotel.getAvgRating());
			
			// insert the hotel into db
			ps.executeUpdate();	
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				id = rs.getInt(1);
			}
			hotel.setId(id);
			hotelsMap.put(hotel.getHid(), hotel);
			System.out.println(hotel + ", id = " + id + " inserted into db.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		return id;
	}
	
	
	/**
	 * Manager updates an hotel
	 */
	public static boolean managerUpdate(String hid, String property, Object value) {
		if (hid == null || hid.length() == 0) {
			return false;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "UPDATE hotels SET " + property.toLowerCase() + " = ? WHERE hid = ? and id != 0";
		
		try {
//			conn = MySQLDataStoreUtilities.getConnection();
			conn = connUtil.getConnection();
			System.out.println("managerUpdate conn: " + conn);
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, value);
			ps.setString(2, hid);
			
			// insert the hotel into db
			ps.executeUpdate();		
			System.out.println(hid + " updated in db.");
			hotelsMap.get(hid).updateHotel(property, value);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
	}

	
	/**
	 * Get a hotel object by using the hid
	 */
	public static Hotel getHotelById(String hid) {
		if (hotelsMap != null && !hotelsMap.isEmpty()) {
			return hotelsMap.get(hid);
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Hotel hotel = null;
		String sql = "SELECT * FROM hotels WHERE hid = ?";
		
		try {
			conn = connUtil.getConnection();
			System.out.println("getHotelById conn: " + conn);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, hid);
			
			// get the order from db
			rs = ps.executeQuery();		
			if (rs.next()) {
				Integer id = rs.getInt("id");
	            String name = rs.getString("name");
	            String descp = rs.getString("descp");
	            String address = rs.getString("address");
	            String city = rs.getString("city");
	            Integer zipCodeRange = rs.getInt("zipCodeRange");
	            Float avgRating = rs.getFloat("avgRating");
	            
	            // set accessories, sold and avgRating
	            hotel = new Hotel(id, hid, name, descp, address, city, zipCodeRange, avgRating, null);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		return hotel;
	}
	
	
	/**
	 * Get a hotel's name by using the hotel id
	 */
	public static String getNameById(String hid) {
		if (hotelsMap != null && !hotelsMap.isEmpty()) {
			return hotelsMap.get(hid).getName();
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		String sql = "SELECT name FROM hotels WHERE hid = ?";
		
		try {
			conn = connUtil.getConnection();
			System.out.println("getTitleById conn: " + conn);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, hid);
			
			// get the title from db
			rs = ps.executeQuery();		
			if (rs.next()) {
	            name = rs.getString("name");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		return name;
	}
	
	
	/**
	 * Updates hotels' stock and sold count
	 */
	public static void updateHotelAvgRating(String hid, Float avgRating) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "UPDATE hotels SET avgRating = ? WHERE hid = ? and id != 0";
		
		try {
			conn = connUtil.getConnection();
			System.out.println("updateHotelAvgRating conn: " + conn);
			
			ps = conn.prepareStatement(sql);
		    ps.setFloat(1, avgRating);
		    ps.setString(2, hid);
            ps.executeUpdate();
            System.out.println(hid + "'s avgRating updated into db.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
	}
	
	/** statistics */
	
	/**
	 * Returns hotels filtered by arguments
	 */
	public static ArrayList<Hotel> hotelSearch(Hotel hotel4Search) {
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		
		String hid = hotel4Search.getHid();
		String name = hotel4Search.getName();
		String address = hotel4Search.getAddress();
		String city = hotel4Search.getCity();
		Integer zipCodeRange = hotel4Search.getZipCodeRange();
		Float avgRating = hotel4Search.getAvgRating();
		System.out.println("Product to search: " + hotel4Search);
		
		// get reviews from db.reviews table
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM hotels";
		
		if (hid.equals("") && name.equals("") && address.equals("") && city.equals("") && zipCodeRange == null && avgRating == null) {
			sql += "";
		} else {
			sql += " WHERE";
			sql += (!hid.equals("") ? " hid = '" + hid + "' and" : "");
			sql += (!name.equals("") ? " name like '%" + name + "%' and" : "");
			sql += (!address.equals("") ? " address like '%" + address + "%' and" : "");
			sql += (!city.equals("") ? " city like '%" + city + "%' and" : "");
			sql += (zipCodeRange != null ? " zipCodeRange = " + zipCodeRange + " and" : "");
			sql += (avgRating != null ? " avgRating >= " + avgRating : "");
			
			int len = sql.length();
			if (sql.substring(len - 3).equals("and")) {
				sql = sql.substring(0, len - 3);
			}
		}
		
		try {
			conn = connUtil.getConnection();
			System.out.println("hotelSearch conn: " + conn);
			ps = conn.prepareStatement(sql);
			System.out.println("Sql: " + ps);
			rs = ps.executeQuery();
			
			while (rs.next()) {
	            Hotel hotel = new Hotel(rs.getInt("id"), rs.getString("hid"), rs.getString("name"), rs.getString("descp"),
	            		rs.getString("address"), rs.getString("city"), rs.getInt("zipCodeRange"), rs.getFloat("avgRating"), null);
	            hotelList.add(hotel);
	        }
			System.out.println("Get all searched hotels from db");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		return hotelList;
	}
}
