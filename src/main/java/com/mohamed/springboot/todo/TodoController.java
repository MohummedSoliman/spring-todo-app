package com.mohamed.springboot.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

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
	public String showNewTodoPage(ModelMap model) {
		String userName = (String)model.get("name"); 
		Todo todo = new Todo(0, userName, "Default", LocalDate.now(), false);
		model.put("todo", todo);
		return "todo";
	}
	
	@PostMapping(value="add-todo")
	public String addNewTodo(ModelMap model,@Valid Todo todo, 
							BindingResult result) {
		
		if (result.hasErrors()) {
			return "todo";
		}
		
		todoService.addTodo(model.get("name").toString(), todo.getDescription(),
							todo.getTargetDate(), false);
		return "redirect:all-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteById(@RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:all-todos";
	}
	
	@GetMapping("update-todo")
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@PostMapping("update-todo")
	public String updateTodo(ModelMap model,@Valid Todo todo, 
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "todo";
		}
		String userName = (String) model.get("name");
		todo.setUserName(userName);
		todoService.updateTodo(todo);
		return "redirect:all-todos";
	}
}
