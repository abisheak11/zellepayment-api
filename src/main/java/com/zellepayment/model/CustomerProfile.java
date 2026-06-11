package com.zellepayment.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerProfile {

    private String id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String address;
}
