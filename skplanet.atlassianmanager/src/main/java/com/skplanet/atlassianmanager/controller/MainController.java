package com.skplanet.atlassianmanager.controller;

import com.skplanet.atlassianmanager.service.UserService;
import com.skplanet.atlassianmanager.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String main() {
		
		return "main";
	}
	
	@PostMapping("/authUser")
	public String authUser(User user) {
		
		userService.authUser(user);
		
		return "";
	}
	
}
