package com.rabiloo.sharedocument.controller.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabiloo.sharedocument.dto.user.UserDto;
import com.rabiloo.sharedocument.dto.user.UserResponse;
import com.rabiloo.sharedocument.service.UserService;
import com.rabiloo.sharedocument.util.Constants;
import com.rabiloo.sharedocument.util.Encode;
import com.rabiloo.sharedocument.util.MessageUtil;
import com.rabiloo.sharedocument.validation.UserValidation;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> save(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("fullName") String fullName,
			@RequestParam("email") String email, @RequestParam("phone") String phone,
			@RequestParam("image") MultipartFile image, HttpSession session) {

		if (!UserValidation.validateForm(username, password, fullName, email, phone)) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Không được bỏ trống các ô"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}

		if (!UserValidation.validatePassword(password)) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Mật khẩu phải có ít nhất 6 kí tự"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}

		if (!UserValidation.validateEmail(email)) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Email không đúng định dạng"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}

		if (!UserValidation.validatePhone(phone)) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Số điện thoại không đúng định dạng"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}

		if (userService.findByUsernameAndDeleted(username, false) != null) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Đã tồn tại tên đăng nhập này"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}
		if (userService.findByEmailAndDeleted(email, false) != null) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Đã tồn tại tên email này"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}

		userService.save(username, password, fullName, email, phone, image);
		UserResponse userResponse = new UserResponse(new MessageUtil("Tạo tài khoản thành công, đăng nhập ngay?", 200));
		session.setAttribute("userRegister", userService.countByRegister());
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@PostMapping("/forgotPassword")
	public ResponseEntity<UserResponse> forgotPassword(@RequestParam("email") String email) {
		if (userService.forgotPassword(email) == false) {
			UserResponse userResponse = new UserResponse(
					new MessageUtil("Không tồn tại email " + email + " trong hệ thống."));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}
		// userService.forgotPassword(email);
		UserResponse userResponse = new UserResponse(new MessageUtil("Hãy kiểm tra email để lấy mật khẩu mới", 200));
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/change-password")
	public ResponseEntity<UserResponse> changePassword(@RequestParam("id") Integer id,
			@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmNewPassword") String confirmNewPassword, HttpSession session) {
		UserResponse userLogin = (UserResponse) session.getAttribute("userLogin");
		UserDto userDto = userLogin.getUserDto();
		if (!Encode.checkPassword(currentPassword, userDto.getPassword())) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Nhập sai mật khẩu hiện tại"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}
		if (!UserValidation.validatePassword(newPassword)) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Mật khẩu phải có ít nhất 6 kí tự."));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}
		if (!newPassword.equals(confirmNewPassword)) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Nhập lại mật khẩu sai."));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}
		userService.changePassword(newPassword, id);
		UserResponse userResponse = new UserResponse(new MessageUtil("Thay đổi mật khẩu thành công", 200));
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@PostMapping("/change-info")
	public ResponseEntity<UserResponse> changeInfo(@RequestParam("id") Integer id,
			@RequestParam("fullName") String fullName, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam(name = "image", required = false) MultipartFile file,
			HttpSession session) {
		UserResponse userLogin = (UserResponse) session.getAttribute("userLogin");
		UserDto userDto = userLogin.getUserDto();
		if (!UserValidation.validateForm(fullName, email, phone)) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Không được bỏ trống các ô"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}
		if (!UserValidation.validateEmail(email)) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Email không đúng định dạng"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}

		if (!UserValidation.validatePhone(phone)) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Số điện thoại không đúng định dạng"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}
		if (!userDto.getEmail().equals(email) && userService.findByEmailAndDeleted(email, false) != null) {
			UserResponse userResponse = new UserResponse(new MessageUtil("Đã tồn tại tên email này"));
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}
		UserResponse userResponse = userService.update(id, fullName, email, phone, file);
		userResponse.setMessage(new MessageUtil("Cập nhật thông tin thành công", 200));
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);

	}

	@GetMapping("/listuser")
	public ResponseEntity<UserResponse> getListUser(HttpServletRequest request) {
		String search = "";
		if (request.getParameter("sSearch") != null) {
			search = request.getParameter("sSearch");
		}
		Integer pageDisplayLength = 1;
		if (request.getParameter("iDisplayLength") != null)
			pageDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		Constants.pageSize = pageDisplayLength;
		Integer iDisplayStart = 1;
		if (request.getParameter("iDisplayStart") != null)
			iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		Integer page = (iDisplayStart / pageDisplayLength);
		UserResponse userResponse = userService.findByFullNameContaining(search, page);
		userResponse.setAaData(userResponse.getUserDtos());
		userResponse.setiTotalDisplayRecords(userService.countByFullNameContaining(search));
		userResponse.setiTotalRecords(userService.countByFullNameContaining(search));
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<UserResponse> delete(@PathVariable("id") Integer id) {
		userService.delete(id);
		return new ResponseEntity<UserResponse>(HttpStatus.OK);
	}

	@PutMapping("/user/upgrade/{id}")
	public ResponseEntity<UserResponse> upgrade(@PathVariable("id") Integer id, HttpSession session) {
		userService.upgrade(id);
		session.setAttribute("userRegister", userService.countByRegister());
		return new ResponseEntity<UserResponse>(HttpStatus.OK);
	}
}
