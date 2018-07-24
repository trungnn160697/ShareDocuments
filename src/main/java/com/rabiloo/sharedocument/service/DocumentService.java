package com.rabiloo.sharedocument.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.rabiloo.sharedocument.dto.document.DocumentDto;
import com.rabiloo.sharedocument.dto.document.DocumentRequest;
import com.rabiloo.sharedocument.dto.document.DocumentResponse;
import com.rabiloo.sharedocument.dto.user.UserResponse;
import com.rabiloo.sharedocument.entity.Document;
import com.rabiloo.sharedocument.entity.Subject;
import com.rabiloo.sharedocument.entity.User;
import com.rabiloo.sharedocument.mapper.DocumentMapper;
import com.rabiloo.sharedocument.repository.DocumentRepository;
import com.rabiloo.sharedocument.util.Constants;
import com.rabiloo.sharedocument.util.Format;
import com.rabiloo.sharedocument.util.UploadUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DocumentService {
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private DocumentMapper documentMapper;
	@Autowired
	private HttpSession session;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UserService userService;

	public DocumentResponse findByName(String name, Integer page) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, Constants.pageSize);
		List<Document> listDocument = documentRepository.findByDeletedAndNameContaining(false, name, pageRequest)
				.getContent();
		List<DocumentDto> documentDtos = new ArrayList<>();
		if (listDocument != null) {
			if (!CollectionUtils.isEmpty(listDocument)) {
				documentDtos = listDocument.parallelStream().map(document -> documentMapper.toDto(document))
						.collect(Collectors.toList());
			}
		}
		DocumentResponse documentResponse = new DocumentResponse();
		documentResponse.setDocumentDtos(documentDtos);
		return documentResponse;
	}

	public Long countByName(String name) {
		return documentRepository.countByDeletedAndNameContaining(false, name);
	}

	public void save(String name, Integer subjectId, String description, MultipartFile image,
			MultipartFile linkDocument) {
		DocumentRequest documentRequest = createFormRequest(name, subjectId, description, image, linkDocument);
		Document document = documentMapper.toEntity(documentRequest);
		document.setDeleted(false);
		document.setRate(0.0);
		document.setNumberOfDownload(0);
		document.setSubject(subjectService.findById(documentRequest.getSubjectId()));
		document.setUser(userService.findById(documentRequest.getIdUser()));
		documentRepository.save(document);
	}

	public DocumentRequest createFormRequest(String name, Integer subjectId, String description, MultipartFile image,
			MultipartFile document) {

		UserResponse userLogin = (UserResponse) session.getAttribute("userLogin");
		String urlImage = "";
		if (image == null) {
			urlImage = userLogin.getUserDto().getImage();
		} else {
			urlImage = UploadUtil.upload(image, Constants.URL_IMAGE_DOCUMENT, "image");
		}
		DocumentRequest documentRequest = new DocumentRequest(name, description, urlImage,
				Format.formatDate(new Date()), subjectId, UploadUtil.upload(document, Constants.URL_DOCUMENT));
		documentRequest.setIdUser(userLogin.getUserDto().getId());
		return documentRequest;
	}

	public void delete(Integer id) {
		Document document = findOne(id);
		if (document != null) {
			document.setDeleted(true);
		}
	}

	public Document findOne(Integer id) {
		return documentRepository.findById(id).get();
	}

	public DocumentResponse getDocument(Integer id) {
		Document document = findOne(id);
		DocumentResponse documentResponse = new DocumentResponse();
		documentResponse.setDocumentDto(documentMapper.toDto(document));
		return documentResponse;
	}

	public Document update(Document document) {
		return documentRepository.save(document);
	}

	public DocumentResponse findByName(String name, User user, Integer page) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, Constants.pageSize);
		List<Document> listDocument = documentRepository
				.findByDeletedAndUserAndNameContaining(false, user, name, pageRequest).getContent();
		List<DocumentDto> documentDtos = new ArrayList<>();
		if (listDocument != null) {
			if (!CollectionUtils.isEmpty(listDocument)) {
				documentDtos = listDocument.parallelStream().map(document -> documentMapper.toDto(document))
						.collect(Collectors.toList());
			}
		}
		DocumentResponse documentResponse = new DocumentResponse();
		documentResponse.setDocumentDtos(documentDtos);
		return documentResponse;
	}

	public Long countByName(String name, User user) {
		return documentRepository.countByDeletedAndUserAndNameContaining(false, user, name);
	}

	public DocumentResponse findByName(String name, Subject subject, Integer page) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, Constants.pageSize);
		List<Document> listDocument = documentRepository
				.findByDeletedAndSubjectAndNameContaining(false, subject, name, pageRequest).getContent();
		List<DocumentDto> documentDtos = new ArrayList<>();
		if (listDocument != null) {
			if (!CollectionUtils.isEmpty(listDocument)) {
				documentDtos = listDocument.parallelStream().map(document -> documentMapper.toDto(document))
						.collect(Collectors.toList());
			}
		}
		DocumentResponse documentResponse = new DocumentResponse();
		documentResponse.setDocumentDtos(documentDtos);
		return documentResponse;
	}

	public Long countByName(String name, Subject subject) {
		return documentRepository.countByDeletedAndSubjectAndNameContaining(false, subject, name);
	}

	public Long countAll() {
		return documentRepository.countByDeleted(false);
	}

	public Long coutnNumberOfDownload() {
		return documentRepository.countDownload();
	}

	public DocumentResponse findTop3Document() {
		List<Document> listDocument = documentRepository.findTop3ByOrderByNumberOfDownloadDesc();
		List<DocumentDto> documentDtos = new ArrayList<>();
		if (listDocument != null) {
			if (!CollectionUtils.isEmpty(listDocument)) {
				documentDtos = listDocument.parallelStream().map(document -> documentMapper.toDto(document))
						.collect(Collectors.toList());
			}
		}
		DocumentResponse documentResponse = new DocumentResponse();
		documentResponse.setDocumentDtos(documentDtos);
		return documentResponse;
	}
}
