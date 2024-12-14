package com.example.exception;

public class MessageUpdateFailedException extends RuntimeException{
    public MessageUpdateFailedException(String msg){
        super(msg);
    }   
}
