package com.rabiloo.sharedocument.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.rabiloo.sharedocument.dto.RepplyRequest;
import com.rabiloo.sharedocument.dto.feedback.FeedBackResponse;
import com.rabiloo.sharedocument.dto.feedback.FeedbackDto;
import com.rabiloo.sharedocument.dto.feedback.FeedbackRequest;
import com.rabiloo.sharedocument.entity.Feedback;
import com.rabiloo.sharedocument.mapper.FeedbackMapper;
import com.rabiloo.sharedocument.repository.FeedbackRepository;
import com.rabiloo.sharedocument.util.Constants;
import com.rabiloo.sharedocument.util.MailUtil;

@Service
@Transactional(noRollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class FeedbackService {
	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private FeedbackMapper feedbackMapper;
	@Autowired
	private JavaMailSender emailSender;

	public FeedBackResponse save(FeedbackRequest feedbackRequest) {
		Feedback feedback = feedbackMapper.toEntity(feedbackRequest);
		feedback.setDeleted(false);
		Feedback result = feedbackRepository.save(feedback);
		FeedBackResponse feebackResponse = new FeedBackResponse();
		feebackResponse.setFeedbackDto(feedbackMapper.toDto(result));
		return feebackResponse;
	}

	public FeedBackResponse findAll(String email, Integer page) {
		FeedBackResponse feedbackResponse = new FeedBackResponse();
		List<FeedbackDto> feedbackDtos = new ArrayList<>();
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, Constants.pageSize);
		List<Feedback> listFeedback = feedbackRepository.findByDeletedAndEmailContaining(false, email, pageRequest)
				.getContent();
		if (feedbackDtos != null) {
			if (!CollectionUtils.isEmpty(listFeedback)) {
				feedbackDtos = listFeedback.parallelStream().map(feedback -> feedbackMapper.toDto(feedback))
						.collect(Collectors.toList());
			}
		}
		feedbackResponse.setFeedbackDtos(feedbackDtos);
		return feedbackResponse;

	}

	public Long count(String email) {
		return feedbackRepository.countByDeletedAndEmailContaining(false, email);
	}

	public FeedBackResponse delete(Integer id) {
		Feedback feedback = feedbackRepository.findById(id).get();
		feedback.setDeleted(true);
		feedbackRepository.save(feedback);
		return new FeedBackResponse();
	}

	public FeedBackResponse findOne(Integer id) {
		FeedbackDto feedbackDto = feedbackMapper.toDto(feedbackRepository.findById(id).get());
		FeedBackResponse feebackResponse = new FeedBackResponse();
		feebackResponse.setFeedbackDto(feedbackDto);
		return feebackResponse;
	}

	public void sendMail(RepplyRequest repplyRequest) {
		emailSender.send(
				MailUtil.sendMail(repplyRequest.getEmail(), repplyRequest.getTitle(), repplyRequest.getContent()));
	}

}
