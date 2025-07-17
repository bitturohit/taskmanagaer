package com.taskManager.response;

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
	private T payload;

	public static <T> ApiResponse<T> success(T data, String message)
	{
		return new ApiResponse<>(true, message, data);
	}
}
