package com.rabiloo.sharedocument.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabiloo.sharedocument.service.QuestionService;
import com.rabiloo.sharedocument.util.QuestionValidator;

@RestController
public class QuestionResource {
	@Autowired
	private QuestionService questionService;

	@PostMapping("/question/add")
	public ResponseEntity<String> save(@RequestParam("examId") Integer examId,
			@RequestParam("contentQuestion") String contentQuestion, @RequestParam("answerA") String answerA,
			@RequestParam("answerB") String answerB, @RequestParam("answerC") String answerC,
			@RequestParam("answerD") String answerD, @RequestParam("answerTrue") String answerTrue,
			@RequestParam(name = "image", required = false) MultipartFile file) {
		if(!QuestionValidator.validateForm(contentQuestion, answerA, answerB, answerC, answerD, answerTrue)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		questionService.save(examId, contentQuestion, answerA, answerB, answerC, answerD, answerTrue, file);
		return new ResponseEntity<String>(HttpStatus.OK);

	}
}
