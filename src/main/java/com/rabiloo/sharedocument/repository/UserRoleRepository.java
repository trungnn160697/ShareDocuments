package com.rabiloo.sharedocument.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabiloo.sharedocument.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
