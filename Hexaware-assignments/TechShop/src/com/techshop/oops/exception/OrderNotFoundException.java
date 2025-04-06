package com.techshop.oops.exception;

@SuppressWarnings("serial")
public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
