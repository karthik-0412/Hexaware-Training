package com.techshop.oops.entity;

import java.util.Date;

public class Inventory {
	

	
	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}


	public void setLastStockUpdate(Date lastStockUpdate) {
		this.lastStockUpdate = lastStockUpdate;
	}

	private int inventoryID;
    private Product product;
    private int quantityInStock;
    private Date lastStockUpdate;

    public Inventory(int inventoryID, Product product, int quantityInStock, Date lastStockUpdate) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = lastStockUpdate;
    }
    
    
    public Product getProduct() {
        return product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }
    public int getInventoryID() {
		return inventoryID;
	}


	public Date getLastStockUpdate() {
		return lastStockUpdate;
	}
	
	
    public void addToInventory(int quantity) {
        quantityInStock += quantity;
        lastStockUpdate = new Date();
    }

    public void removeFromInventory(int quantity) {
        if (quantity <= quantityInStock) {
            quantityInStock -= quantity;
            lastStockUpdate = new Date();
        }
    }

    public void updateStockQuantity(int newQuantity) {
        quantityInStock = newQuantity;
        lastStockUpdate = new Date();
    }

    public boolean isProductAvailable(int quantityToCheck) {
        return quantityInStock >= quantityToCheck;
    }

    public double getInventoryValue() {
        return product.price * quantityInStock;
    }

    public void listLowStockProducts(int threshold) {
        if (quantityInStock < threshold) {
            System.out.println("Low Stock: " + product.productName + " - " + quantityInStock);
        }
    }

    public void listOutOfStockProducts() {
        if (quantityInStock == 0) {
            System.out.println("Out of Stock: " + product.productName);
        }
    }

    public void listAllProducts() {
        System.out.println("Product: " + product.productName + ", Quantity: " + quantityInStock);
    }
    
    @Override
	public String toString() {
		return "Inventory [inventoryID=" + inventoryID + ", product=" + product + ", quantityInStock=" + quantityInStock
				+ ", lastStockUpdate=" + lastStockUpdate + "]";
	}

    
}
