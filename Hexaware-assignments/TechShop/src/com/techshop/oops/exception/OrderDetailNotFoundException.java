package com.techshop.oops.exception;

@SuppressWarnings("serial")
public class OrderDetailNotFoundException extends Exception {
    public OrderDetailNotFoundException(String message) {
        super(message);
    }
}