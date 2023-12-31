package com.mohamed.springboot.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todosCount = 0;

	static {
		todos.add(new Todo(++todosCount, "Mohamed", "Learn Spring Boot", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "Mohamed", "Learn AWS", LocalDate.now().plusYears(3), false));
		todos.add(new Todo(++todosCount, "Mohamed", "Learn Git Version Control", LocalDate.now().plusYears(4), false));
	}

	public List<Todo> findByUsername(String username) {
		
		Predicate<? super Todo> predicate = 
				todo -> todo.getUserName().equalsIgnoreCase(username);
		todos.stream().filter(predicate).toList();
		return todos;
	}

	public void addTodo(String name, String description, LocalDate targetDate, boolean done) {
		todos.add(new Todo(++todosCount, name, description, targetDate, done));
	}

	public void deleteTodo(int id) {

		Predicate<? super Todo> prdicate = todo -> todo.getId() == id;
		todos.removeIf(prdicate);
	}

	public Todo findById(int id) {
		return todos.stream().
					filter(todo -> todo.getId() == id).
					findFirst().
					get();
	}

	public void updateTodo(Todo todo) {
		deleteTodo(todo.getId());
		todos.add(todo);
	}
}
