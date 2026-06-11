package com.zellepayment;

import org.springframework.boot.SpringApplication;

public class TestZellepaymentApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(ZellepaymentApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
