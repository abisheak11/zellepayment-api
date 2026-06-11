package com.zellepayment.exception;

public class BankAccountsException extends Exception{
    public BankAccountsException(){
        super();
    }

    public BankAccountsException(String message){
        super(message);
    }

    public BankAccountsException(String message,Throwable throwable){
        super(message,throwable);
    }
}
