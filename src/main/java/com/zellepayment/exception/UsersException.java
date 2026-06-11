package com.zellepayment.exception;

public class UsersException extends Exception{

    public UsersException(){
        super();
    }

    public UsersException(String message){
        super(message);
    }

    public UsersException(String message,Throwable throwable){
        super(message,throwable);
    }
}
