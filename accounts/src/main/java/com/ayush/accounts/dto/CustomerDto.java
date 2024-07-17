package com.ayush.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
	name = "Customer",
	description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

	@Schema(
		description = "Name of the customer",
		example = "John Doe"
	)
	@NotEmpty(message = "Name cannot be null or empty.")
	@Size(min = 5, max = 30, message = "Length of customer name should be between 5 and 30.")
	private String name;

	@Schema(
		description = "Email id of the customer",
		example = "xyz@example.com"
	)
	@NotEmpty(message = "Email cannot be null or empty.")
	@Email(message = "Please provide a valid email address")
	private String email;

	@Schema(
		description = "Mobile number of the customer",
		example = "9999999999"
	)
	@NotEmpty(message = "Mobile number cannot be empty.")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits long.")
	private String mobileNumber;

	private AccountsDto accountsDto;
}
