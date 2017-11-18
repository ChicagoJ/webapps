import java.util.*;

public class ShoppingCart{
	private ArrayList itemsAdded;
	
	public ShoppingCart(){
		itemsAdded = new ArrayList();
	}
	
	public List getItemsAdded(){
		return(itemsAdded);
	}
	
	public synchronized void addItem(String itemID){
		CartItem cItem;
		for(int i = 0; i < itemsAdded.size(); i++){
			cItem = (CartItem)itemsAdded.get(i);
			if(cItem.getItemID().equals(itemID)) {
				cItem.addItemAmount();
				return;
			}
		}
		//??????????????
		CartItem newCItem = new CartItem(ItemBase.getItem(itemID));
		itemsAdded.add(newCItem);
	}
	
	public synchronized void setNumAdded(String itemID, int numAdded){
		CartItem cItem;
		for (int i =0; i < itemsAdded.size(); i++){
			cItem = (CartItem)itemsAdded.get(i);
			if(cItem.getItemID().equals(itemID)){
				if(numAdded <=0){
					itemsAdded.remove(i);
				}else{
					cItem.setAmount(numAdded);
				}
				return;
			}
		}
		//!!!!!!!!!!!!
		CartItem newCItem = new CartItem(ItemBase.getItem(itemID));
		itemsAdded.add(newCItem);
	}
	
}