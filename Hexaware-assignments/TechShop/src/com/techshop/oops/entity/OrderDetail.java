package com.techshop.oops.entity;

public class OrderDetail {
	

	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private int orderDetailID;
	private Order order;
	private Product product;
	private int quantity;
	
	public OrderDetail(int orderDetailID, Order order, Product product, int quantity) {
		super();
		this.orderDetailID = orderDetailID;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}
	
	public int getOrderDetailID() {
		return orderDetailID;
	}

	public Order getOrder() {
		return order;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public double calculateSubtotal() {
        return product.isProductInStock() ? product.price * quantity : 0;
    }

    public void getOrderDetailInfo() {
        System.out.println("Order Detail ID: " + orderDetailID);
        System.out.println("Product: " + product);
        System.out.println("Quantity: " + quantity);
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addDiscount(double discount) {
        System.out.println("Discount of " + discount + " applied.");
    }
	
    @Override
	public String toString() {
		return "OrderDetail [orderDetailID=" + orderDetailID + ", order=" + order + ", product=" + product
				+ ", quantity=" + quantity + "]";
	}
    
	

}
