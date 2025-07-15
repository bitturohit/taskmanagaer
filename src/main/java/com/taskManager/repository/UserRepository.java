package com.taskManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManager.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	// spring data JPA derived query. Spring auto-converts the method name it into a
	// SQL query. Alternatively we can use
	// @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',
	// :keyword, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	// List<User> search(String keyword);

	List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
}
