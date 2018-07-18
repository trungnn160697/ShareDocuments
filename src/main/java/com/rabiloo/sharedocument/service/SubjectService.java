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

import com.rabiloo.sharedocument.dto.subject.SubjectDto;
import com.rabiloo.sharedocument.dto.subject.SubjectRequest;
import com.rabiloo.sharedocument.dto.subject.SubjectResponse;
import com.rabiloo.sharedocument.entity.Subject;
import com.rabiloo.sharedocument.mapper.SubjectMapper;
import com.rabiloo.sharedocument.repository.SubjectReposiotory;
import com.rabiloo.sharedocument.util.Constants;

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class SubjectService {

	@Autowired
	private SubjectMapper subjectMapper;
	@Autowired
	private SubjectReposiotory subjectRepository;

	public SubjectResponse findAll() {
		SubjectResponse subjectResponse = new SubjectResponse();
		List<SubjectDto> subjectDtos = new ArrayList<>();
		List<Subject> listSubject = subjectRepository.findByDeleted(false);
		if (listSubject != null) {
			if (!CollectionUtils.isEmpty(listSubject)) {
				subjectDtos = listSubject.parallelStream().map(subject -> subjectMapper.toDto(subject))
						.collect(Collectors.toList());
			}
		}
		subjectResponse.setSubjectDtos(subjectDtos);
		return subjectResponse;

	}

	public SubjectResponse findByDeletedAndNameContaining(Boolean deleted, String name, Integer page) {
		SubjectResponse subjectResponse = new SubjectResponse();
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, Constants.pageSize);
		List<SubjectDto> subjectDtos = new ArrayList<>();
		Page<Subject> pageSubject = subjectRepository.findByDeletedAndNameContaining(deleted, name, pageRequest);
		List<Subject> listSubject = pageSubject.getContent();
		if (listSubject != null) {
			if (!CollectionUtils.isEmpty(listSubject)) {
				subjectDtos = listSubject.parallelStream().map(subject -> subjectMapper.toDto(subject))
						.collect(Collectors.toList());
			}
		}
		subjectResponse.setSubjectDtos(subjectDtos);
		return subjectResponse;
	}

	public Long countByDeletedAndNameContaining(Boolean deleted, String name) {
		return subjectRepository.countByDeletedAndNameContaining(deleted, name);
	}

	public void save(String name, String code, String description) {
		createFormRequest(name, code, description);
	}

	public void update(Integer id, String name, String code, String description) {
		createFormRequest(id, name, code, description);
	}

	public void delete(Integer id) {
		Subject subject = subjectRepository.findById(id).get();
		subject.setDeleted(true);
		subjectRepository.save(subject);
	}

	public SubjectResponse findByIdAndDeleted(Integer id, Boolean deleted) {
		Subject subject = subjectRepository.findByIdAndDeleted(id, deleted);
		SubjectDto subjectDto = subjectMapper.toDto(subject);
		SubjectResponse subjectResponse = new SubjectResponse();
		subjectResponse.setSubjectDto(subjectDto);
		return subjectResponse;
	}

	public void createFormRequest(String name, String code, String description) {
		SubjectRequest subjectRequest = new SubjectRequest(name, code, description);
		Subject subject = subjectMapper.toEntity(subjectRequest);
		subject.setDeleted(false);
		subjectRepository.save(subject);
	}

	public void createFormRequest(Integer id, String name, String code, String description) {
		SubjectResponse subjectResponse = findByIdAndDeleted(id, false);
		if (subjectResponse != null) {
			SubjectDto subjectDto = subjectResponse.getSubjectDto();
			subjectDto.setName(name);
			subjectDto.setCode(code);
			subjectDto.setDescription(description);
			Subject subject = subjectMapper.toEntity(subjectDto);
			subjectRepository.save(subject);
		}

	}
	public Subject findById(Integer id) {
		return subjectRepository.findByIdAndDeleted(id, false);
	}

}
