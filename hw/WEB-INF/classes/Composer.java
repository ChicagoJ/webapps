public class Composer {

    private String id;
    private String productName;
    private String productPrice;
    
    
    public Composer (String id, String productName, String productPrice) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    
    public String getId() {
        return id;
    }
    
    public String getproductName() {
        return productName;
    }
    
    public String getproductPrice() {
        return productPrice;
    }
}