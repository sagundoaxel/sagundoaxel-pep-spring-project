package com.example.exception;

public class UsernameExists extends RuntimeException  {
    public UsernameExists(String message){
        super(message);
    }
    
}
