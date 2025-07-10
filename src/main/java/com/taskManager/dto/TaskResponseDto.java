package com.taskManager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TaskResponseDto
{
	private long id;
	private String message;
	private String description;
	private String createdFor;
}
