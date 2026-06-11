package com.zellepayment.controller;

import com.zellepayment.exception.TransactionsException;
import com.zellepayment.model.TransactionsTO;
import com.zellepayment.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    public TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionsTO>> getAllTransaction(){
        log.info("fetching all transaction...");
        List<TransactionsTO>transactions = null;
        try {
            transactions=transactionService.getAllTransaction();

        } catch (TransactionsException e) {
            log.error("error fetching transaction details..:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching transaction details..");
        }
        log.info("Successfully fetched {} transaction details",transactions.size());
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionsTO>getTransactionById(@PathVariable("id") int transactionId){
        log.info("fetching transaction By Id...");
        TransactionsTO transactions = null;
        try {
            transactions=transactionService.getTransactionById(transactionId);

        } catch (TransactionsException e) {
            log.error("error fetching transaction details By ID..:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching transaction details By Id..");
        }
        log.info("Successfully fetched transaction details By Id");
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/accountId")
    public ResponseEntity<TransactionsTO>getTransactionByAccountId(@RequestParam("accountId") int accountId){
        log.info("fetching transaction By AccountId...");
        TransactionsTO transactions = null;
        try {
            transactions=transactionService.getTransactionByAccountId(accountId);

        } catch (TransactionsException e) {
            log.error("error fetching transaction details By AccountID..:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching transaction details By AccountId..");
        }
        log.info("Successfully fetched transaction details By AccountId");
        return ResponseEntity.ok(transactions);
    }

}
