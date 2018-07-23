package com.rabiloo.sharedocument.controller.resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabiloo.sharedocument.dto.document.DocumentResponse;
import com.rabiloo.sharedocument.entity.Document;
import com.rabiloo.sharedocument.entity.Subject;
import com.rabiloo.sharedocument.entity.User;
import com.rabiloo.sharedocument.service.DocumentService;
import com.rabiloo.sharedocument.service.SubjectService;
import com.rabiloo.sharedocument.service.UserService;
import com.rabiloo.sharedocument.util.Constants;
import com.rabiloo.sharedocument.util.MessageUtil;
import com.rabiloo.sharedocument.validation.DocumentValidator;

@RestController
public class DocumentResource {
	@Autowired
	private DocumentService documentService;
	@Autowired
	private UserService userService;
	@Autowired
	private SubjectService subjectService;

	@GetMapping("/listdocument")
	public ResponseEntity<DocumentResponse> getListDocument(HttpServletRequest request) {
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

	@DeleteMapping("/document/delete/{id}")
	public ResponseEntity<Document> delete(@PathVariable("id") Integer id) {
		documentService.delete(id);
		return new ResponseEntity<Document>(HttpStatus.OK);
	}

	@GetMapping("/document/view/{id}")
	public ResponseEntity<DocumentResponse> getDocument(@PathVariable("id") Integer id) {

		return new ResponseEntity<DocumentResponse>(documentService.getDocument(id), HttpStatus.OK);
	}

	@GetMapping("/document/download/{id}")
	public void displayImage(HttpServletResponse response, @PathVariable("id") Integer id) {
		Document document = documentService.findOne(id);
		File file = new File(Constants.URL_DOCUMENT + document.getLinkDocument());
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		BufferedInputStream inStrem;
		try {
			inStrem = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inStrem.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			outStream.flush();
			inStrem.close();
			document.setNumberOfDownload(document.getNumberOfDownload() + 1);
			documentService.update(document);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@GetMapping("/listDocumentByUser")
	public ResponseEntity<DocumentResponse> getListDocumentByUser(HttpServletRequest request, HttpSession session) {
		String search = "";
		Integer id = (Integer) session.getAttribute("userOfId");
		User user = userService.findById(id);
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
		DocumentResponse documentResponse = documentService.findByName(search, user, page);
		documentResponse.setAaData(documentResponse.getDocumentDtos());
		documentResponse.setiTotalDisplayRecords(documentService.countByName(search, user));
		documentResponse.setiTotalRecords(documentService.countByName(search, user));
		return new ResponseEntity<DocumentResponse>(documentResponse, HttpStatus.OK);
	}

	@GetMapping("/listDocumentBySubject")
	public ResponseEntity<DocumentResponse> getListDocumentBySubject(HttpServletRequest request, HttpSession session) {
		String search = "";
		Integer id = (Integer) session.getAttribute("subjectOfId");
		Subject subject = subjectService.findById(id);
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
		DocumentResponse documentResponse = documentService.findByName(search, subject, page);
		documentResponse.setAaData(documentResponse.getDocumentDtos());
		documentResponse.setiTotalDisplayRecords(documentService.countByName(search, subject));
		documentResponse.setiTotalRecords(documentService.countByName(search, subject));
		return new ResponseEntity<DocumentResponse>(documentResponse, HttpStatus.OK);
	}

}
