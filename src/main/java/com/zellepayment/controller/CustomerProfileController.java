package com.zellepayment.controller;

import com.zellepayment.exception.CustomerProfileException;
import com.zellepayment.model.CustomerProfile;
import com.zellepayment.services.CustomerProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/customers")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService customerProfileService;

    @GetMapping()
    public ResponseEntity<List<CustomerProfile>>getAllCustomer() throws CustomerProfileException {
        log.info("inside the CustomerProfileController.getAllCustomer");
        List<CustomerProfile>customerProfileList = customerProfileService.getAllCustomerProfile();

        log.info("CustomerProfile fetched successfully:{}",customerProfileList.size());

        return ResponseEntity.ok(customerProfileList);
    }
}
