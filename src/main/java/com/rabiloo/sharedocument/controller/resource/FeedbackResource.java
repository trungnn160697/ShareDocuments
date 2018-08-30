package com.rabiloo.sharedocument.controller.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabiloo.sharedocument.dto.RepplyRequest;
import com.rabiloo.sharedocument.dto.feedback.FeedBackResponse;
import com.rabiloo.sharedocument.dto.feedback.FeedbackRequest;
import com.rabiloo.sharedocument.service.FeedbackService;
import com.rabiloo.sharedocument.util.Constants;

@RestController
public class FeedbackResource {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/send-feedback")
	@ResponseBody
	public ResponseEntity<FeedBackResponse> send(@RequestBody FeedbackRequest feedbackRequest) {
		FeedBackResponse feedBackResponse = feedbackService.save(feedbackRequest);
		return new ResponseEntity<FeedBackResponse>(feedBackResponse, HttpStatus.OK);
	}

	@GetMapping("/listFeedback")
	public ResponseEntity<FeedBackResponse> getListFeedback(HttpServletRequest request) {
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
		FeedBackResponse feedbackResponse = feedbackService.findAll(search, page);
		feedbackResponse.setAaData(feedbackResponse.getFeedbackDtos());
		feedbackResponse.setiTotalDisplayRecords(feedbackService.count(search));
		feedbackResponse.setiTotalRecords(feedbackService.count(search));
		return new ResponseEntity<FeedBackResponse>(feedbackResponse, HttpStatus.OK);
	}

	@DeleteMapping("/feedback/delete/{id}")
	public ResponseEntity<FeedBackResponse> delete(@PathVariable("id") Integer id) {
		feedbackService.delete(id);
		return new ResponseEntity<FeedBackResponse>(HttpStatus.OK);
	}
	
	@GetMapping("/feedback/{id}")
	public ResponseEntity<FeedBackResponse> getFeedback(@PathVariable("id") Integer id){
		FeedBackResponse feedBackResponse = feedbackService.findOne(id);
		return new ResponseEntity<FeedBackResponse>(feedBackResponse,HttpStatus.OK);
	}
	
	@PostMapping("/send-repply")
	@ResponseBody
	public ResponseEntity<FeedBackResponse> sendRepply(@RequestBody RepplyRequest repply){
		feedbackService.sendMail(repply);
		return new ResponseEntity<FeedBackResponse>(HttpStatus.OK);
	}
}
