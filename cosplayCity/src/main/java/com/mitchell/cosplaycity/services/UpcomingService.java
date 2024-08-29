package com.mitchell.cosplaycity.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.mitchell.cosplaycity.models.Upcoming;
import com.mitchell.cosplaycity.models.User;
import com.mitchell.cosplaycity.repositories.UpcomingRepository;

@Service
public class UpcomingService {

	@Autowired
	UpcomingRepository upcomingRepository;
	
	@Autowired
	UserService userService;
	
	public List<Upcoming> findAll() {
		List<Upcoming> allUpcomings = upcomingRepository.findAll();
		return allUpcomings;
	}
	
	public Upcoming findById(Long upcomingId) {
		Optional<Upcoming> optionalUpcoming = upcomingRepository.findById(upcomingId);
		if(optionalUpcoming.isEmpty()) {
			return null;
		}
		Upcoming upcoming = optionalUpcoming.get();
		return upcoming;
	}
	
	public Upcoming save(Upcoming newUpcoming, Long userId, BindingResult result) {
		User user = userService.findById(userId);
		newUpcoming.setUser(user);
		upcomingRepository.save(newUpcoming);
		user.getUpcomings().add(newUpcoming);
		userService.update(user, result);
		return newUpcoming;
	}
	
	public Upcoming update(Long upcomingId, Upcoming newUpcoming, BindingResult result) {
		Optional<Upcoming> optionalUpcoming = upcomingRepository.findById(upcomingId);
		if(optionalUpcoming.isEmpty()) {
			System.out.println("nope");
			return null;
		}
		User user = userService.findById(newUpcoming.getUser().getId());
		newUpcoming.setId(upcomingId);
		for(Upcoming eachUpcoming : user.getUpcomings()) {
			if(eachUpcoming.getId() == upcomingId) {
				eachUpcoming.setName(newUpcoming.getName());
				System.out.println(eachUpcoming.getId());
				System.out.println(newUpcoming.getName());
			}
		}
		newUpcoming.setUser(user);
		upcomingRepository.save(newUpcoming);
		userService.update(user, result);
		return newUpcoming;
	}
	
	public void deleteById(Long upcomingId) {
		Upcoming upcoming = this.findById(upcomingId);
		User user = userService.findById(upcoming.getUser().getId());
		user.getUpcomings().remove(upcoming);
		userService.update(user, null);
		upcomingRepository.deleteById(upcomingId);
	}
	
	
}
