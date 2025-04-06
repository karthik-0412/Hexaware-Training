package com.techshop.oops.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	

	private int CustomerID;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String address;
	private List<Order> orders;
	public Customer(int customerID, String firstname, String lastname, String email, String phone, String address) {
		super();
		CustomerID = customerID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.orders = new ArrayList<>();
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int calculateTotalOrders() {
        return orders.size();
    }
	
	public void getCustomerDetails() {
		System.out.println("Customer ID: "+ CustomerID);
		System.out.println("Name: "+firstname+" "+lastname);
		System.out.println("Email: "+email);
		System.out.println("Phone: "+phone);
		System.out.println("Address: "+address);
	}
	
	public void updateCustomerInfo(String email, String phone, String address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
	
	
}
