package com.rabiloo.sharedocument.mapper;

import org.springframework.stereotype.Repository;

import com.rabiloo.sharedocument.dto.document.DocumentDto;
import com.rabiloo.sharedocument.dto.document.DocumentRequest;
import com.rabiloo.sharedocument.entity.Document;

@Repository("documentMapper")
public class DocumentMapper {
	public Document toEntity(DocumentRequest documentRequest) {
		return new Document(documentRequest.getName(), documentRequest.getDescription(), documentRequest.getImage(),
				documentRequest.getDate(), documentRequest.getDocument());
	}

	public DocumentDto toDto(Document document) {
		return new DocumentDto(document.getId(), document.getName(), document.getDescription(), document.getImage(),
				document.getRate(), document.getDate(), document.getSubject().getName(), document.getNumberOfDownload(),
				document.getLinkDocument(), document.getUser().getUsername(), document.getUser().getId(),
				document.getSubject().getId());
	}
}
