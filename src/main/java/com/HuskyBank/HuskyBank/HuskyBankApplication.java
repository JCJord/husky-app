package com.HuskyBank.HuskyBank;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HuskyBankApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

		// Access environment variables
		String dbUrl = dotenv.get("DB_URL");
		String dbUsername = dotenv.get("DB_USERNAME");
		String dbPassword = dotenv.get("DB_PASSWORD");

		// Set environment variables
		System.setProperty("DB_URL", dbUrl);
		System.setProperty("DB_USERNAME", dbUsername);
		System.setProperty("DB_PASSWORD", dbPassword);
		SpringApplication.run(HuskyBankApplication.class, args);
	}

}
