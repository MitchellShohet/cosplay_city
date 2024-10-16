package com.mitchell.cosplaycity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.mitchell.cosplaycity.models.Profile;
import com.mitchell.cosplaycity.models.User;
import com.mitchell.cosplaycity.repositories.ProfileRepository;

@Service
public class ProfileService {
	
	//Repository and other model Services connections
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private FilesService filesService;
	
	@Autowired
	private UserService userService;
	
	//Profile functions
	
	public List<Profile> findAll() {
		return profileRepository.findAll();
	}
	
	public Profile findById(Long profileId) {
		Optional<Profile> optionalProfile = profileRepository.findById(profileId);
		if(optionalProfile.isEmpty()) {
			return null;
		}
		Profile profile = optionalProfile.get();
		return profile;
	}

	public Profile update(Profile profile, Long userId, MultipartFile photo, BindingResult result) {
		User user = userService.findById(userId);
		profile.setUser(user);
		profile.setProfilePhoto(filesService.save(photo, user.getUserName(), "profilePhoto"));
		profile.setId(user.getProfile().getId());
		profileRepository.save(profile);
		return profile;
	}

}

