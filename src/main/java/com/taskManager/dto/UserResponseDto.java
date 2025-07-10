package com.taskManager.dto;

import com.taskManager.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserResponseDto
{
	private int id;
	private String name;
	private String email;
	private Role role;
}
