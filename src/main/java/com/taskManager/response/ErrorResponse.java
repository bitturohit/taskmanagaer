package com.taskManager.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse
{
	private String error;
	private Object details;
	private int statusCode;
	private LocalDateTime timestamp;

	public ErrorResponse(String error, Object details, int statusCode)
	{
		this.error = error;
		this.details = details;
		this.statusCode = statusCode;
		this.timestamp = LocalDateTime.now();
	}
}
