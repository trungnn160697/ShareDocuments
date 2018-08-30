package com.rabiloo.sharedocument.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.rabiloo.sharedocument.dto.exam.ExamDto;
import com.rabiloo.sharedocument.dto.exam.ExamRequest;
import com.rabiloo.sharedocument.dto.exam.ExamResponse;
import com.rabiloo.sharedocument.entity.Exam;
import com.rabiloo.sharedocument.entity.Subject;
import com.rabiloo.sharedocument.mapper.ExamMapper;
import com.rabiloo.sharedocument.repository.ExamRepository;
import com.rabiloo.sharedocument.util.Constants;

@Service
@Transactional(noRollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class ExamService {

	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private ExamMapper examMapper;
	@Autowired
	private SubjectService subjectService;

	public ExamResponse findByNameContaining(String name, Integer page) {
		ExamResponse examResponse = new ExamResponse();
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, Constants.pageSize);
		List<ExamDto> examDtos = new ArrayList<>();
		Page<Exam> pageExam = examRepository.findByDeletedAndNameContaining(false, name, pageRequest);
		List<Exam> listExam = pageExam.getContent();
		if (listExam != null) {
			if (!CollectionUtils.isEmpty(listExam)) {
				examDtos = listExam.parallelStream().map(exam -> examMapper.toDto(exam)).collect(Collectors.toList());
			}
		}
		examResponse.setExamDtos(examDtos);
		return examResponse;
	}

	public Long countByNameContaining(String name) {
		return examRepository.countByDeletedAndNameContaining(false, name);
	}

	public Exam save(String name, Integer subjectId, Integer quantityQuestion) {
		Exam exam = examMapper.toEntity(createFormRequest(name, subjectId, quantityQuestion));
		exam.setSubject(subjectService.findById(subjectId));
		return examRepository.save(exam);
	}

	public ExamRequest createFormRequest(String name, Integer subjectId, Integer quantityQuestion) {
		return new ExamRequest(name, subjectId, quantityQuestion);
	}

	public Exam delete(Integer id) {
		Exam exam = examRepository.findById(id).get();
		exam.setDeleted(true);
		return examRepository.save(exam);
	}

	public Exam findOne(Integer id) {
		return examRepository.findById(id).get();
	}

	public Exam update(Exam exam) {
		return examRepository.save(exam);
	}
	public Long countAll() {
		return examRepository.countByDeleted(false);
	}
	public ExamResponse getExam(Integer id) {
		Exam exam = findOne(id);
		ExamDto examDto = examMapper.toDto(exam);
		ExamResponse examResponse = new ExamResponse();
		examResponse.setExamtDto(examDto);
		return examResponse;
	}
	
	public ExamResponse getExam(String name,Integer page) {
		ExamResponse examResponse = new ExamResponse();
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, Constants.PAGE_SIZE_EXAM);
		List<ExamDto> examDtos = new ArrayList<>();
		Page<Exam> pageExam = examRepository.findByDeletedAndNameContaining(false, name, pageRequest);
		List<Exam> listExam = pageExam.getContent();
		if (listExam != null) {
			if (!CollectionUtils.isEmpty(listExam)) {
				examDtos = listExam.parallelStream().map(exam -> examMapper.toDto(exam)).collect(Collectors.toList());
			}
		}
		examResponse.setExamDtos(examDtos);
		return examResponse;
	}
	
	public Long countExam(Subject subject) {
		return examRepository.countByDeletedAndSubject(false, subject);
	}
	public Long countExam() {
		return examRepository.countByDeleted(false);
	}
}

