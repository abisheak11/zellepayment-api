package com.zellepayment.controller;

import com.zellepayment.exception.BankAccountsException;
import com.zellepayment.exception.UsersException;
import com.zellepayment.model.BankAccountsTO;
import com.zellepayment.model.UsersTO;
import com.zellepayment.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/bankaccounts")
public class BankAccountController {

    @Autowired
    public BankAccountService bankAccountService;


    @GetMapping
    public ResponseEntity <List<BankAccountsTO>> getAllBankAccounts(){
        log.info("fetching all BankAccount details....");
        List<BankAccountsTO>bankAccounts = null;
        try {
           bankAccounts = bankAccountService.getAccounts();
        } catch (BankAccountsException e) {
            log.error("Error Fetching bankAccount detail:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching BankAccount details ");
        }
        log.info("Successfully fetched {} bankAccounts details",bankAccounts.size());
        return ResponseEntity.ok(bankAccounts);
    }
    @GetMapping({"/{Id}"})
    public ResponseEntity<BankAccountsTO>getAccountById(@PathVariable("Id")int accountId){
        log.info("fetching all BankAccount details by Id....");
        BankAccountsTO bankAccount = null;
        try {
            bankAccount = bankAccountService.getAccountByID(accountId);
        } catch (BankAccountsException e) {
            log.error("Error Fetching bankAccount detail ById:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching BankAccount details ById ");
        }
        log.info("Successfully fetched bankAccounts details ById ");
        return ResponseEntity.ok(bankAccount);
    }
    @GetMapping({"/accountNo"})
    public ResponseEntity<BankAccountsTO>getAccountByAccountNumber(@RequestParam("accountNo")String accountNumber){
        log.info("fetching all BankAccount details by AccountNumber....");
        BankAccountsTO bankAccount = null;
        try {
            bankAccount = bankAccountService.getAccountByAccountNumber(accountNumber);
        } catch (BankAccountsException e) {
            log.error("Error Fetching bankAccount detail ByAccountNumber:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching BankAccount details ByAccountNumber ");
        }
        log.info("Successfully fetched bankAccounts details ByAccountNumber ");
        return ResponseEntity.ok(bankAccount);
    }
}
