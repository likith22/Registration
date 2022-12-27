package com.ola.registration.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ola.registration.entity.Rider;

public interface RiderRepository extends JpaRepository<Rider, Integer> {
	public Rider findByName(String name);
	
	@Query(value = "SELECT * FROM ola.rider where availablity = 'available'"
			,nativeQuery = true)
	public Collection<Rider> findByAvailablity();
}
