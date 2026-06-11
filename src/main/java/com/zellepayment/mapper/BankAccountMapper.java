package com.zellepayment.mapper;

import com.zellepayment.entity.BankAccounts;
import com.zellepayment.entity.Users;
import com.zellepayment.entity.ZelleAlias;
import com.zellepayment.model.BankAccountsTO;
import com.zellepayment.model.UsersTO;
import com.zellepayment.model.ZelleAliasTO;

import java.util.List;

public class BankAccountMapper {
    public static BankAccountsTO getBankMapper(BankAccounts bankAccounts){
        BankAccountsTO bankAccountsTO = new BankAccountsTO();
        bankAccountsTO.setAccountId(bankAccounts.getAccountId());
        bankAccountsTO.setBankName(bankAccounts.getBankName());
        bankAccountsTO.setAccountNumber(bankAccounts.getAccountNumber());
        bankAccountsTO.setIfscCode(bankAccounts.getIfscCode());
        bankAccountsTO.setIsPrimary(bankAccounts.getIsPrimary());
        bankAccountsTO.setCreatedAt(bankAccounts.getCreatedAt());
        List<ZelleAliasTO>zelleAliasTOS=bankAccounts.getZelleAliases().stream().map(zell->{
            ZelleAliasTO zelleAliasTO = new ZelleAliasTO();
            zelleAliasTO.setAliasId(zell.getAliasId());
            zelleAliasTO.setAliasType(zell.getAliasType());
            zelleAliasTO.setAliasValue(zell.getAliasValue());
            zelleAliasTO.setCreatedAt(zell.getCreatedAt());
            return zelleAliasTO;
        }).toList();
        bankAccountsTO.setZelleAliasTOS(zelleAliasTOS);
        return bankAccountsTO;
    }
}
