package com.taskManager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.dto.task.TaskFilterDto;
import com.taskManager.dto.task.TaskRequestDto;
import com.taskManager.dto.task.TaskResponseDto;
import com.taskManager.response.ApiResponse;
import com.taskManager.response.PageResponse;
import com.taskManager.response.PageResponseBuilder;
import com.taskManager.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController
{
	private final TaskService taskService;

	@GetMapping
	public ResponseEntity<ApiResponse<PageResponse<TaskResponseDto>>> getAllTasks(
			Pageable pageable)
	{

		Page<TaskResponseDto> tasks = taskService.getAllTasks(pageable);
		PageResponse<TaskResponseDto> metaWrapped = PageResponseBuilder.build(tasks);

		return ResponseEntity
				.ok(new ApiResponse<>(true, "Fetched all tasks", metaWrapped));
	}

	@GetMapping("/filter")
	public ResponseEntity<ApiResponse<Page<TaskResponseDto>>> filterTasks(
			@ModelAttribute TaskFilterDto filterDto,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir)
	{
		Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending()
				: Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<TaskResponseDto> filtered = taskService.filterTasks(filterDto, pageable);

		return ResponseEntity.ok(ApiResponse.success(filtered, "Filtered tasks"));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<TaskResponseDto>> create(
			@Valid @RequestBody TaskRequestDto taskDto)
	{
		TaskResponseDto task = taskService.createTask(taskDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(true, "New task created", task));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id)
	{
		taskService.deleteTask(id);

		return ResponseEntity.ok(new ApiResponse<>(
				true,
				"Task with ID " + id + " deleted successfully",
				null));
	}
}
