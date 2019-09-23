package com.java.app.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.java.app.loan")
public class LoanApplication {

	public static void main(String[] args) {
		// run application
		SpringApplication.run(LoanApplication.class, args);
	}
}
