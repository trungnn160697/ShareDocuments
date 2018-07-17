package com.rabiloo.sharedocument.mapper;

import org.springframework.stereotype.Repository;

import com.rabiloo.sharedocument.dto.user.UserDto;
import com.rabiloo.sharedocument.dto.user.UserRequest;
import com.rabiloo.sharedocument.entity.User;

@Repository("userMapper")
public class UserMapper {
	public User toEntity(UserRequest userRequest) {
		return new User(userRequest.getUsername(), userRequest.getPassword(), userRequest.getFullName(),
				userRequest.getEmail(), userRequest.getPhone(), userRequest.getImage());
	}

	public UserDto toDto(User user) {
		return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail(),
				user.getPhone(), user.getImage(), user.getUserRoles().get(0).getName());
	}
}
