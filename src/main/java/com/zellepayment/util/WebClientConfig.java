package com.zellepayment.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${customerprofile.url}")
    private String customerprofile;
    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl(customerprofile).build();
    }

}
