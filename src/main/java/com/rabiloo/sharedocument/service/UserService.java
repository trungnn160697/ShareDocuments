package com.rabiloo.sharedocument.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.rabiloo.sharedocument.dto.user.UserDto;
import com.rabiloo.sharedocument.dto.user.UserRequest;
import com.rabiloo.sharedocument.dto.user.UserResponse;
import com.rabiloo.sharedocument.entity.Document;
import com.rabiloo.sharedocument.entity.User;
import com.rabiloo.sharedocument.entity.UserRole;
import com.rabiloo.sharedocument.mapper.UserMapper;
import com.rabiloo.sharedocument.repository.UserRepository;
import com.rabiloo.sharedocument.util.Constants;
import com.rabiloo.sharedocument.util.Encode;
import com.rabiloo.sharedocument.util.MailUtil;
import com.rabiloo.sharedocument.util.UploadUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	public HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameAndDeleted(username, false);
		if (user == null) {
			throw new UsernameNotFoundException("Không tồn tại username này");
		}
		Set<GrantedAuthority> grantedAuthority = new HashSet<>();
		for (UserRole role : user.getUserRoles()) {
			grantedAuthority.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
		}
		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), grantedAuthority);
		session.setAttribute("userLogin", getUserLogin(userDetails));
		session.setAttribute("userRegister", countByRegister());
		return userDetails;
	}

	public void save(String username, String password, String fullName, String email, String phone,
			MultipartFile file) {
		createFormRequest(username, password, fullName, email, phone, file);
	}

	public UserResponse update(Integer id, String fullName, String email, String phone, MultipartFile file) {
		return createFormRequest(id, fullName, email, phone, file);
	}

	public void createFormRequest(String username, String password, String fullName, String email, String phone,
			MultipartFile file) {
		UserRequest userRequest = new UserRequest(username, Encode.encode(password), fullName, email, phone,
				UploadUtil.upload(file, Constants.URL_IMAGE_USER, "image"));
		User user = userMapper.toEntity(userRequest);
		List<UserRole> userRoles = new ArrayList<>();
		userRoles.add(userRoleService.findById(2));
		user.setUserRoles(userRoles);
		user.setDeleted(null);
		userRepository.save(user);
	}

	public UserResponse createFormRequest(Integer id, String fullName, String email, String phone, MultipartFile file) {
		UserResponse userLogin = (UserResponse) session.getAttribute("userLogin");
		User user = userRepository.findById(id).get();
		user.setFullName(fullName);
		user.setEmail(email);
		user.setPhone(phone);
		if (file != null) {
			user.setImage(UploadUtil.upload(file, Constants.URL_IMAGE_USER, "image"));
		} else {
			user.setImage(userLogin.getUserDto().getImage());
		}
		userLogin = new UserResponse(userMapper.toDto(user));
		session.setAttribute("userLogin", userLogin);
		userRepository.save(user);
		return new UserResponse(userMapper.toDto(user));
	}

	public Boolean forgotPassword(String email) {
		User user = findByEmailAndDeleted(email, false);
		if (user == null) {
			return false;
		}
		Integer randomPassword = Encode.randomPassword();
		user.setPassword(Encode.encode(randomPassword.toString()));
		userRepository.save(user);
		emailSender.send(MailUtil.sendMail(email, "Thay đổi mật khẩu",
				"Bạn đã thay đổi mật khẩu thành công.\nMật khẩu mới của bạn là " + randomPassword + "."));
		return true;
	}

	public void delete(Integer id) {
		User user = userRepository.findById(id).get();
		List<Document> documents =  user.getListDocument();
		for (int i = 0; i < documents.size(); i++) {
			documents.get(i).setDeleted(true);
		}
		user.setListDocument(documents);
		user.setDeleted(true);
		userRepository.save(user);
		emailSender.send(MailUtil.sendMail(user.getEmail(), "Vô hiệu hóa tài khoản",
				"Tài khoản của bạn tại website Share-Document đã bị vô hiệu hóa. Liên hệ với website để biết lí do."));
	}

	public void upgrade(Integer id) {
		User user = userRepository.findById(id).get();
		List<Document> documents =  user.getListDocument();
		for (int i = 0; i < documents.size(); i++) {
			documents.get(i).setDeleted(false);
		}
		user.setListDocument(documents);
		user.setDeleted(false);
		userRepository.save(user);
		emailSender.send(MailUtil.sendMail(user.getEmail(), "Active tài khoản",
				"Tài khoản của bạn tại website Share-Document đã được active. Đăng nhập ngay."));
	}

	public User findByUsernameAndDeleted(String username, Boolean deleted) {
		return userRepository.findByUsernameAndDeleted(username, deleted);
	}

	public User findByEmailAndDeleted(String email, Boolean deleted) {
		return userRepository.findByEmailAndDeleted(email, deleted);
	}

	public UserResponse getUserLogin(UserDetails userDetails) {
		User user = userRepository.findByUsernameAndDeleted(userDetails.getUsername(), false);
		UserResponse userResponse = new UserResponse(userMapper.toDto(user));
		return userResponse;
	}

	public Long countByDeleted(Boolean deleted) {
		return userRepository.countByDeleted(deleted);
	}

	public void changePassword(String password, Integer id) {
		User user = userRepository.findById(id).get();
		user.setPassword(Encode.encode(password));
		UserResponse userLogin = new UserResponse();
		userLogin.setUserDto(userMapper.toDto(user));
		session.setAttribute("userLogin", userLogin);
		userRepository.save(user);
	}

	public Long countByDeletedAndFullNameContaining(Boolean deleted, String search) {
		return userRepository.countByDeletedAndFullNameContaining(deleted, search);
	}

	public UserResponse findByDeletedAndFullNameContaining(Boolean deleted, String search, Integer page) {
		UserResponse userResponse = new UserResponse();
		List<UserDto> userDtos = new ArrayList<>();
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, Constants.pageSize);
		Page<User> pageUser = userRepository.findByDeletedAndFullNameContaining(deleted, search, pageRequest);
		List<User> listUser = pageUser.getContent();
		if (listUser != null) {
			if (!CollectionUtils.isEmpty(listUser)) {
				userDtos = listUser.parallelStream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
			}
		}
		userResponse.setUserDtos(userDtos);
		return userResponse;
	}

	public User findById(Integer id) {
		return userRepository.findByIdAndDeleted(id, false);
	}

	public Long countByFullNameContaining(String search) {
		return userRepository.countByUsernameContaining(search);
	}

	public UserResponse findByFullNameContaining(String search, Integer page) {
		UserResponse userResponse = new UserResponse();
		List<UserDto> userDtos = new ArrayList<>();
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, Constants.pageSize);
		Page<User> pageUser = userRepository.findByUsernameContainingOrderByIdDesc(search, pageRequest);
		List<User> listUser = pageUser.getContent();
		if (listUser != null) {
			if (!CollectionUtils.isEmpty(listUser)) {
				userDtos = listUser.parallelStream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
			}
		}
		userResponse.setUserDtos(userDtos);
		return userResponse;
	}

	public Long countByRegister() {
		return userRepository.countByDeleted(null);
	}

}
