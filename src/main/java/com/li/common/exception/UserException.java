package com.li.common.exception;

public class UserException extends RuntimeException{
    public UserException() {
    }
    public UserException(String message) {
        super(message);
    }
}
