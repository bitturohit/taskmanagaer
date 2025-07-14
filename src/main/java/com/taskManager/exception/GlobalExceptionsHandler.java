package com.taskManager.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taskManager.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionsHandler
{
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorResponse> handleResourceError(ResourceNotFound e)
	{
		ErrorResponse error = new ErrorResponse(
				"Resource not found",
				e.getMessage(),
				HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handlevalidationerrors(MethodArgumentNotValidException e)
	{
		Map<String, String> errors = new HashMap<>();

		e.getBindingResult().getFieldErrors().forEach((error) -> {
			String fieldName = error.getField();
			String errMsg = error.getDefaultMessage();
			errors.put(fieldName, errMsg);
		});

		ErrorResponse error = new ErrorResponse(
				"Validation failed",
				errors,
				HttpStatus.BAD_REQUEST.value());

		return ResponseEntity.badRequest().body(error);
	}
}
