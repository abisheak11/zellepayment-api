package com.zellepayment.exception;

public class TransactionLogsException extends Exception{
    public TransactionLogsException(){
        super();
    }

    public TransactionLogsException(String message){
        super(message);
    }

    public TransactionLogsException(String message,Throwable throwable){
        super(message,throwable);
    }
}
