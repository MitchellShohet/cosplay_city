package com.mitchell.cosplaycity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mitchell.cosplaycity.models.User;
import com.mitchell.cosplaycity.models.UserLogin;
import com.mitchell.cosplaycity.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("userLogin", new UserLogin());
		return "login.jsp";
	}
	
	@PostMapping("/processLogin")
	public String processLogin(
			Model model,
			@Valid
			@ModelAttribute("userLogin") UserLogin userLogin,
			BindingResult result,
			HttpSession session) {
		User newUser = userService.login(userLogin, result);
		if(newUser != null) {
			session.setAttribute("loggedInUser", newUser);
			return "redirect:/discover";
		}
		else {
			model.addAttribute("userLogin", userLogin);
			return "login.jsp";
		}
	}
	
	@GetMapping("/register")
	public String register(
			Model model) {
		model.addAttribute("newUser", new User());
		return "register.jsp";		
	}
	
	@PostMapping("/processRegister")
	public String processRegister(
			Model model,
			@Valid
			@ModelAttribute("newUser") User newUser,
			BindingResult result,
			HttpSession session) {
		User processedUser = userService.register(newUser, result);
		if(processedUser != null) {			
			session.setAttribute("loggedInUser", processedUser);
			return "redirect:/discover";
		}
		else {
			model.addAttribute("newUser", newUser);
			return "register.jsp";
		}
	}
	
	@GetMapping("/processLogout")
	public String processLogout(
			HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/loginRedirect")
	public String loginRedirect(
			Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("loginNeeded", "Must be logged in to view.");
		return "register.jsp";
		
	}
	
}
