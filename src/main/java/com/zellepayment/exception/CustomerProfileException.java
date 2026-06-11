package com.zellepayment.exception;

public class CustomerProfileException extends Exception{
    public CustomerProfileException(){
        super();
    }
    public CustomerProfileException(String msg){
        super(msg);
    }
    public CustomerProfileException(String message,Throwable throwable){
        super(message,throwable);
    }
}
