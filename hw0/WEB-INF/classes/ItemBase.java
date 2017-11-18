public class ItemBase {
	private static Item[] items = 
	{
		new Item("gc001", "XBox 360", 199.99, 1.0, 20.0),
		new Item("gc002", "XBox One", 349.99, 0.9, 0.0),
		new Item("gc003", "PlayStation 3", 199.99, 1.0, 0.0),
		new Item("gc004", "PlayStation 4", 399.99, 0.5, 0.0),
		new Item("gc005", "Wii", 99.99, 1.0, 0.0),
		new Item("gc006", "Wii U", 199.99, 1.0, 50.0),
    new Item("gc011", "XBox Dummy Accessory", 19.99, 1.0, 0.0),
    new Item("gc012", "PS Dummy Accessory", 19.99, 1.0, 0.0),
    new Item("gc013", "Wii Dummy Accessory", 19.99, 1.0, 0.0),
    new Item("gc021", "XBox Dummy warranty", 19.99, 1.0, 0.0),
    new Item("gc022", "PS Dummy warranty", 19.99, 1.0, 0.0),
    new Item("gc023", "Wii Dummy warranty", 19.99, 1.0, 0.0),
    new Item("gs001", "FIFA 14", 19.99, 1.0, 0.0),
    new Item("gs002", "FORZA 5", 19.99, 1.0, 0.0),
    new Item("gs003", "NBA 2K14", 19.99, 1.0, 0.0),
	};
	
	public static Item getItem(String itemID) {
    Item item;
    if (itemID == null) {
      return(null);
    }
    for(int i=0; i<items.length; i++) {
      item = items[i];
      if (itemID.equals(item.getItemID())) {
        return(item);
      }
    }
    return(null);
  }
}