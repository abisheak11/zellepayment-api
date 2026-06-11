package com.zellepayment.services;

import com.zellepayment.entity.BankAccounts;
import com.zellepayment.exception.BankAccountsException;
import com.zellepayment.model.BankAccountsTO;

import java.util.List;

public interface BankAccountService {

    List<BankAccountsTO> getAccounts()throws BankAccountsException;

    BankAccountsTO getAccountByID(int id )throws BankAccountsException;

    BankAccountsTO getAccountByAccountNumber(String accountNumber )throws BankAccountsException;

}
