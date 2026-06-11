package com.zellepayment.mapper;

import com.zellepayment.entity.TransactionLogs;
import com.zellepayment.model.TransactionLogsTO;

public class TransactionLogMapper {

    public static TransactionLogsTO getTransactionLogMapper(TransactionLogs transactionLogs){
        TransactionLogsTO transactionLogsTO = new TransactionLogsTO();
        transactionLogsTO.setLogId(transactionLogs.getLogId());
        transactionLogsTO.setStatus(transactionLogs.getStatus());
        transactionLogsTO.setMessage(transactionLogs.getMessage());
        transactionLogsTO.setCreatedAt(transactionLogs.getCreatedAt());
        return transactionLogsTO;
    }
}
