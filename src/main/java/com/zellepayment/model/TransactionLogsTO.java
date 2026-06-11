package com.zellepayment.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TransactionLogsTO {
    private int logId;

    private String status;

    private String message;

    private LocalDateTime createdAt;

    private TransactionsTO transactionsTO;
}
