package com.rabiloo.sharedocument.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rabiloo.sharedocument.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	public Page<Feedback> findByDeletedAndEmailContaining(boolean deleted, String email, Pageable pageable);

	public Long countByDeletedAndEmailContaining(boolean deleted, String email);
}
