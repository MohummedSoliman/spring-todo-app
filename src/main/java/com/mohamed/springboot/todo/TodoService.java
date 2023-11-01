package com.mohamed.springboot.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	
	static {
		todos.add(new Todo(1, "Mohamed", "Learn Spring Boot",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(2, "Mohamed", "Learn AWS",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(3, "Mohamed", "Learn Git Version Control",
				LocalDate.now().plusYears(1), false));
	}
	
	public List<Todo> findByUsername(String username){
		return todos;
	}
}
