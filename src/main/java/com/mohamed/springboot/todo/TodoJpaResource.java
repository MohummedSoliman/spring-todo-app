package com.mohamed.springboot.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class TodoJpaResource {

	private TodoService todoService;
	private TodoRepository todoRepository;
	

	public TodoJpaResource(TodoService todoService, TodoRepository todoRepository) {
		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}
	
	@GetMapping("basicAuth")
	public String basicAuthCheck() {
		return "Success" ;
	}

	@GetMapping("users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		return todoRepository.findByUserName(username);
	}

	@GetMapping("users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
		return todoRepository.findById(id).get();
	}

	@DeleteMapping("users/{username}/todos/{id}")
	public ResponseEntity<Object> deleteTodo(@PathVariable String username, @PathVariable int id) {
		todoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
		todoRepository.save(todo);
		return todo;
	}

	@PostMapping("/users/{username}/todos")
	public void createTodo(@PathVariable String username, @RequestBody Todo todo) {
		todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
	}
}








