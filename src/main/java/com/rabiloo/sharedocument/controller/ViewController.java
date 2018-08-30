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
import com.rabiloo.sharedocument.dto.exam.ExamResponse;
import com.rabiloo.sharedocument.dto.subject.SubjectResponse;
import com.rabiloo.sharedocument.entity.Document;
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
	public String index(Model model, HttpSession session) {
		model.addAttribute("topDocuments", documentService.findTop9Document().getDocumentDtos());
		model.addAttribute("totalDocument", documentService.countAll());
		model.addAttribute("numberOfDownload", documentService.coutnNumberOfDownload());
		model.addAttribute("totalExam", examService.countAll());
		model.addAttribute("totalUser", userService.countByDeleted(false));
		session.setAttribute("active_menu", 0);
		return "/user/index";
	}

	@GetMapping("/share-document/{id}/{page}")
	public String getDocumentBysubject(Model model, @PathVariable("id") Integer subjectId,
			@PathVariable("page") Integer page, HttpSession session) {
		Subject subject = subjectService.findById(subjectId);
		session.setAttribute("subjectId", subjectId);
		String nameDocument = "";
		if (session.getAttribute("search") != null) {
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
		model.addAttribute("top3Download", documentService.findTop3DocumentBySubject(subject).getDocumentDtos());
		model.addAttribute("numberPage", numberPage);
		model.addAttribute("currPage", page);
		model.addAttribute("listDocument", documentDtos);
		model.addAttribute("subject", subject);

		if (subjectId == 1) {
			session.setAttribute("active_menu", 1);
		}
		if (subjectId == 2) {
			session.setAttribute("active_menu", 2);
		}
		if (subjectId == 3) {
			session.setAttribute("active_menu", 3);
		}
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
		model.addAttribute("currPage", page);
		model.addAttribute("listDocument", documentDtos);
		model.addAttribute("subject", subject);
		model.addAttribute("top3Download", documentService.findTop3DocumentBySubject(subject).getDocumentDtos());

		session.setAttribute("search", nameDocument);
		return "/user/document-subject";
	}

	@GetMapping("/share-document/document/{id}")
	public String getDocumentDetail(@PathVariable("id") Integer id, Model model, HttpSession session) {
		Document document = documentService.findOne(id);
		model.addAttribute("document", document);
		model.addAttribute("subject", document.getSubject());
		model.addAttribute("top3Download",
				documentService.findTop3DocumentBySubject(document.getSubject()).getDocumentDtos());
		session.setAttribute("active_menu", document.getSubject().getId());
		return "/user/document-detail";
	}
	
	@GetMapping("/share-document/exams/{page}")
	public String exam( Model model, HttpSession session,@PathVariable(name="page",required=false) Integer page) {
		if (page == null) {
			page = 1;
		}
		
		Long count = examService.countExam();
		Integer totalPage = (int) Math.ceil((double) count / Constants.PAGE_SIZE_EXAM);
		ArrayList<Integer> numberPage = new ArrayList<>();
		for (int i = 1; i <= totalPage; i++) {
			numberPage.add(i);
		}
		SubjectResponse subjectResponse = subjectService.findAll();
		ExamResponse examResponse = examService.getExam("",page-1);
		model.addAttribute("numberPage", numberPage);
		model.addAttribute("currPage", page);
		model.addAttribute("examResponse", examResponse);
		model.addAttribute("subjects", subjectResponse);
		session.setAttribute("active_menu", 4);
		return "/user/exam";
	}
	
	@GetMapping("/share-document/exam/{id}")
	public String getExam(@PathVariable("id") Integer id, Model model, HttpSession session) {
		ExamResponse examResponse = examService.getExam(id);
		model.addAttribute("examResponse", examResponse);
		session.setAttribute("active_menu", 4);
		return "/user/exam-test";
	}
}
