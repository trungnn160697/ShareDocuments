package com.rabiloo.sharedocument.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rabiloo.sharedocument.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsernameAndDeleted(String username, Boolean deleted);

	User findByEmailAndDeleted(String email, Boolean deleted);

	Long countByDeleted(Boolean deleted);

	Page<User> findByDeletedAndFullNameContaining(Boolean deleted, String search, Pageable pageable);

	Long countByDeletedAndFullNameContaining(Boolean deleted, String search);

	User findByIdAndDeleted(Integer id,Boolean deleted);
	
	Page<User> findByUsernameContainingOrderByIdDesc(String search, Pageable pageable);

	Long countByUsernameContaining(String search);
	
	
	

	
}
