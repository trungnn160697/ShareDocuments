package com.rabiloo.sharedocument.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rabiloo.sharedocument.service.DocumentService;

@Controller
public class ViewController {
	@Autowired
	private DocumentService documentService;

	@GetMapping("/share-document")
	public String index(Model model) {
		model.addAttribute("topDocuments", documentService.findTop3Document().getDocumentDtos());
		model.addAttribute("totalDocument", documentService.countAll());
		return "/user/index";
	}
}
