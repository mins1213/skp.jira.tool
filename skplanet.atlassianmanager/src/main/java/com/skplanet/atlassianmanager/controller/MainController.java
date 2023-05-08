package com.skplanet.atlassianmanager.controller;

import com.skplanet.atlassianmanager.service.UserService;
import com.skplanet.atlassianmanager.vo.UserVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String main() {
		
		System.out.println("main");
		return "main";
	}
	
	@PostMapping("/authUser")
	public String authUser(UserVo userVo) {
		
		System.out.println(userVo.toString());
		
		try {
			userService.authUser(userVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MainController.authUser ERROR", e);
			return "false";
		}

		return "done";
	}
	
}
