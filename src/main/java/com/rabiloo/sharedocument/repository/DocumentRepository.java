package com.rabiloo.sharedocument.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rabiloo.sharedocument.entity.Document;
import com.rabiloo.sharedocument.entity.Subject;
import com.rabiloo.sharedocument.entity.User;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
	Page<Document> findByDeletedAndNameContaining(boolean deleted, String name, Pageable pageable);

	Long countByDeletedAndNameContaining(boolean deleted, String name);

	Page<Document> findByDeletedAndUserAndNameContaining(boolean deleted, User user, String name, Pageable pageable);

	Long countByDeletedAndUserAndNameContaining(boolean deleted, User user, String name);

	Page<Document> findByDeletedAndSubjectAndNameContaining(boolean deleted, Subject subject, String name,
			Pageable pageable);

	Long countByDeletedAndSubjectAndNameContaining(boolean deleted, Subject subject, String name);
}
