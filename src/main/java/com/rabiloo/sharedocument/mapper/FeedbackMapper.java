package com.rabiloo.sharedocument.mapper;

import org.springframework.stereotype.Repository;

import com.rabiloo.sharedocument.dto.feedback.FeedbackDto;
import com.rabiloo.sharedocument.dto.feedback.FeedbackRequest;
import com.rabiloo.sharedocument.entity.Feedback;

@Repository("feedbackMapper")
public class FeedbackMapper {
	public Feedback toEntity(FeedbackDto feedBackDto) {
		return new Feedback(feedBackDto.getName(), feedBackDto.getEmail(), feedBackDto.getContent());
	}

	public FeedbackDto toDto(Feedback feedback) {
		return new FeedbackDto(feedback.getId(), feedback.getName(), feedback.getEmail(), feedback.getContent());
	}

	public Feedback toEntity(FeedbackRequest feedBackRequest) {
		return new Feedback(feedBackRequest.getName(), feedBackRequest.getEmail(), feedBackRequest.getContent());
	}
}
