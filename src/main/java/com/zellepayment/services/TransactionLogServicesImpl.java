package com.zellepayment.services;

import com.zellepayment.entity.TransactionLogs;
import com.zellepayment.exception.TransactionLogsException;
import com.zellepayment.mapper.TransactionLogMapper;
import com.zellepayment.mapper.TransactionMapper;
import com.zellepayment.model.TransactionLogsTO;
import com.zellepayment.model.TransactionsTO;
import com.zellepayment.repository.TransactionLogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionLogServicesImpl implements TransactionLogService{

    @Autowired
    public TransactionLogsRepository transactionLogsRepository;

    @Override
    public List<TransactionLogsTO> getAllTransactionLogs() throws TransactionLogsException {
        log.info("inside the TransactionLog.getAllTransactionLogs");
        List<TransactionLogs>transactionLogs = transactionLogsRepository.findAll();
        if (CollectionUtils.isEmpty(transactionLogs)){
            log.info("Here NoTransactionLog founded");
            throw new TransactionLogsException("Here NoTransactionLog founded");
        }
        return transactionLogs.stream().map(transactionLog -> {
            TransactionLogsTO transactionLogsTO = new TransactionLogsTO();
            transactionLogsTO.setLogId(transactionLog.getLogId());
            transactionLogsTO.setStatus(transactionLog.getStatus());
            transactionLogsTO.setMessage(transactionLog.getMessage());
            transactionLogsTO.setCreatedAt(transactionLog.getCreatedAt());
//            transactionLogsTO.setTransactionsTO(transactionLog.getTransactions());
            return transactionLogsTO;
        }).collect(Collectors.toList());

    }

    @Override
    public TransactionLogsTO getTransactionLogById(int logId) throws TransactionLogsException {
        log.info("inside the TransactionLog.getTransactionLogsById");
        return transactionLogsRepository.findById(logId).map(TransactionLogMapper::getTransactionLogMapper).orElseThrow
                (() -> {
                    log.info("Transaction Details not found for this transactionId:{}", logId);
                    return new TransactionLogsException("Transaction not found for this transactionId");
                });
    }
}
