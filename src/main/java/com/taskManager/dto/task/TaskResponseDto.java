package com.taskManager.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskResponseDto
{
	private long id;
	private String message;
	private String description;
	private AssignedUserDto createdFor;
}
