import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
public class Stock {
      HashMap<String, Integer> hmap = new HashMap<String, Integer>();
      public Stock(){
	

      /*Adding elements to HashMap*/
      hmap.put("gc001", 100);
      hmap.put("gc002", 90);
      hmap.put("gc003", 80);
      hmap.put("gc004", 70);
      hmap.put("gc005", 60);
      hmap.put("gc006", 50);
      hmap.put("gc011", 999);
      hmap.put("gc012", 999);
      hmap.put("gc013", 999);
      hmap.put("gc021", 999);
      hmap.put("gc022", 999);
      hmap.put("gc023", 999);
      hmap.put("gs001", 40);
      hmap.put("gs002", 30);
      hmap.put("gs003", 20);
      }
      
      public int getStock(String itemId){
            
            return hmap.get(itemId);
      }
}