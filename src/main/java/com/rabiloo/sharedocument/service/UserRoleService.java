package com.rabiloo.sharedocument.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rabiloo.sharedocument.entity.UserRole;
import com.rabiloo.sharedocument.repository.UserRoleRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserRoleService {
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public UserRole findById(Integer id) {
		return userRoleRepository.findById(id).get();
	}
}
