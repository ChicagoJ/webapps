package iit;

import java.io.Serializable;

public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String roomId;
	private String roomType;
	private String descp;
	private String hid;
	private Double price;
	private Double discount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscountedPrice() {
		return this.price * this.getDiscount();
	}
	
	public Room(Integer id, String roomId, String roomType, String descp,
			String hid, Double price, Double discount) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.roomType = roomType;
		this.descp = descp;
		this.hid = hid;
		this.price = price;
		this.discount = discount;
	}
	
	
	public String toString() {
		return "Room [id=" + id + ", roomId=" + roomId + ", roomType="
				+ roomType + ", hid=" + hid + "]";
	}
	
}
