package com.zellepayment.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UserRequestUpdate {

    @Min(1)
    private int userId;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String passwordHash;

    private LocalDateTime createdAt;

    private String status;
}
