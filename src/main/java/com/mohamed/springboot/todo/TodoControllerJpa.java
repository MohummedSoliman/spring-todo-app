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
public class TodoControllerJpa {
	
	private TodoRepository todoRepository;

	public TodoControllerJpa(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	@RequestMapping("all-todos")
	public String listAllTodos(ModelMap model) {
		String userName = getLoggedinusername(model);
		List<Todo> todos = todoRepository.findByUserName(userName);
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	private String getLoggedinusername(ModelMap model) {
		return (String) model.get("name");
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String userName = getLoggedinusername(model); 
		Todo todo = new Todo(0, userName, "", LocalDate.now(), false);
		model.put("todo", todo);
		return "todo";
	}
	
	@PostMapping(value="add-todo")
	public String addNewTodo(ModelMap model,@Valid Todo todo, 
							BindingResult result) {
		
		if (result.hasErrors()) {
			return "todo";
		}
		String userName = getLoggedinusername(model);
		todo.setUserName(userName);
		todoRepository.save(todo);
		return "redirect:all-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteById(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:all-todos";
	}
	
	@GetMapping("update-todo")
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@PostMapping("update-todo")
	public String updateTodo(ModelMap model,@Valid Todo todo, 
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "todo";
		}
		String userName = getLoggedinusername(model);
		todo.setUserName(userName);
		todoRepository.save(todo);
		return "redirect:all-todos";
	}
}
