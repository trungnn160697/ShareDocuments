package com.rabiloo.sharedocument.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rabiloo.sharedocument.dto.question.QuestionRequest;
import com.rabiloo.sharedocument.entity.Exam;
import com.rabiloo.sharedocument.entity.Question;
import com.rabiloo.sharedocument.mapper.QuestionMapper;
import com.rabiloo.sharedocument.repository.QuestionRepository;
import com.rabiloo.sharedocument.util.Constants;
import com.rabiloo.sharedocument.util.UploadUtil;

@Service
@Transactional(noRollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private ExamService examService;

	public QuestionRequest createFormRequest(Integer examId, String contentQuestion, String answerA, String answerB,
			String answerC, String answerD, String answerTrue, MultipartFile image) {
		if (image != null) {
			return new QuestionRequest(examId, contentQuestion, answerA, answerB, answerC, answerD, answerTrue,
					UploadUtil.upload(image, Constants.URL_IMAGE_QUESTION, "image"));
		}
		return new QuestionRequest(examId, contentQuestion, answerA, answerB, answerC, answerD, answerTrue, null);
	}

	public Question save(Integer examId, String contentQuestion, String answerA, String answerB, String answerC,
			String answerD, String answerTrue, MultipartFile image) {
		QuestionRequest questionRequest = createFormRequest(examId, contentQuestion, answerA, answerB, answerC, answerD,
				answerTrue, image);
		Exam exam = examService.findOne(examId);
		Question question = questionMapper.toEntity(questionRequest);
		question.setExam(exam);
		exam.setQuantityQuestion(exam.getQuantityQuestion() + 1);
		examService.update(exam);
		return questionRepository.save(question);

	}
}
