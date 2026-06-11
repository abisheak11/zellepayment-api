package com.zellepayment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ZELLE_ALIAS")
@Getter
@Setter
public class ZelleAlias {

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "zelle_alias")
    @SequenceGenerator(name = "zelle_alias",sequenceName = "zelle_alias_seq",allocationSize = 1)
    @Id
    @Column(name = "ALIAS_ID")
    private int aliasId;

    //@Column(name = "USER_ID")
   // public Integer userId;

    @Column(name = "ALIAS_TYPE")
    private String aliasType;

    @Column(name = "ALIAS_VALUE")
    private String aliasValue;

    //@Column(name = "ACCOUNT_ID")
    //public Integer accountId;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Users userAlias;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    private BankAccounts bankAccounts;
}
