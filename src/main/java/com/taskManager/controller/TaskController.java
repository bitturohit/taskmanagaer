package com.taskManager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.dto.TaskRequestDto;
import com.taskManager.dto.TaskResponseDto;
import com.taskManager.response.ResponseApi;
import com.taskManager.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController
{
	private final TaskService taskService;

	@PostMapping
	public ResponseEntity<ResponseApi<TaskResponseDto>> create(
			@Valid @RequestBody TaskRequestDto taskDto)
	{
		TaskResponseDto task = taskService.createTask(taskDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseApi<>("New task created", task, true));
	}

	@GetMapping
	public ResponseEntity<ResponseApi<List<TaskResponseDto>>> getAllTasks()
	{
		List<TaskResponseDto> tasks = taskService.getAllTasks();

		return ResponseEntity.ok(new ResponseApi<>("Fetched all tasks", tasks, true));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseApi<String>> delete(@PathVariable long id)
	{
		taskService.deleteTask(id);

		return ResponseEntity
				.ok(new ResponseApi<>("Task with ID " + id + " deleted successfully", null, true));
	}
}
