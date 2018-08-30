package com.rabiloo.sharedocument.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rabiloo.sharedocument.service.DocumentService;
import com.rabiloo.sharedocument.service.ExamService;
import com.rabiloo.sharedocument.service.UserService;
import com.rabiloo.sharedocument.util.Constants;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private ExamService examService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String manager(Model model,HttpSession session) {
		model.addAttribute("totalUser", userService.countByDeleted(false));
		model.addAttribute("totalDocument", documentService.countAll());
		model.addAttribute("numberOfDownload", documentService.coutnNumberOfDownload());
		model.addAttribute("totalExam", examService.countAll());
		session.setAttribute("active_menu",0);
		return "manager/index";
	}

	@GetMapping("/403")
	public String page403() {
		return "403";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@GetMapping("/display")
	public void displayImage(HttpServletResponse response, @RequestParam("image") String name) {
		File file = new File(Constants.URL_IMAGE_USER + name);
		response.setContentType("image/*");
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		BufferedInputStream inStrem;
		try {
			inStrem = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inStrem.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			outStream.flush();
			inStrem.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@GetMapping("/display-document")
	public void displayImageDocument(HttpServletResponse response, @RequestParam("image") String name) {
		File file = new File(Constants.URL_IMAGE_DOCUMENT + name);
		response.setContentType("image/*");
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		BufferedInputStream inStrem;
		try {
			inStrem = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inStrem.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			outStream.flush();
			inStrem.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
