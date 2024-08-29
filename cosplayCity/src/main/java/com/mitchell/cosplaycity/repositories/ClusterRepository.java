package com.mitchell.cosplaycity.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mitchell.cosplaycity.models.Cluster;

@Repository
public interface ClusterRepository extends CrudRepository<Cluster, Long> {
	
	List<Cluster> findAll();
	

}
