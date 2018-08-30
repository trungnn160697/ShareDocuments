package com.rabiloo.sharedocument.mapper;

import org.springframework.stereotype.Repository;

import com.rabiloo.sharedocument.dto.question.QuestionDto;
import com.rabiloo.sharedocument.dto.question.QuestionRequest;
import com.rabiloo.sharedocument.entity.Question;

@Repository("questionMapper")
public class QuestionMapper {
	public Question toEntity(QuestionRequest questionRequest) {
		return new Question(questionRequest.getContentQuestion(), questionRequest.getAnswerA(),
				questionRequest.getAnswerB(), questionRequest.getAnswerC(), questionRequest.getAnswerD(),
				questionRequest.getAnswerTrue(), questionRequest.getImageUrl(), false);
	}

	public QuestionDto toDto(Question question) {
		return new QuestionDto(question.getId(), question.getContentQuestion(), question.getAnswerA(),
				question.getAnswerB(), question.getAnswerC(), question.getAnswerD(), question.getAnswerTrue(),
				question.getImageUrl(), question.getDeleted());
	}
}
