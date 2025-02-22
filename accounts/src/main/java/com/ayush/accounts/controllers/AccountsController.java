package com.ayush.accounts.controllers;

import com.ayush.accounts.constants.AccountsConstants;
import com.ayush.accounts.dto.CustomerDto;
import com.ayush.accounts.dto.ErrorResponseDto;
import com.ayush.accounts.dto.ResponseDto;
import com.ayush.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Tag(
	name = "CRUD REST APIs for Accounts",
	description = "APIs to CREATE, UPDATE, GET, and DELETE Account Details."
)
@Validated
public class AccountsController {

	private IAccountsService iAccountsService;

	@Operation(
		summary = "Create Account REST API",
		description = "REST API to create new customer and account."
	)
	@ApiResponses(
		{
			@ApiResponse(
				responseCode = "201",
				description = "HTTP Status CREATED"
			),
			@ApiResponse(
				responseCode = "500",
				description = "HTTP Internal Server Error",
				content = @Content(
					schema = @Schema(
						implementation = ErrorResponseDto.class
					)
				)
			)
		}

	)
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

		iAccountsService.createAccount(customerDto);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(
				new ResponseDto(
					AccountsConstants.STATUS_201,
					AccountsConstants.MESSAGE_201
				)
			);
	}

	@Operation(
		summary = "Get account REST API",
		description = "REST API to fetch account details."
	)
	@ApiResponse(
		responseCode = "200",
		description = "HTTP Status OK"
	)
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(
		@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits long.")
		@RequestParam String mobileNumber
	) {
		CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK)
			.body(customerDto);
	}

	@Operation(
		summary = "Update account REST API",
		description = "REST API to update account details."
	)
	@ApiResponses(
		{
			@ApiResponse(
				responseCode = "200",
				description = "HTTP Status OK"
			),
			@ApiResponse(
				responseCode = "500",
				description = "HTTP Internal Server Error",
				content = @Content(
					schema = @Schema(
						implementation = ErrorResponseDto.class
					)
				)
			),
			@ApiResponse(
				responseCode = "417",
				description = "Expectation failed."
			)
		}

	)
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdated = iAccountsService.updateAccount(customerDto);

		if (isUpdated) {
			return ResponseEntity
				.status(HttpStatus.OK)
				.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity
				.status(HttpStatus.EXPECTATION_FAILED)
				.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(
		summary = "Delete account REST API",
		description = "REST API to delete account."
	)
	@ApiResponses(
		{
			@ApiResponse(
				responseCode = "200",
				description = "HTTP Status OK"
			),
			@ApiResponse(
				responseCode = "500",
				description = "HTTP Internal Server Error",
				content = @Content(
					schema = @Schema(
						implementation = ErrorResponseDto.class
					)
				)
			),
			@ApiResponse(
				responseCode = "417",
				description = "Expectation failed."
			)
		}

	)
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccount(
		@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits long.")
		@RequestParam String mobileNumber
	) {
		boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

		if (isDeleted) {
			return ResponseEntity
				.status(HttpStatus.OK)
				.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity
				.status(HttpStatus.EXPECTATION_FAILED)
				.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}
	}
}
