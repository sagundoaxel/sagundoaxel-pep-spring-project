package com.example.exception;

public class MessageNotCreatedException extends RuntimeException{
    public MessageNotCreatedException(String msg){
        super(msg);
    }
    
}
