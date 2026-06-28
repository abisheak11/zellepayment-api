package com.zellepayment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="USERS")
@Getter
@Setter
public class Users {
//user name Abisheak this is the changeee
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_seq", allocationSize = 1)
    @Column(name="USER_ID")
    private int userId;

    @Column(name="FULL_NAME")
    private String fullName;

    @Column(name="EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "STATUS")
    private String status;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<BankAccounts> bankAccounts;

    @OneToMany(mappedBy = "userAlias",cascade = CascadeType.ALL)
    private List<ZelleAlias> zelleAlias;

    @OneToMany(mappedBy = "userSender",cascade = CascadeType.ALL)
    private List<Transactions> senderTransactions;

    @OneToMany(mappedBy = "userReceiver",cascade = CascadeType.ALL)
    private List<Transactions> receiverTransactions;
}
