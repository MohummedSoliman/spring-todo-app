package com.mohamed.springboot.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer>{

	List<Todo> findByUserName(String userName);
	
}
