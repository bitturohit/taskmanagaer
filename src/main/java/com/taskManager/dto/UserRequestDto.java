package com.taskManager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto
{
	@NotBlank(message = "Name should not be empty")
	@Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$",
			message = "Name should contain only alphabets and spaces")
	private String name;

	@Email(message = "Enter a valid email")
	private String email;

}
