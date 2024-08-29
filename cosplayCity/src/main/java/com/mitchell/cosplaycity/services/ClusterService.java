package com.mitchell.cosplaycity.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.mitchell.cosplaycity.models.Cluster;
import com.mitchell.cosplaycity.models.User;
import com.mitchell.cosplaycity.repositories.ClusterRepository;

@Service
public class ClusterService {
	
	@Autowired
	private ClusterRepository clusterRepository;
	
	@Autowired
	private FilesService filesService;
	
	@Autowired
	private UserService userService;
	
	public List<Cluster> findAll() {
		List<Cluster> allClusters = clusterRepository.findAll();
		return allClusters;
	}
	
	public Cluster findById(Long clusterId) {
		Optional<Cluster> optionalCluster = clusterRepository.findById(clusterId);
		if(optionalCluster.isEmpty()) {
			return null;
		}
		Cluster cluster = optionalCluster.get();
		return cluster;		
	}

	public Cluster saveCluster(Cluster newCluster, User user, List<MultipartFile> rawContent, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		ArrayList<String> content = new ArrayList<String>();
		if(!rawContent.isEmpty()) {
			for(MultipartFile eachContent : rawContent) {
				content.add(filesService.save(eachContent));
			}
		}
		newCluster.setContent((List<String>)content);
		newCluster.setUser(user);
		clusterRepository.save(newCluster);
		return newCluster;		
	}
	
	public Cluster update(Long clusterId, Cluster cluster, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		User user = this.findById(clusterId).getUser();
		cluster.setId(clusterId);
		cluster.setUser(user);
		clusterRepository.save(cluster);
		return cluster;
	}
	
	public void deleteById(Long clusterId) {
		clusterRepository.deleteById(clusterId);
	}

}
