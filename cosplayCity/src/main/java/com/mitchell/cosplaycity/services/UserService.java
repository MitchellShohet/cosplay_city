package com.mitchell.cosplaycity.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mitchell.cosplaycity.models.Profile;
import com.mitchell.cosplaycity.models.User;
import com.mitchell.cosplaycity.models.UserLogin;
import com.mitchell.cosplaycity.repositories.UserRepository;

@Service
public class UserService {
	
	//Repository connection
	
	@Autowired
	private UserRepository userRepository;
	
	//User functions
	
	public List<User> findAll() {
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	}
	
	public User findById(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			return null;
		}
		User oneUser = optionalUser.get();
		return oneUser;
	}
	
	public User register(User newUser, BindingResult result) {
		//validation for registering a new user
		Optional<User> optionalUser = userRepository.findByEmailIs(newUser.getEmail());	
		if(optionalUser.isPresent()) {
			result.rejectValue("email", "unique", "An account is already associated with that Email.");
		}
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("password", "matches", "Password and Password Confirmation must match.");
		}
		if(result.hasErrors()) {
			return null;
		}
		//if the registration is valid, move on to registration logic
		String passwordHashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()); //hashing password for security
		newUser.setPassword(passwordHashed);
		Profile profile = new Profile(newUser); //creates a new profile and establishes the new profile's user as the new user
		newUser.setProfile(profile); //establishes the new user's profile as the new profile
		userRepository.save(newUser);
		return newUser;
	}
	
	public User login(UserLogin userLogin, BindingResult result) {
		//validation for logging in to an account
		Optional<User> optionalUser = userRepository.findByEmailIs(userLogin.getEmail());
		if(optionalUser.isEmpty()) {
			result.rejectValue("email", "empty", "Invalid Email or Password.");
			return null;
		}
		User newUser = optionalUser.get();
		if(!BCrypt.checkpw(userLogin.getPassword(), newUser.getPassword())) {
		    result.rejectValue("email", "Matches", "Invalid Email or Password.");
		}
		if(result.hasErrors()) {
			return null;
		}
		//if the login is valid, return the logged in user
		return newUser;
	}
	
	public User update(User user, BindingResult result) {
		Optional<User> optionalUser = userRepository.findById(user.getId());
		if(optionalUser.isEmpty()) {
			return null;
		}
		userRepository.save(user);
		return user;
	}
}
