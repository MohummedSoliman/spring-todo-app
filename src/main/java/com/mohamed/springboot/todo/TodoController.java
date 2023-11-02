package com.mohamed.springboot.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
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
	
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage() {
		return "todo";
	}
	
	@PostMapping(value="add-todo")
	public String addNewTodo(@RequestParam String description, ModelMap model) {
		todoService.addTodo(model.get("name").toString(), description, LocalDate.now().plusYears(2), false);
		return "redirect:all-todos";
	}
}
