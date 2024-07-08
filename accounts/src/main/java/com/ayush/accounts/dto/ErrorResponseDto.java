package com.ayush.accounts.dto;

import com.ayush.accounts.constants.AccountsConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Schema(
	name = "Error Response",
	description = "Schema to hold error response information."
)
@AllArgsConstructor
public class ErrorResponseDto {
	@Schema(description = "API Path invoked.", example = "/api/fetch")
	private String apiPath;

	@Schema(description = "Error code for the error encountered.", example = AccountsConstants.STATUS_500)
	private HttpStatus errorCode;

	@Schema(description = "Error message for the error encountered.", example = AccountsConstants.MESSAGE_500)
	private String errorMessage;

	@Schema(description = "Time of error occurrence.", example = "2024-07-03T20:29:30.3203Z")
	private LocalDateTime errorTime;
}
