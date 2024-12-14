package com.example.exception;

public class UsernameAndPasswordMismatchException extends RuntimeException {
    public UsernameAndPasswordMismatchException(String msg){
        super(msg);
    }
    
}
