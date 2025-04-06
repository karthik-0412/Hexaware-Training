package com.techshop.oops.entity;

public class Product {

	private int productID;
	String productName;
	private String description;
	double price;
	private boolean instock;
	public Product(int productID, String productName, String description, double price, boolean instock) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.instock = instock;
	}
	
	public int getProductID() {
		return productID;
	}

	

	public String getProductName() {
		return productName;
	}



	public String getDescription() {
		return description;
	}



	public double getPrice() {
		return price;
	}



	public boolean isInstock() {
		return instock;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public void getProductDetails() {
		System.out.println("Product ID:"+ productID);
		System.out.println("Name: "+ productName);
		System.out.println("Description: "+ description);
		
	}
	
	public void updateProductInfo(double price, String description) {
        this.price = price;
        this.description = description;
    }

    public boolean isProductInStock() {
        return instock;
    }
	
    @Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + ", instock=" + instock + "]";
	}
}
