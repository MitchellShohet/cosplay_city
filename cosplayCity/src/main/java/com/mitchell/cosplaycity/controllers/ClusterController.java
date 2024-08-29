package com.mitchell.cosplaycity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mitchell.cosplaycity.models.Cluster;
import com.mitchell.cosplaycity.models.User;
import com.mitchell.cosplaycity.services.ClusterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ClusterController {
	
	@Autowired
	private ClusterService clusterService;
	
	@GetMapping("/discover")
	public String discover(
			Model model,
			HttpSession session) {
		List<Cluster> allClusters = clusterService.findAll();
		model.addAttribute("allClusters", allClusters);
		model.addAttribute("session", session);
		return "discover.jsp";
	}
	
	@GetMapping("/newCluster")
	public String newCluster(
			Model model,
			HttpSession session) {
		if(session.getAttribute("loggedInUser") == null) {
			return "redirect:/loginRedirect";}
		model.addAttribute("newCluster", new Cluster());
		return "newCluster.jsp";
	}
	
	@PostMapping("/processNewCluster")
	public String processNewCluster( 
			Model model,
			@Valid
			@ModelAttribute("newCluster") Cluster newCluster,
			BindingResult result,
			@RequestParam("file") List<MultipartFile> rawContent,
			HttpSession session) {
		if(result.hasErrors()) {
			return "newCluster.jsp";
		}
		Cluster cluster = clusterService.saveCluster(newCluster, (User)session.getAttribute("loggedInUser"), rawContent, result);
		return "redirect:/profile/" + newCluster.getUser().getId();
	}
	
	@GetMapping("/clusterPage/{clusterId}")
	public String clusterPage(
			Model model,
			@PathVariable("clusterId") Long clusterId) {
		Cluster cluster = clusterService.findById(clusterId);
		model.addAttribute("cluster", cluster);
		return "clusterPage.jsp";
	}
	
	@GetMapping("/editCluster/{clusterId}")
	public String editCluster(
			Model model,
			@PathVariable("clusterId") Long clusterId,
			HttpSession session) {
		if(session.getAttribute("loggedInUser") == null) {
				return "redirect:/loginRedirect";}
		Cluster cluster = clusterService.findById(clusterId);
		if(((User) session.getAttribute("loggedInUser")).getId() != cluster.getUser().getId()) {
			return "redirect:/loginRedirect";}
		model.addAttribute("cluster", cluster);
		return "editCluster.jsp";
	}
	
	@PostMapping("/processEditCluster/{clusterId}")
	public String processEditCluster(
			Model model,
			@PathVariable("clusterId") Long clusterId,
			@Valid
			@ModelAttribute("cluster") Cluster cluster,
			BindingResult result,
			HttpSession session) {
		if(result.hasErrors()) {
			cluster.setId(clusterId);
			model.addAttribute("cluster", cluster);			
			return "editCluster.jsp";
		}
		Cluster processedCluster = clusterService.update(clusterId, cluster, result);
		return "redirect:/clusterPage/" + processedCluster.getId();
	}
	
	@DeleteMapping("/deleteCluster/{clusterId}")
	public String deleteCluster(
			@PathVariable("clusterId") Long clusterId,
			HttpSession session) {
		if(session.getAttribute("loggedInUser") == null) {
			return "redirect:/loginRedirect";}
		Cluster cluster = clusterService.findById(clusterId);
		if(((User) session.getAttribute("loggedInUser")).getId() != cluster.getUser().getId()) {
			return "redirect:/loginRedirect";}
		clusterService.deleteById(clusterId);
		return "redirect:/discover";
	}

}
