package com.rabiloo.sharedocument.controller.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabiloo.sharedocument.dto.document.DocumentResponse;
import com.rabiloo.sharedocument.service.DocumentService;
import com.rabiloo.sharedocument.util.Constants;
import com.rabiloo.sharedocument.util.MessageUtil;
import com.rabiloo.sharedocument.validation.DocumentValidator;

@RestController
public class DocumentResource {
	@Autowired
	private DocumentService documentService;

	@GetMapping("/listdocument")
	public ResponseEntity<DocumentResponse> getListSubject(HttpServletRequest request) {
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
		DocumentResponse documentResponse = documentService.findByName(search, page);
		documentResponse.setAaData(documentResponse.getDocumentDtos());
		documentResponse.setiTotalDisplayRecords(documentService.countByName(search));
		documentResponse.setiTotalRecords(documentService.countByName(search));
		return new ResponseEntity<DocumentResponse>(documentResponse, HttpStatus.OK);
	}

	@PostMapping("/document/add-document")
	public ResponseEntity<DocumentResponse> add(@RequestParam("name") String name,
			@RequestParam("idSubject") Integer subjectId, @RequestParam("description") String description,
			@RequestParam(name = "image", required = false) MultipartFile image,
			@RequestParam(name = "linkDocument", required = false) MultipartFile linkDocument) {
		if (!DocumentValidator.validateForm(name, subjectId, description)) {
			DocumentResponse documentResponse = new DocumentResponse();
			documentResponse.setMessage(new MessageUtil("Không được bỏ trống thông tin"));
			return new ResponseEntity<DocumentResponse>(documentResponse, HttpStatus.OK);
		}
		if (linkDocument == null) {
			DocumentResponse documentResponse = new DocumentResponse();
			documentResponse.setMessage(new MessageUtil("Chưa upload tài liệu"));
			return new ResponseEntity<DocumentResponse>(documentResponse, HttpStatus.OK);
		}

		documentService.save(name, subjectId, description, image, linkDocument);
		DocumentResponse documentResponse = new DocumentResponse();
		documentResponse.setMessage(new MessageUtil("Thành công", 200));
		return new ResponseEntity<DocumentResponse>(documentResponse, HttpStatus.OK);
	}

}
