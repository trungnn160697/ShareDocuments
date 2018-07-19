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

import com.rabiloo.sharedocument.dto.subject.SubjectResponse;
import com.rabiloo.sharedocument.service.SubjectService;
import com.rabiloo.sharedocument.util.Constants;
import com.rabiloo.sharedocument.util.MessageUtil;
import com.rabiloo.sharedocument.validation.SubjectValidation;

@RestController
public class SubjectResource {
	@Autowired
	private SubjectService subjectService;

	@GetMapping("/listsubject")
	public ResponseEntity<SubjectResponse> getListSubject(HttpServletRequest request) {
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
		SubjectResponse subjectResponse = subjectService.findByDeletedAndNameContaining(false, search, page);
		subjectResponse.setAaData(subjectResponse.getSubjectDtos());
		subjectResponse.setiTotalDisplayRecords(subjectService.countByDeletedAndNameContaining(false, search));
		subjectResponse.setiTotalRecords(subjectService.countByDeletedAndNameContaining(false, search));
		return new ResponseEntity<SubjectResponse>(subjectResponse, HttpStatus.OK);
	}

	@DeleteMapping("/subject/delete/{id}")
	public ResponseEntity<SubjectResource> delete(@PathVariable("id") Integer id) {
		subjectService.delete(id);
		return new ResponseEntity<SubjectResource>(HttpStatus.OK);
	}

	@PostMapping("/subject/info-subject")
	public ResponseEntity<SubjectResponse> getSubjectById(@RequestParam("id") Integer id) {
		return new ResponseEntity<SubjectResponse>(subjectService.findByIdAndDeleted(id, false), HttpStatus.OK);
	}

	@PostMapping("/subject/add-subject")
	public ResponseEntity<SubjectResponse> add(@RequestParam("name") String name, @RequestParam("code") String code,
			@RequestParam("description") String description) {
		if (!SubjectValidation.validateForm(name, code, description)) {
			return new ResponseEntity<SubjectResponse>(
					new SubjectResponse(new MessageUtil("Không được bỏ trống thông tin")), HttpStatus.OK);
		}
		subjectService.save(name, code, description);
		return new ResponseEntity<SubjectResponse>(new SubjectResponse(new MessageUtil("", 200)), HttpStatus.OK);
	}

	@PostMapping("/subject/update-subject")
	public ResponseEntity<SubjectResponse> update(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("code") String code, @RequestParam("description") String description) {
		if (!SubjectValidation.validateForm(name, code, description)) {
			return new ResponseEntity<SubjectResponse>(
					new SubjectResponse(new MessageUtil("Không được bỏ trống thông tin")), HttpStatus.OK);
		}
		subjectService.update(id, name, code, description);
		return new ResponseEntity<SubjectResponse>(new SubjectResponse(new MessageUtil("", 200)), HttpStatus.OK);
	}
}
