package com.zellepayment.services;

import com.zellepayment.exception.CustomerProfileException;
import com.zellepayment.model.CustomerProfile;
import com.zellepayment.util.CustomerProfileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
@Slf4j
public class CustomerProfileImpl implements CustomerProfileService{

    @Autowired
    private CustomerProfileUtil customerProfileUtil;

    @Override
    public List<CustomerProfile> getAllCustomerProfile() throws CustomerProfileException{
        log.info("inside the CustomerProfileImpl.getAllCustomerProfile");
        List<CustomerProfile> customerProfileList=customerProfileUtil.getCustomers();

        if (CollectionUtils.isEmpty(customerProfileList)){
            throw  new CustomerProfileException("customer details not founded");
        }
        log.info("customer profile successfully fetched");
        return customerProfileList;
    }
}
