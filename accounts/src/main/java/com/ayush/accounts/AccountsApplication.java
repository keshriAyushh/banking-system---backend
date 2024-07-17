package com.ayush.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
	info = @Info(
		title = "Accounts microservice REST API Documentation",
		description = "Documentation for account related REST APIs",
		version = "v1",
		contact = @Contact(
			name = "Ayush Keshri",
			email = "ayushkeshri275@gmail.com"
		),
		license = @License(
			name = "Apache 2.0",
			url = ""
		)
	)
)
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}