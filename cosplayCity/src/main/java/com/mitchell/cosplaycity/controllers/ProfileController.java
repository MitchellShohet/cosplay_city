package com.mitchell.cosplaycity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mitchell.cosplaycity.models.Profile;
import com.mitchell.cosplaycity.models.Upcoming;
import com.mitchell.cosplaycity.models.User;
import com.mitchell.cosplaycity.services.ProfileService;
import com.mitchell.cosplaycity.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile/{profileId}") 
	public String profile(
			Model model,
			@PathVariable ("profileId") Long profileId
			) {
		Profile profile = profileService.findById(profileId);
		model.addAttribute(profile);
		return "profile.jsp";
	}
	
	@GetMapping("/editProfile/{userId}")
	public String editProfile(
			Model model,
			@PathVariable("userId") Long userId,
			HttpSession session) {
		if(session.getAttribute("loggedInUser") == null) {
			return "redirect:/loginRedirect";}
		User user = userService.findById(userId);
		if(((User) session.getAttribute("loggedInUser")).getId() != userId) {
			return "redirect:/loginRedirect";}
		Profile profile = profileService.findById(user.getProfile().getId());
		if(((User) session.getAttribute("loggedInUser")).getId() != profile.getUser().getId()) {
			return "redirect:/user/discover";
		}
		model.addAttribute("user", user);
		model.addAttribute("profile", profile);
		model.addAttribute("newUpcoming", new Upcoming());
		return "editProfile.jsp";
	}
	
	@PostMapping("/processEditProfile/{userId}")
	public String processEditProfile(
			Model model,
			@PathVariable("userId") Long userId,
			@RequestParam("file") MultipartFile photo,
			@Valid
			@ModelAttribute("profile") Profile profile,
			BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("profile", profile);
			model.addAttribute("userId", userId);
			return "editProfile.jsp";
		}
		Profile processedProfile = profileService.update(profile, userId, photo, result);
		return "redirect:/profile/" + processedProfile.getUser().getId();
	}

}
