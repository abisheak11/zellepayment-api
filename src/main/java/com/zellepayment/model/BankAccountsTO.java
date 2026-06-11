package com.zellepayment.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class BankAccountsTO {

    private int accountId;

    private String bankName;

    private String accountNumber;

    private String ifscCode;

    private int isPrimary;

    private LocalDateTime createdAt;

    private UsersTO usersTO;

    private List<ZelleAliasTO> zelleAliasTOS;
}
