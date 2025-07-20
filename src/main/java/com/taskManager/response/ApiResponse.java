package com.taskManager.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T>
{
	private boolean success;
	private String message;
	private T data;
	private LocalDateTime timestamp;

	public static <T> ApiResponse<T> success(T data, String message)
	{
		return ApiResponse.<T>builder()
				.success(true)
				.message(message)
				.data(data)
				.timestamp(LocalDateTime.now())
				.build();
	}

	public static <T> ApiResponse<T> failure(String message)
	{
		return ApiResponse.<T>builder()
				.success(false)
				.message(message)
				.data(null)
				.timestamp(LocalDateTime.now())
				.build();
	}
}
