package com.mohamed.springboot.todo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodoController {
	
	private TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@RequestMapping("all-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("Mohamed");
		model.addAttribute("todos", todos);
		return "listTodos";
	}
}
