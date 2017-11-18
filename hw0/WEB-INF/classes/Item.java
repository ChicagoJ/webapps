public class Item {
	private String itemID;
	private String itemName;
	private double cost;
	private double discount;
	private double rebates;
	
	public Item(String iID, String iNa, double co, double dis, double reb){
		setItemID(iID);
		setItemName(iNa);
		setCost(co);
		setDiscount(dis);
		setRebates(reb);
	}
	public void setItemID(String iID){
		this.itemID = iID;
	}
	public void setCost(double co){
		this.cost = co;
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
	public String getItemID(){
		return(itemID);
	}
	public double getCost(){
		return(cost);
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
}