package com.zellepayment.services;

import com.zellepayment.exception.TransactionsException;
import com.zellepayment.model.TransactionsTO;

import java.util.List;

public interface TransactionService {

    List<TransactionsTO> getAllTransaction()throws TransactionsException;

    TransactionsTO getTransactionById(int transactionId)throws  TransactionsException;

    TransactionsTO getTransactionByAccountId(int accountId)throws TransactionsException;

}
