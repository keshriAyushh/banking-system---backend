package com.ayush.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

	@NotEmpty(message = "Name cannot be null or empty.")
	@Size(min = 5, max = 30, message = "Length of customer name should be between 5 and 30.")
	private String name;

	@NotEmpty(message = "Email cannot be null or empty.")
	@Email(message = "Please provide a valid email address")
	private String email;

	@NotEmpty(message = "Mobile number cannot be empty.")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits long.")
	private String mobileNumber;

	private AccountsDto accountsDto;
}
