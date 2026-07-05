package com.zellepayment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "BANK_ACCOUNTS")
@Getter
@Setter
public class BankAccounts {

    @GeneratedValue(strategy =GenerationType.SEQUENCE,generator = "bank_acc")
    @SequenceGenerator(name = "bank_acc",sequenceName = "bank_acc_seq",allocationSize = 1)
    @Id
    @Column(name = "ACCOUNT_ID")
    private int accountId;

    //@Column(name = "USER_ID")
    //public Integer userId;


    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "IFSC_CODE")
    private String ifscCode;

    @Column(name = "IS_PRIMARY")
    private int isPrimary;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Users user;

    @OneToMany(mappedBy = "bankAccounts",cascade = CascadeType.ALL)
    private List<ZelleAlias> zelleAliases;

}
