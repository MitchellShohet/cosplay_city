package com.mitchell.cosplaycity.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mitchell.cosplaycity.models.Upcoming;

@Repository
public interface UpcomingRepository extends CrudRepository<Upcoming, Long>{
	
	public List<Upcoming> findAll();

}
