package com.zellepayment.repository;

import com.zellepayment.entity.BankAccounts;
import com.zellepayment.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {
    Transactions findBySenderAccountId(int accountId);
}
