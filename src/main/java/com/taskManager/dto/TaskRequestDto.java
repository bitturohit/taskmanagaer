package com.taskManager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDto
{
	@NotBlank(message = "message should not be blank")
	private String message;

	@NotBlank(message = "description should not be blank")
	private String description;

	private int userId;
}
