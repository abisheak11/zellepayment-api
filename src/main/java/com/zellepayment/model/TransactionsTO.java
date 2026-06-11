package com.zellepayment.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Getter
@Setter
public class TransactionsTO {

    private int transactionId;
//
//    private int senderId;
//
//    private int receiverId;

    private int senderAccountId;

    private int receiverAccountId;

    private double amount;

    private String currency;

    private String status;

    private String referencesNote;

    private LocalDateTime createdAt;

    private LocalDateTime completedAt;

    private List<TransactionLogsTO> transactionLogsTOS;

    private UsersTO SenderIdTO;

    private UsersTO ReceiverIdTO;

}
