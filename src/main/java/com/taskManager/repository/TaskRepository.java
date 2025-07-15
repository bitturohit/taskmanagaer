package com.taskManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>
{
	List<Task> findByMessageContainingIgnoreCase(String message);

	List<Task> findByCreatedFor_NameContainingIgnoreCase(String name);
}
