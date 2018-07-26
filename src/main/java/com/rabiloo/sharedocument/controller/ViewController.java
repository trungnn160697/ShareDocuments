package com.rabiloo.sharedocument.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rabiloo.sharedocument.dto.document.DocumentDto;
import com.rabiloo.sharedocument.entity.Subject;
import com.rabiloo.sharedocument.service.DocumentService;
import com.rabiloo.sharedocument.service.ExamService;
import com.rabiloo.sharedocument.service.SubjectService;
import com.rabiloo.sharedocument.service.UserService;
import com.rabiloo.sharedocument.util.Constants;

@Controller
public class ViewController {
	@Autowired
	private DocumentService documentService;
	@Autowired
	private ExamService examService;
	@Autowired
	private UserService userService;
	@Autowired
	private SubjectService subjectService;

	@GetMapping("/share-document")
	public String index(Model model) {
		model.addAttribute("topDocuments", documentService.findTop9Document().getDocumentDtos());
		model.addAttribute("totalDocument", documentService.countAll());
		model.addAttribute("numberOfDownload", documentService.coutnNumberOfDownload());
		model.addAttribute("totalExam", examService.countAll());
		model.addAttribute("totalUser", userService.countByDeleted(false));
		return "/user/index";
	}

	@GetMapping("/share-document/{id}/{page}")
	public String getDocumentBysubject(Model model, @PathVariable("id") Integer subjectId,
			@PathVariable("page") Integer page, HttpSession session) {
		Subject subject = subjectService.findById(subjectId);
		session.setAttribute("subjectId", subjectId);
		String nameDocument = "";
		if(session.getAttribute("search")!=null) {
			nameDocument = (String) session.getAttribute("search");
		}
		if (page == null) {
			page = 1;
		}
		List<DocumentDto> documentDtos = documentService.findDocumentBySubject(subject, nameDocument, page - 1)
				.getDocumentDtos();
		Integer totalPage = (int) Math
				.ceil((double) documentService.countDocument(subject, nameDocument) / Constants.PAGE_SIZE);
		ArrayList<Integer> numberPage = new ArrayList<>();
		for (int i = 1; i <= totalPage; i++) {
			numberPage.add(i);
		}
		model.addAttribute("numberPage", numberPage);
		model.addAttribute("currPage", page);
		model.addAttribute("listDocument", documentDtos);
		return "/user/document-subject";
	}

	@PostMapping("/share-document/{id}/{page}")
	public String getDocumentBysubject(Model model, @PathVariable("id") Integer subjectId,
			@RequestParam("documentSearch") String documentSearch, @PathVariable("page") Integer page,
			HttpSession session) {
		Subject subject = subjectService.findById(subjectId);
		session.setAttribute("subjectId", subjectId);
		String nameDocument = "";
		if (documentSearch != null) {
			nameDocument = documentSearch;
		}
		if (page == null) {
			page = 1;
		}
		List<DocumentDto> documentDtos = documentService.findDocumentBySubject(subject, nameDocument, page - 1)
				.getDocumentDtos();
		Integer count = documentService.countDocument(subject, nameDocument);
		Integer totalPage = (int) Math.ceil((double) count / Constants.PAGE_SIZE);
		ArrayList<Integer> numberPage = new ArrayList<>();
		for (int i = 1; i <= totalPage; i++) {
			numberPage.add(i);
		}
		model.addAttribute("numberPage", numberPage);
		model.addAttribute("currPage", 1);
		model.addAttribute("listDocument", documentDtos);
		session.setAttribute("search",nameDocument);
		return "/user/document-subject";
	}
}
