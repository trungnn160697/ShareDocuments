package com.rabiloo.sharedocument.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rabiloo.sharedocument.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
	Page<Document> findByDeletedAndNameContaining(boolean deleted, String name, Pageable pageable);

	Long countByDeletedAndNameContaining(boolean deleted, String name);
}
