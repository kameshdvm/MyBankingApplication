package com.mybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.mybank.database")
public class MyBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBankingApplication.class, args);
	}

}
