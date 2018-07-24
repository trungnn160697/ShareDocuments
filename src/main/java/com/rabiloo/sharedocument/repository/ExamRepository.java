package com.rabiloo.sharedocument.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rabiloo.sharedocument.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
	Page<Exam> findByDeletedAndNameContaining(Boolean deleted,String name,Pageable pageable);

	Long countByDeletedAndNameContaining(Boolean deleted,String name);
	
	Long countByDeleted(Boolean deleted);

}
