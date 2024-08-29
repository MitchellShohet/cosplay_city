package com.mitchell.cosplaycity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mitchell.cosplaycity.models.Upcoming;
import com.mitchell.cosplaycity.models.User;
import com.mitchell.cosplaycity.services.UpcomingService;
import com.mitchell.cosplaycity.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UpcomingController {
	
	@Autowired
	private UpcomingService upcomingService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/processNewUpcoming/{userId}") 
	public String processNewUpcoming(
			Model model,
			@PathVariable("userId") Long userId,
			@Valid
			@ModelAttribute("newUpcoming") Upcoming newUpcoming,
			BindingResult result,
			HttpSession session) {
		User user = userService.findById(userId);
		if(result.hasErrors()) {
			model.addAttribute("profile", user.getProfile());
			model.addAttribute("user", user);
			model.addAttribute("newUpcoming", newUpcoming);
			return "editProfile.jsp";
		}
		upcomingService.save(newUpcoming, user.getId(), result);
		return "redirect:/editProfile/" + user.getId();
	}
	
	@GetMapping("/editUpcoming/{upcomingId}")
	public String editUpcoming(
			Model model,
			@PathVariable("upcomingId") Long upcomingId,
			HttpSession session) {
		if(session.getAttribute("loggedInUser") == null) {
			return "redirect:/loginRedirect";}
		Upcoming upcoming = upcomingService.findById(upcomingId);
		if(((User) session.getAttribute("loggedInUser")).getId() != upcoming.getUser().getId()) {
			return "redirect:/loginRedirect";}
		model.addAttribute("upcoming", upcoming);
		return "editUpcoming.jsp";
	}
	
	@PostMapping("/processEditUpcoming/{upcomingId}") 
	public String processEditUpcomings(
			Model model,
			@PathVariable("upcomingId") Long upcomingId,
			@Valid
			@ModelAttribute("upcoming") Upcoming upcoming,
			BindingResult result) {
		upcoming.setId(upcomingId);
		upcoming.setUser(upcomingService.findById(upcomingId).getUser());
		if(result.hasErrors()) {
			model.addAttribute("upcoming", upcoming);
			model.addAttribute("upcomingId", upcomingId);
			return "editUpcoming.jsp";
		}
		upcomingService.update(upcomingId, upcoming, result);
		Long userId = upcoming.getUser().getId();
		return "redirect:/editProfile/" + userId;
	}
	
	@DeleteMapping("/deleteUpcoming/{upcomingId}") 
	public String deleteUpcoming(
			@PathVariable("upcomingId") Long upcomingId,
			HttpSession session) {
		if(session.getAttribute("loggedInUser") == null) {
			return "redirect:/loginRedirect";}
		Upcoming upcoming = upcomingService.findById(upcomingId);
		if(((User) session.getAttribute("loggedInUser")).getId() != upcoming.getUser().getId()) {
			return "redirect:/loginRedirect";}
		User user = (User) session.getAttribute("loggedInUser");
		upcomingService.deleteById(upcomingId);
		return "redirect:/editProfile/" + user.getId();
	}
	

}
