package com.zellepayment.exception;

public class TransactionsException extends Exception{
    public TransactionsException(){
        super();
    }

    public TransactionsException(String message){
        super(message);
    }

    public TransactionsException(String message,Throwable throwable){
        super(message,throwable);
    }
}
