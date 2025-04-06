package com.techshop.oops.exception;

@SuppressWarnings("serial")
public class InventoryNotFoundException extends Exception {
    public InventoryNotFoundException(String message) {
        super(message);
    }
}