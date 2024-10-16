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
	
	//Repository and other model Services connections
	
	@Autowired
	private ClusterRepository clusterRepository;
	
	@Autowired
	private FilesService filesService;
	
	//Cluster Functions
	
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
		ArrayList<String> content = new ArrayList<String>(); //creates new ArrayList to store each photo's path as a string
		if(!rawContent.isEmpty()) {
			for(MultipartFile eachContent : rawContent) {
				content.add(filesService.save(eachContent, user.getUserName(), newCluster.getTitle())); //saves each photo to uploads folder, and adds the path to the cluster's content ArrayList
			}
		}
		newCluster.setContent((List<String>)content);
		newCluster.setUser(user);
		clusterRepository.save(newCluster);
		return newCluster;		
	}
	
	public Cluster update(Long clusterId, Cluster cluster, List<MultipartFile> rawContent, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		ArrayList<String> content = new ArrayList<String>(); //creates new ArrayList to store each photo's path as a string
		User user = this.findById(clusterId).getUser();
		if(!rawContent.isEmpty()) {
			for(MultipartFile eachContent : rawContent) {
				boolean optionalContent = (filesService.checkReupload(user.getUserName() + cluster.getTitle() + eachContent.getOriginalFilename())); //tests each photo if it exists previously in uploads folder
				if(optionalContent) { 
					content.add("uploads/" + user.getUserName() + cluster.getTitle() + eachContent.getOriginalFilename()); //if the photo already exists in the uploads folder, the raw file path is used as a string
				}
				else {
					content.add(filesService.save(eachContent, user.getUserName(), cluster.getTitle())); //if the photo is a new upload, it is added to the uploads folder and the path saved returned as a string
				}
			}
		}
		cluster.setContent((List<String>)content);
		cluster.setId(clusterId);
		cluster.setUser(user);
		clusterRepository.save(cluster); //saves the updated cluster to database
		return cluster;
	}
	
	public void deleteById(Long clusterId) {
		clusterRepository.deleteById(clusterId);
	}

}
