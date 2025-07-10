package com.taskManager.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taskManager.response.ResponseApi;

@RestControllerAdvice
public class GlobalExceptionsHandler
{
	@ExceptionHandler(exception = ResourceNotFound.class)
	public ResponseEntity<ResponseApi<String>> handleResourceError(ResourceNotFound e)
	{
		return ResponseEntity.badRequest()
				.body(new ResponseApi<>("Resource not found", e.getMessage(), false));
	}

	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseApi<Map<String, String>>> handlevalidationerrors(
			MethodArgumentNotValidException e)
	{
		Map<String, String> errors = new HashMap<>();

		e.getBindingResult()
				.getFieldErrors()
				.forEach((error) -> errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.badRequest()
				.body(new ResponseApi<>("Validation failed", errors, false));
	}
}
