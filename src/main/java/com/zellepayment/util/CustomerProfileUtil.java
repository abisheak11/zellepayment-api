package com.zellepayment.util;


import com.zellepayment.model.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CustomerProfileUtil {

    @Autowired
    private WebClient webClient;

    public List<CustomerProfile> getCustomers(){
        return webClient.get().retrieve().bodyToFlux(CustomerProfile.class).collectList().block();
    }
}
