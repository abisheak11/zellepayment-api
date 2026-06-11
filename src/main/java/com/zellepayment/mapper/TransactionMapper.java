package com.zellepayment.mapper;

import com.zellepayment.entity.Transactions;
import com.zellepayment.model.TransactionsTO;

public class TransactionMapper {

    public static TransactionsTO getTransactionMapper(Transactions transactions){
       TransactionsTO transactionsTO = new TransactionsTO();
       transactionsTO.setTransactionId(transactions.getTransactionId());
       transactionsTO.setReceiverAccountId(transactions.getReceiverAccountId());
       transactionsTO.setSenderAccountId(transactions.getSenderAccountId());
       transactionsTO.setAmount(transactions.getAmount());
       transactionsTO.setReferencesNote(transactionsTO.getReferencesNote());
       transactionsTO.setCurrency(transactions.getCurrency());
       transactionsTO.setCreatedAt(transactions.getCreatedAt());
       transactionsTO.setStatus(transactions.getStatus());
       return transactionsTO;
    }
}
