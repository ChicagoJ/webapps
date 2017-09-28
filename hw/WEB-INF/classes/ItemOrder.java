
public class ItemOrder {
  private Item item;
  private int numItems;

  public ItemOrder(Item item) {
    setItem(item);
    setNumItems(1);
  }

  public Item getItem() {
    return(item);
  }

  protected void setItem(Item item) {
    this.item = item;
  }

  public String getItemId() {
    return(getItem().getItemId());
  }  

  public String getItemName() {
    return(getItem().getItemName());
  }


  public double getCost() {
    return(getItem().getPrice());
  }

  public double getDiscount() {
    return(getItem().getDiscount());
  }   
  public double getRebates() {
    return(getItem().getRebates());
  } 
  
  public int getNumItems() {
    return(numItems);
  }

  public void setNumItems(int n) {
    this.numItems = n;
  }

  public void incrementNumItems() {
    setNumItems(getNumItems() + 1);
  }

  public void cancelOrder() {
    setNumItems(0);
  }

  public double getTotalCost() {
    if(getDiscount()!=0){
        return(getNumItems() * getCost() * getDiscount() - getRebates());
    } else {
        return (getNumItems() * getCost() - getRebates());
    }
  }
}
