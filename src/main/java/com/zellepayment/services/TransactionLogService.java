package com.zellepayment.services;

import com.zellepayment.exception.TransactionLogsException;
import com.zellepayment.model.TransactionLogsTO;
import com.zellepayment.model.TransactionsTO;

import java.util.List;

public interface TransactionLogService {

    List<TransactionLogsTO> getAllTransactionLogs()throws TransactionLogsException;

    TransactionLogsTO getTransactionLogById(int logId)throws TransactionLogsException;
}
