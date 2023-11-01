package com.mohamed.springboot.todo;

import java.time.LocalDate;
import java.util.List;

public class TodoService {
	
	private static List<Todo> todos;
	
	static {
		todos.add(new Todo(1, "Mohamed", "Learn Spring Boot",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(1, "Mohamed", "Learn AWS",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(1, "Mohamed", "Learn Git Version Control",
				LocalDate.now().plusYears(1), false));
	}
	
	public List<Todo> findByUsername(String username){
		return todos;
	}
}
