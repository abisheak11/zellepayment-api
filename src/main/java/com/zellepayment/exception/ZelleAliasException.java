package com.zellepayment.exception;

public class ZelleAliasException extends Exception{

    public ZelleAliasException(){
        super();
    }

    public ZelleAliasException(String message){
        super(message);
    }

    public ZelleAliasException(String message,Throwable throwable){
        super(message,throwable);
    }
}
