package com.zellepayment.repository;

import com.zellepayment.entity.TransactionLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLogsRepository extends JpaRepository <TransactionLogs,Integer>{
}
