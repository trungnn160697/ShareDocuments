package com.rabiloo.sharedocument.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedbackController {

	@GetMapping("/feedback")
	public String feedback(HttpSession session) {
		session.setAttribute("active_menu",5);
		return "/manager/feedback";
	}
}
