package com.mohamed.springboot.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "Mohamed", "Learn Spring Boot",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "Mohamed", "Learn AWS",
				LocalDate.now().plusYears(3), false));
		todos.add(new Todo(++todosCount, "Mohamed", "Learn Git Version Control",
				LocalDate.now().plusYears(4), false));
	}
	
	public List<Todo> findByUsername(String username){
		return todos;
	}
	
	public void addTodo(String name, String description,
						LocalDate targetDate,boolean done) {
		todos.add(new Todo(++todosCount, name, description,
				targetDate, done));
	}
	
	public void deleteTodo(int id) {
		
		Predicate<? super Todo> prdicate = todo -> todo.getId() == id;
		todos.removeIf(prdicate);
	}
}
