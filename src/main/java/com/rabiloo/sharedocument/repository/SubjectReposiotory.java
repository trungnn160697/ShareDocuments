package com.rabiloo.sharedocument.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rabiloo.sharedocument.entity.Subject;

public interface SubjectReposiotory extends JpaRepository<Subject, Integer> {
	Page<Subject> findByDeletedAndNameContaining(Boolean deleted, String name, Pageable pageable);

	Long countByDeletedAndNameContaining(Boolean deleted, String name);
	
	Subject findByIdAndDeleted(Integer id,Boolean deleted);
}
