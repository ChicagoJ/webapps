import java.util.*;

public class Order implements java.io.Serializable {
	private String orderId;
	private String userId;
	private Calendar orderTime;
	private Calendar endCancelTime;
	private List itemsOrdered ;
	private double price;
	private String conformationID;
	private String itemName;
	private int itemNumber;
	
	public Order(String oid, String uid, Calendar odt, String cid){
		this.orderId = oid;
		this.userId = uid;
		this.orderTime = odt;
		this.conformationID = cid;
	}

	public Order(String uid, String oid, String itemName, int itemNumber, String cid){
		setUserId(uid);
		setOrderId(oid);
		setItemName(itemName);
		setConformationId(cid);
		setItemNumber(itemNumber);
	}

	public void setOrderId(String oid){
		this.orderId = oid;
	}	
	public void setUserId(String uid){
		this.userId = uid;
	}
	public void setOrderTime(Calendar odt){
		this.orderTime = odt;
	}
	public void setEndCancelTime(Calendar ect){
		this.endCancelTime = ect;
	}
	public void setItemsOrdered(List iod){
		this.itemsOrdered = iod;
	}
	public String getOrderId(){
		return orderId;
	}
	public String getUserId(){
		return userId;
	}
	public Calendar getOrderTime(){
		return orderTime;
	}
	public Calendar getEndCancelTime(){
		return endCancelTime;
	}
	public List getItemsOrdered(){
		return itemsOrdered;
	}
	public void setConformationId(String cid){
		this.conformationID = cid;
	}
	public String getConformationId(){
		return conformationID;
	}
	public void setPrice(double pri){
		this.price = pri;
	}
	public double getPrice(){
		return price;
	}
	public String getFormattedOrderTime() {
		return (orderTime.get(Calendar.MONTH) + 1) + "/" + orderTime.get(Calendar.DATE) + "/" + orderTime.get(Calendar.YEAR);
	}
	public void setItemName(String itemName){
		this.itemName = itemName;
	}
	public String getItemName(){
		return itemName;
	}
	public void setItemNumber(int itemNumber){
		this.itemNumber = itemNumber;
	}
	public int getItemNumber(){
		return itemNumber;
	}


}