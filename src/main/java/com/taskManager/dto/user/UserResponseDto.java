package com.taskManager.dto.user;

import com.taskManager.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto
{
	private int id;
	private String name;
	private String email;
	private Role role;
}
