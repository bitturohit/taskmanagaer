package com.taskManager.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AssignedUserDto
{
	private int id;
	private String name;
}
