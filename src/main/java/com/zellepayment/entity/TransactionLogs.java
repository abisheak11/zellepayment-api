package com.zellepayment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "TRANSACTION_LOGS")
@Getter
@Setter
public class TransactionLogs {

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "transaction_logs")
    @SequenceGenerator(name = "transaction_logs",sequenceName = "transaction_logs_seq",allocationSize = 1)
    @Id
    @Column(name = "LOG_ID")
    private int logId;

    //@Column(name = "TRANSACTION_ID")
    //public Integer transactionId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "TRANSACTION_ID")
    private Transactions transactions;
}
