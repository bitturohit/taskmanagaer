package com.taskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManager.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

}
