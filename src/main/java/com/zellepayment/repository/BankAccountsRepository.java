package com.zellepayment.repository;

import com.zellepayment.entity.BankAccounts;
import com.zellepayment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountsRepository extends JpaRepository<BankAccounts,Integer> {

    BankAccounts findByAccountNumber(String accountNumber);
}
