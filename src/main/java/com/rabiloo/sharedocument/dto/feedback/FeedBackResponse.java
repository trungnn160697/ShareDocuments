package com.rabiloo.sharedocument.dto.feedback;

import java.util.List;

import com.rabiloo.sharedocument.util.MessageUtil;

public class FeedBackResponse {
	private List<FeedbackDto> feedbackDtos;
	private FeedbackDto feedbackDto;
	private MessageUtil message;
	private Long iTotalRecords;
	private Long iTotalDisplayRecords;
	private String sEcho;
	private List<FeedbackDto> aaData;

	public List<FeedbackDto> getFeedbackDtos() {
		return feedbackDtos;
	}

	public void setFeedbackDtos(List<FeedbackDto> feedbackDtos) {
		this.feedbackDtos = feedbackDtos;
	}

	public FeedbackDto getFeedbackDto() {
		return feedbackDto;
	}

	public void setFeedbackDto(FeedbackDto feedbackDto) {
		this.feedbackDto = feedbackDto;
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

	public List<FeedbackDto> getAaData() {
		return aaData;
	}

	public void setAaData(List<FeedbackDto> aaData) {
		this.aaData = aaData;
	}

}
