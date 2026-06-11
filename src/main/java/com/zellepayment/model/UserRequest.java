package com.zellepayment.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String fullName;

    private String email;

    private String phoneNumber;

    private String passwordHash;

    private LocalDateTime createdAt;

    private String status;
}
