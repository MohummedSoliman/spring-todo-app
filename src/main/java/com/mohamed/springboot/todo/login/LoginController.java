package com.mohamed.springboot.todo.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private LoginAuthenticationService authenticationService;
	
	public LoginController(LoginAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value="login", method= RequestMethod.GET)
	public String toTologin() {
		return "login";
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String goToWelcomePage(@RequestParam String name, @RequestParam String password,
									ModelMap model) {
		
		if(authenticationService.authenticate(name, password)) {
		
			model.put("name", name);
			return "welcome";
		}
		model.put("error", "Invalid Credintional! Please Try again");
		return "login";
	}
}
