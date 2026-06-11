package com.zellepayment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transactions_seq", allocationSize = 1)
    @Column(name = "TRANSACTION_ID")
    private int transactionId;

    @Column(name = "SENDER_ACCOUNT_ID")
    private int senderAccountId;

    @Column(name = "RECEIVER_ACCOUNT_ID")
    private int receiverAccountId;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REFERENCE_NOTE")
    private String referenceNote;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "COMPLETED_AT")
    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "transactions",cascade = CascadeType.ALL)
    private List<TransactionLogs> transactionLogs;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "SENDER_USER_ID")
    private Users userSender;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "RECEIVER_USER_ID")
    private Users userReceiver; // ✅ removed dot
}