package com.rabiloo.sharedocument.controller.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabiloo.sharedocument.dto.exam.ExamResponse;
import com.rabiloo.sharedocument.service.ExamService;
import com.rabiloo.sharedocument.util.Constants;
import com.rabiloo.sharedocument.util.MessageUtil;
import com.rabiloo.sharedocument.validation.ExamValidator;

@RestController
public class ExamResource {
	@Autowired
	private ExamService examService;

	@GetMapping("/listExam")
	public ResponseEntity<ExamResponse> getListSubject(HttpServletRequest request) {
		String search = "";
		if (request.getParameter("sSearch") != null) {
			search = request.getParameter("sSearch");
		}
		Integer pageDisplayLength = 1;
		if (request.getParameter("iDisplayLength") != null)
			pageDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		Constants.pageSize = pageDisplayLength;
		Integer iDisplayStart = 1;
		if (request.getParameter("iDisplayStart") != null)
			iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		Integer page = (iDisplayStart / pageDisplayLength);
		ExamResponse examResponse = examService.findByNameContaining(search, page);
		examResponse.setAaData(examResponse.getExamDtos());
		examResponse.setiTotalDisplayRecords(examService.countByNameContaining(search));
		examResponse.setiTotalRecords(examService.countByNameContaining(search));
		return new ResponseEntity<ExamResponse>(examResponse, HttpStatus.OK);
	}

	@PostMapping("/exam/add-exam")
	public ResponseEntity<ExamResponse> add(@RequestParam("name") String name,
			@RequestParam("subject") Integer subjectId, @RequestParam("numberQuestion") Integer quantityQuestion) {
		if (!ExamValidator.validateForm(name, subjectId, quantityQuestion)) {
			return new ResponseEntity<ExamResponse>(new ExamResponse(new MessageUtil("Không được bỏ trống thông tin")),
					HttpStatus.OK);
		}
		examService.save(name, subjectId, quantityQuestion);
		return new ResponseEntity<ExamResponse>(new ExamResponse(new MessageUtil("", 200)), HttpStatus.OK);
	}

	@DeleteMapping("/exam/delete/{id}")
	public ResponseEntity<ExamResponse> delete(@PathVariable("id") Integer id) {
		examService.delete(id);
		return new ResponseEntity<ExamResponse>(HttpStatus.OK);
	}

}
