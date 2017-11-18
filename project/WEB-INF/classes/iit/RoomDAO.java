package iit;

import java.io.*;
import java.sql.*;
import java.util.*;

public class RoomDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static HashMap<String, Room> roomsMap = new HashMap<String, Room>();
	private static ConnectionUtil connUtil = ConnectionUtil.getInstance();
	
	public static HashMap<String, Room> getAllRooms() {
		if (roomsMap != null && !roomsMap.isEmpty()) {
			return roomsMap;
		}
		
		// get items from db.products table
		// no users in hashmap, then get user from db
		String sql = "SELECT * FROM rooms";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			conn = connUtil.getConnection();
			System.out.println("getAllItems conn: " + conn);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
	            String roomId  = rs.getString("roomId");
	            String roomType = rs.getString("roomType");
	            String descp = rs.getString("descp");
	            String hid = rs.getString("hid");
	            Double price = rs.getDouble("price");
	            Double discount = rs.getDouble("discount");
	            
	            // set accessories, sold and avgRating
	            Room room = new Room(id, roomId, roomType, descp, hid, price, discount);
	            roomsMap.put(roomId, room);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		System.out.println("Get all rooms from db");
		return roomsMap;
	}
	
	
	/**
	 * Get rooms of a hotel
	 */
	public static ArrayList<Room> getRoomsOfHotel(String hid) {
		ArrayList<Room> roomList = new ArrayList<Room>();
		if (roomsMap != null && !roomsMap.isEmpty()) {
			for (Room room : roomsMap.values()) {
				if (room.getHid().equals(hid)) {
					roomList.add(room);
				}
			}
			return roomList;
		}
		
		// get rooms from db.rooms table
		// no rooms in hashmap, then get room from db
		String sql = "SELECT * FROM rooms WHERE hid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			conn = connUtil.getConnection();
			System.out.println("getRoomsOfHotel conn: " + conn);
			ps = conn.prepareStatement(sql);
			ps.setString(1, hid);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String roomId  = rs.getString("roomId");
				String roomType = rs.getString("roomType");
				String descp = rs.getString("descp");
				Double price = rs.getDouble("price");
				Double discount = rs.getDouble("discount");
				
				// set accessories, sold and avgRating
				Room room = new Room(id, roomId, roomType, descp, hid, price, discount);
				roomsMap.put(roomId, room);
				roomList.add(room);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		System.out.println("Get all rooms from db");
		return roomList;
	}
	
	
	/**
	 * Get a room object by using the roomId
	 */
	public static Room getRoomById(String roomId) {
		if (roomsMap != null && !roomsMap.isEmpty()) {
			return roomsMap.get(roomId);
		}
		Room room = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM rooms WHERE roomId = ?";
		
		try {
			conn = connUtil.getConnection();
			System.out.println("getRoomById conn: " + conn);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, roomId);
			
			// get the title from db
			rs = ps.executeQuery();		
			if (rs.next()) {
				Integer id = rs.getInt("id");
	            String roomType = rs.getString("roomType");
	            String descp = rs.getString("descp");
	            String hid = rs.getString("hid");
	            Double price = rs.getDouble("price");
	            Double discount = rs.getDouble("discount");
	            
	            room = new Room(id, roomId, roomType, descp, hid, price, discount);
	            roomsMap.put(roomId, room);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		return room;
	}
	
	
	
	
	/**
	 * Get a room's type by using the roomId
	 */
	public static String getTypeById(String roomId) {
		if (roomsMap != null && !roomsMap.isEmpty()) {
			return roomsMap.get(roomId).getRoomType();
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String type = "";
		String sql = "SELECT roomType FROM rooms WHERE roomId = ?";
		
		try {
			conn = connUtil.getConnection();
			System.out.println("getTypeById conn: " + conn);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, roomId);
			
			// get the title from db
			rs = ps.executeQuery();		
			if (rs.next()) {
	            type = rs.getString("roomType");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		return type;
	}
	
	
	/**
	 * Get a hotel's lowest price of rooms
	 */
	public static Double getLowestPriceOfHotel(String hid) {
		Double lowest = Double.MAX_VALUE;
		if (roomsMap != null && !roomsMap.isEmpty()) {
			for (Room room : roomsMap.values()) {
				if (room.getHid().equals(hid) && room.getDiscountedPrice() < lowest) {
					lowest = room.getPrice();
				}
			}
			return lowest;
		}
		
		// query from db if no result from memory
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT min(price * discount) FROM rooms WHERE hid = ?";
		
		try {
			conn = connUtil.getConnection();
			System.out.println("getLowestPriceOfHotel conn: " + conn);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, hid);
			
			// get the title from db
			rs = ps.executeQuery();		
			if (rs.next()) {
				lowest = rs.getDouble("min(price * discount)");
			}
//			System.out.println("lowerst price: " + lowest);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MySQLDataStoreUtilities.release(rs, ps);
		}
		return lowest;
	}
	
}
