package com.ajax;

public class Composer {

    private String id;
    private String productName;
    private String productPrice;
    private String productClass;
    
    
    public Composer (String id, String productName, String productPrice, String productClass) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productClass = productClass;
    }

    public String getproductClass() {
        return productClass;
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