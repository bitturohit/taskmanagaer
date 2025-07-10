package com.taskManager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskManager.dto.TaskRequestDto;
import com.taskManager.dto.TaskResponseDto;
import com.taskManager.exceptions.ResourceNotFound;
import com.taskManager.model.Task;
import com.taskManager.model.User;
import com.taskManager.repository.TaskRepository;
import com.taskManager.repository.UserRepository;
import com.taskManager.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService
{
	private final UserRepository ur;
	private final TaskRepository tr;

	@Override
	public TaskResponseDto createTask(TaskRequestDto taskDto)
	{
		User user = ur.findById(taskDto.getUserId())
				.orElseThrow(() -> new ResourceNotFound(
						"User not found with id: " + taskDto.getUserId()));

		Task task = Task.builder()
				.message(taskDto.getMessage())
				.description(taskDto.getDescription())
				.createdFor(user)
				.build();

		Task saved = tr.save(task);

		return TaskResponseDto.builder()
				.id(saved.getId())
				.message(saved.getMessage())
				.description(saved.getDescription())
				.createdFor(saved.getCreatedFor().getName())
				.build();
	}

	@Override
	public void deleteTask(long id)
	{
		Task task = tr.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Task not found with id: " + id));

		tr.delete(task);
	}

	@Override
	public List<TaskResponseDto> getAllTasks()
	{
		return tr.findAll()
				.stream()
				.map(task -> TaskResponseDto.builder()
						.id(task.getId())
						.message(task.getMessage())
						.description(task.getDescription())
						.createdFor(task.getCreatedFor().getName())
						.build())
				.toList();
	}

}
