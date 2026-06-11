package com.zellepayment.services;

import com.zellepayment.entity.BankAccounts;
import com.zellepayment.exception.BankAccountsException;
import com.zellepayment.mapper.BankAccountMapper;
import com.zellepayment.model.BankAccountsTO;
import com.zellepayment.model.ZelleAliasTO;
import com.zellepayment.repository.BankAccountsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BankAccountServiceImpl implements BankAccountService{

    @Autowired
    public BankAccountsRepository bankAccountsRepository;


    @Override
    public List<BankAccountsTO> getAccounts() throws BankAccountsException {
        log.info("inside the BankAccountImpl.getAccount");
        List<BankAccounts> bankAccounts = bankAccountsRepository.findAll();
        if (CollectionUtils.isEmpty(bankAccounts)) {
            log.error("Bank Account not founded");
            throw new BankAccountsException();
        }
        return bankAccounts.stream().map(bankAccounts1 -> {
            BankAccountsTO bankAccountsTO = new BankAccountsTO();
            bankAccountsTO.setAccountId(bankAccounts1.getAccountId());
            bankAccountsTO.setBankName(bankAccounts1.getBankName());
            bankAccountsTO.setAccountNumber(bankAccounts1.getAccountNumber());
            bankAccountsTO.setIfscCode(bankAccounts1.getIfscCode());
            bankAccountsTO.setIsPrimary(bankAccounts1.getIsPrimary());
            bankAccountsTO.setCreatedAt(bankAccounts1.getCreatedAt());
           List<ZelleAliasTO>zelleAliasTOS= bankAccounts1.getZelleAliases().stream().map(zelleAlias -> {
                ZelleAliasTO zelleAliasTO = new ZelleAliasTO();
                zelleAliasTO.setAliasId(zelleAlias.getAliasId());
                zelleAliasTO.setAliasType(zelleAlias.getAliasType());
                zelleAliasTO.setAliasValue(zelleAlias.getAliasValue());
                zelleAliasTO.setCreatedAt(zelleAlias.getCreatedAt());
                return zelleAliasTO;
            }).toList();
            bankAccountsTO.setZelleAliasTOS(zelleAliasTOS);
            return bankAccountsTO;
        }).collect(Collectors.toList());
    }

    @Override
    public BankAccountsTO getAccountByID(int accountId) throws BankAccountsException {
        log.info("inside the BankAccountImpl.getAccountByID");
       BankAccountsTO bankAccountsTO = bankAccountsRepository.findById(accountId).map(BankAccountMapper::getBankMapper)
               .orElseThrow(()->{
           log.info("BankAccount Details not found for this accountId:{}",accountId);
           return new BankAccountsException("BankAccount not found for this accountId");

       });
       return bankAccountsTO;
    }

    @Override
    public BankAccountsTO getAccountByAccountNumber(String accountNumber) throws BankAccountsException {
        BankAccounts bankAccounts = bankAccountsRepository.findByAccountNumber(accountNumber);

        BankAccountsTO bankAccountsTO = new BankAccountsTO();
        bankAccountsTO.setAccountId(bankAccounts.getAccountId());
        bankAccountsTO.setBankName(bankAccounts.getBankName());
        bankAccountsTO.setAccountNumber(bankAccounts.getAccountNumber());
        bankAccountsTO.setIfscCode(bankAccounts.getIfscCode());
        bankAccountsTO.setIsPrimary(bankAccounts.getIsPrimary());
        bankAccountsTO.setCreatedAt(bankAccounts.getCreatedAt());
        List<ZelleAliasTO>zelleAliasTOS= bankAccounts.getZelleAliases().stream().map(zelleAlias -> {
            ZelleAliasTO zelleAliasTO = new ZelleAliasTO();
            zelleAliasTO.setAliasId(zelleAlias.getAliasId());
            zelleAliasTO.setAliasType(zelleAlias.getAliasType());
            zelleAliasTO.setAliasValue(zelleAlias.getAliasValue());
            zelleAliasTO.setCreatedAt(zelleAlias.getCreatedAt());
            return zelleAliasTO;
        }).toList();
        bankAccountsTO.setZelleAliasTOS(zelleAliasTOS);
        return bankAccountsTO;
    }

}
