package com.theduo.storefront.user.exception;

public class ExistByEmailException extends RuntimeException {
    public ExistByEmailException() {
        super("Email already exists");
    }
}
