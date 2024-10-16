package com.mitchell.cosplaycity.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FilesService {
	private final Path root = Paths.get("uploads"); //defines path source for photo storage in application files
	
	public void init() throws IOException { //creates uploads folder if it doesn't exist
		if(! Files.exists(root)) {
			try {
				Files.createDirectory(root);
			} catch(IOException e) {
				throw new RuntimeException("Could not initialize folder for upload!");
			}
		}
	}
	
	public String save(MultipartFile file, String userName, String clusterTitle) { //receives a user uploaded photo, saves to the uploads folder, returns path as a string
		try {
			Path savePath = this.root.resolve(userName + clusterTitle + file.getOriginalFilename());
			Files.copy(file.getInputStream(), savePath);
			return savePath.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Resource load(String filename) { //receives a path as a string, retrieves and returns the photo from the path
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			} 
			else {
				throw new RuntimeException("Could not read the file!");
			}
		}
		catch(MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
	
	public boolean checkReupload(String filename) { //receives a path as a string, retrieves and returns the photo from the path
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return true;
			} 
			else {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
	}

}
