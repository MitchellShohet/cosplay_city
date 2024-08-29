package com.mitchell.cosplaycity.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mitchell.cosplaycity.models.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long>{
	
	public List<Profile> findAll();

}
