import java.util.*;
public class Item {
	private String itemID;
	private String itemName;
	private double price;
	private double discount;
	private double rebates;
	private double stocks;
	private List<String> accessories = new ArrayList<String>();

	public Item(String iID, String iNa, double co, double dis, double reb, double sto){
		setItemId(iID);
		setItemName(iNa);
		setPrice(co);
		setDiscount(dis);
		setRebates(reb);
		setStock(sto);
	}
	public Item() {
		super();
	}
	
	public void setItemId(String iID){
		this.itemID = iID;
	}
	public void setPrice(double co){
		this.price = co;
	}
	public void setItemName(String iNa){
		this.itemName = iNa;
	}
	public void setDiscount(double dis){
		this.discount = dis;
	}
	public void setRebates(double reb){
		this.rebates = reb;
	}
	public String getItemId(){
		return(itemID);
	}
	public double getPrice(){
		return(price);
	}
	public String getItemName(){
		return(itemName);
	}
	public double getDiscount(){
		return(discount);
	}
	public double getRebates(){
		return(rebates);
	}
	public List<String> getAccessories() {
		return accessories;
	}
	public void setAccessories(List<String> accessories) {
		this.accessories = accessories;
	}
	public double getStock(){
		return(stocks);
	}
	public void setStock(double sto){
		this.stocks = sto;
	}	
}