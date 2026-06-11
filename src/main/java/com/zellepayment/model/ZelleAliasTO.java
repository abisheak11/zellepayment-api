package com.zellepayment.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ZelleAliasTO {

    private int aliasId;

    private String aliasType;

    private String aliasValue;

    private LocalDateTime createdAt;

    private UsersTO usersTO;

    private BankAccountsTO bankAccountsTO;
}
