package com.rabiloo.sharedocument.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/info-user")
	public String userInfo(HttpSession session) {
		session.setAttribute("active_menu",1);
		return "manager/info-user";
	}

	@GetMapping("/user")
	public String user(HttpSession session) {
		session.setAttribute("active_menu",2);
		return "manager/user";
	}
}
