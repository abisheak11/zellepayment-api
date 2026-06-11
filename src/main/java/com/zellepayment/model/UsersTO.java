package com.zellepayment.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UsersTO {
    private int userId;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String passwordHash;

    private LocalDateTime createdAt;

    private String status;

    private List<BankAccountsTO> bankAccountsTO;

    private List<ZelleAliasTO> zelleAliasTO;

    private List<TransactionsTO> senderTransactionsTO;

    private List<TransactionsTO> receiverTransactionsTO;

}

