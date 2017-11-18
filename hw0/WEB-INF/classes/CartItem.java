public class CartItem {
	private Item item;
	private int amount;
	
	public CartItem(Item it){
		setItem(it);
		setAmount(1);
	}
	
	public void setItem(Item it){
		this.item = it;
	}
	public void setAmount(int n){
		this.amount = n;
	}
	public Item getItem(){
		return(item);
	}
	public int getAmount(){
		return(amount);
	}
	
	public void addItemAmount(){
		setAmount(getAmount() + 1);
	}
	public void deleteItem(){
		setAmount(0);
	}
	public double getTotalCost(){
		return((getItem().getCost() * getItem().getDiscount()) * getAmount());
	}
	
	//fixing
	public String getItemID() {
    	return(getItem().getItemID());
    }
	
	public String getItemName() {
    	return(getItem().getItemName());
    }
	
	public double getCost() {
    	return(getItem().getCost());
    }
	public double getDiscount(){
		return(getItem().getDiscount());
	}
	public double getRebates(){
		return(getItem().getRebates());
	}
}