package com.zellepayment.controller;

import com.zellepayment.exception.TransactionLogsException;
import com.zellepayment.exception.TransactionsException;
import com.zellepayment.model.TransactionLogsTO;
import com.zellepayment.model.TransactionsTO;
import com.zellepayment.services.TransactionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/transactionslogs")
public class TransactionLogController {

    @Autowired
    public TransactionLogService transactionLogService;

    @GetMapping
    public ResponseEntity<List<TransactionLogsTO>> getAllTransactionLogs() {
        log.info("fetching all transactionLogs...");
        List<TransactionLogsTO> transactionsLogs = null;
        try {
            transactionsLogs = transactionLogService.getAllTransactionLogs();

        } catch (TransactionLogsException e) {
            log.error("error fetching transactionLogs details..:{}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching transactionLogs details..");
        }
        log.info("Successfully fetched {} transactionLogs details", transactionsLogs.size());
        return ResponseEntity.ok(transactionsLogs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionLogsTO>getTransactionById(@PathVariable("id") int logId) {
        log.info("fetching transactionLog By Id...");
        TransactionLogsTO transactions = null;
        try {
            transactions = transactionLogService.getTransactionLogById(logId);

        } catch (TransactionLogsException e) {
            log.error("error fetching transactionLog details By ID..:{}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching transactionLog details By Id..");
        }
        log.info("Successfully fetched transactionLog details By Id");
        return ResponseEntity.ok(transactions);
    }
}
