package com.zellepayment.mapper;
import com.zellepayment.entity.Users;
import com.zellepayment.model.BankAccountsTO;
import com.zellepayment.model.TransactionsTO;
import com.zellepayment.model.UsersTO;
import com.zellepayment.model.ZelleAliasTO;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class UserMapper {

    public static UsersTO getUserMapper(Users users){
       UsersTO usersTO = new UsersTO();
       usersTO.setUserId(users.getUserId());
       usersTO.setFullName(users.getFullName());
       usersTO.setEmail(users.getEmail());
       usersTO.setPhoneNumber(users.getPhoneNumber());
       usersTO.setPasswordHash(users.getPasswordHash());
       usersTO.setCreatedAt(users.getCreatedAt());
       usersTO.setStatus(users.getStatus());
        List<ZelleAliasTO> zelleAliasTO = users.getZelleAlias().stream().map(user->{
          ZelleAliasTO zelleAliasTO1= new ZelleAliasTO();
          zelleAliasTO1.setAliasId(user.getAliasId());
          zelleAliasTO1.setAliasValue(user.getAliasValue());
          zelleAliasTO1.setAliasType(user.getAliasType());
          return zelleAliasTO1;
        }).toList();
       usersTO.setZelleAliasTO(zelleAliasTO);
       List<BankAccountsTO>bankAccountsTOS = users.getBankAccounts().stream().map(userAc->{
           BankAccountsTO bankAccountsTO = new BankAccountsTO();
           bankAccountsTO.setAccountId(userAc.getAccountId());
           bankAccountsTO.setBankName(userAc.getBankName());
           bankAccountsTO.setAccountNumber(userAc.getAccountNumber());
           bankAccountsTO.setIfscCode(userAc.getIfscCode());
           bankAccountsTO.setIsPrimary(userAc.getIsPrimary());
           bankAccountsTO.setCreatedAt(userAc.getCreatedAt());
           return bankAccountsTO;
       }).toList();
       usersTO.setBankAccountsTO(bankAccountsTOS);

        if (!CollectionUtils.isEmpty(users.getSenderTransactions())) {
            List<TransactionsTO> senderTx = users.getSenderTransactions().stream().map(tx -> {
                TransactionsTO t = new TransactionsTO();
                t.setTransactionId(tx.getTransactionId());
                t.setReceiverAccountId(tx.getReceiverAccountId());
                t.setSenderAccountId(tx.getSenderAccountId());
                t.setAmount(tx.getAmount());
                t.setCurrency(tx.getCurrency());
                t.setStatus(tx.getStatus());
                t.setReferencesNote(tx.getReferenceNote());
                t.setCreatedAt(tx.getCreatedAt());
                t.setCompletedAt(tx.getCompletedAt());
                return t;
            }).toList();
            usersTO.setSenderTransactionsTO(senderTx);
        }

        //Receiver Transactions
        if (!CollectionUtils.isEmpty(users.getReceiverTransactions())) {
            List<TransactionsTO> receiverTx = users.getReceiverTransactions().stream().map(tx -> {
                TransactionsTO t = new TransactionsTO();
                t.setTransactionId(tx.getTransactionId());
                t.setReceiverAccountId(tx.getReceiverAccountId());
                t.setSenderAccountId(tx.getSenderAccountId());
                t.setAmount(tx.getAmount());
                t.setCurrency(tx.getCurrency());
                t.setStatus(tx.getStatus());
                t.setReferencesNote(tx.getReferenceNote());
                t.setCreatedAt(tx.getCreatedAt());
                t.setCompletedAt(tx.getCompletedAt());
                return t;
            }).toList();
            usersTO.setReceiverTransactionsTO(receiverTx);
        }

       return usersTO;
    }
}
