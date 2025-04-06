package com.techshop.oops.exception;

@SuppressWarnings("serial")
public class IncompleteOrderException extends Exception {
    public IncompleteOrderException(String message) {
        super(message);
    }

    public IncompleteOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}