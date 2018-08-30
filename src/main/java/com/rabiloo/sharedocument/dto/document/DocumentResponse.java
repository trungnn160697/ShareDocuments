package com.rabiloo.sharedocument.dto.document;

import java.util.List;

import com.rabiloo.sharedocument.util.MessageUtil;

public class DocumentResponse {

	private List<DocumentDto> DocumentDtos;
	private DocumentDto DocumentDto;
	private MessageUtil message;
	private Long iTotalRecords;
	private Long iTotalDisplayRecords;
	private String sEcho;
	private List<DocumentDto> aaData;

	public List<DocumentDto> getDocumentDtos() {
		return DocumentDtos;
	}

	public void setDocumentDtos(List<DocumentDto> documentDtos) {
		DocumentDtos = documentDtos;
	}

	public DocumentDto getDocumentDto() {
		return DocumentDto;
	}

	public void setDocumentDto(DocumentDto documentDto) {
		DocumentDto = documentDto;
	}

	public MessageUtil getMessage() {
		return message;
	}

	public void setMessage(MessageUtil message) {
		this.message = message;
	}

	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public List<DocumentDto> getAaData() {
		return aaData;
	}

	public void setAaData(List<DocumentDto> aaData) {
		this.aaData = aaData;
	}


}
