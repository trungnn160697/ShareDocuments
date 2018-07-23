package com.rabiloo.sharedocument.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rabiloo.sharedocument.service.SubjectService;

@Controller
public class ExamController {
	@Autowired
	private SubjectService subjectService;

	@GetMapping("/exam")
	public String getExam(Model model) {
		model.addAttribute("subjects", subjectService.findAll());
		return "manager/exam";
	}
}
