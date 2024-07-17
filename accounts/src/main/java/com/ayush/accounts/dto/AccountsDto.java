package com.ayush.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
	name = "Accounts",
	description = "Account details of the customer."
)
public class AccountsDto {

	@Schema(
		name = "Account Number",
		example = "3746373891"
	)
	@NotEmpty(message = "Account number cannot be null or empty.")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits long.")
	private Long accountNumber;

	@Schema(
		name = "Account type",
		example = "Savings"
	)
	@NotEmpty(message = "Account type cannot be null or empty.")
	private String accountType;

	@Schema(
		name = "Branch address",
		example = "172 Los Angeles, California"
	)
	@NotEmpty(message = "Branch address cannot be null or empty.")
	private String branchAddress;
}
