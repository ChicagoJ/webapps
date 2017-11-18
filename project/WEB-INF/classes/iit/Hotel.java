package iit;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Hotel {

	private Integer id;
	private String hid;
	private String name;
	private String descp;
	private String address;
	private String city;
	private Integer zipCodeRange;	
	private Float avgRating;
	
	private ArrayList<Review> reviews;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getZipCodeRange() {
		return zipCodeRange;
	}
	public void setZipCodeRange(Integer zipCodeRange) {
		this.zipCodeRange = zipCodeRange;
	}
	public Float getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Float avgRating) {
		this.avgRating = avgRating;
	}
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}
	
	public Hotel(Integer id, String hid, String name, String descp,
			String address, String city, Integer zipCodeRange, Float avgRating,
			ArrayList<Review> reviews) {
		super();
		this.id = id;
		this.hid = hid;
		this.name = name;
		this.descp = descp;
		this.address = address;
		this.city = city;
		this.zipCodeRange = zipCodeRange;
		this.avgRating = avgRating;
		this.reviews = reviews;
	}
	
	public String toString() {
		return "Hotel [hid=" + hid + ", name=" + name + ", descp=" + descp
				+ ", address=" + address + ", city=" + city + ", zipCodeRange="
				+ zipCodeRange + ", avgRating=" + avgRating + ", reviews="
				+ reviews + "]";
	}
	
	// update a value and call its setter by reflect
	public void updateHotel(String propertyName, Object value) {
		System.out.println("calling method: set" + propertyName);
		Method method = null;
		try {
			if (propertyName.equals("Name") || propertyName.equals("Address")) {
				method = getClass().getDeclaredMethod("set" + propertyName, String.class);
			} else {				
				method = getClass().getDeclaredMethod("set" + propertyName, Float.class);
			}
			method.invoke(this, value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
