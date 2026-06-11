package com.zellepayment.services;

import com.zellepayment.entity.Transactions;
import com.zellepayment.entity.Users;
import com.zellepayment.exception.TransactionsException;
import com.zellepayment.mapper.TransactionMapper;
import com.zellepayment.model.TransactionLogsTO;
import com.zellepayment.model.TransactionsTO;
import com.zellepayment.repository.TransactionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServicesImpl implements TransactionService {

    @Autowired
    public TransactionsRepository transactionsRepository;

    @Override
    public List<TransactionsTO> getAllTransaction() throws TransactionsException {
        log.info("inside the TransactionServicesImpl.getAllTransaction");

        //transactionsRepository it extends the JPA Repository ,so it find the all Transaction by using this findAll() method
        //the findAll() method from JPARepository
        List<Transactions>transactions = transactionsRepository.findAll();

        //Here we are checking is the collection is empty rest of the code will not execute only if condition will run
        if(CollectionUtils.isEmpty(transactions)){
            log.info("Here no Transactions Founded");
            throw new TransactionsException("Here no Transactions Founded");
        }

        // Here we have to convert List into TO that's the here we are using the stream
        //why we are converting? otherwise we can't return List, that's the reason we are converting List into TO
        return transactions.stream().map(transaction ->{

            //creating object for transactionTO for set the value
            TransactionsTO transactionsTO = new TransactionsTO();

            //setting the value for transactionTO by get value from entity class transaction
            transactionsTO.setTransactionId(transaction.getTransactionId());
            transactionsTO.setReceiverAccountId(transaction.getReceiverAccountId());
            transactionsTO.setSenderAccountId(transaction.getSenderAccountId());
            transactionsTO.setAmount(transaction.getAmount());
            transactionsTO.setCurrency(transaction.getCurrency());
            transactionsTO.setStatus(transaction.getStatus());
            transactionsTO.setReferencesNote(transaction.getReferenceNote());
            transactionsTO.setCreatedAt(transaction.getCreatedAt());
            transactionsTO.setCompletedAt(transaction.getCompletedAt());

            List<TransactionLogsTO>transactionLogsTO= transaction.getTransactionLogs().stream().map(tran->{
                TransactionLogsTO trans = new TransactionLogsTO();
                trans.setLogId(tran.getLogId());
                trans.setStatus(tran.getStatus());
                trans.setMessage(tran.getMessage());
                trans.setCreatedAt(tran.getCreatedAt());
                return trans;
            }).toList();
            transactionsTO.setTransactionLogsTOS(transactionLogsTO);

//            transactionsTO.setSenderIdTO(transaction.getSenderAccountId());
            Users user = new Users();



            //returning TransactionTO
            return transactionsTO;
            //converting stream into list
        } ).collect(Collectors.toList());
    }

    @Override
    public TransactionsTO getTransactionById(int transactionId) throws TransactionsException {
        log.info("inside the TransactionServicesImpl.getTransactionById");

        return transactionsRepository.findById(transactionId).map(TransactionMapper::getTransactionMapper).orElseThrow
                (() -> {
                    log.info("Transaction Details not found for this transactionId:{}", transactionId);
                    return new TransactionException("Transaction not found for this transactionId");
                });

    }

    @Override
    public TransactionsTO getTransactionByAccountId(int accountId) throws TransactionsException {
        Transactions transactions = transactionsRepository.findBySenderAccountId(accountId);
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
