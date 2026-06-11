package com.zellepayment.services;

import com.zellepayment.exception.CustomerProfileException;
import com.zellepayment.model.CustomerProfile;

import java.util.List;

public interface CustomerProfileService {

    List<CustomerProfile>getAllCustomerProfile()throws CustomerProfileException;

}
