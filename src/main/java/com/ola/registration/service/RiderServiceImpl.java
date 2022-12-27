package com.ola.registration.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.registration.entity.Rider;
import com.ola.registration.exceptions.UserAlreadyExistsException;
import com.ola.registration.repository.RiderRepository;
import com.ola.registration.template.RiderTemplate;

@Service
public class RiderServiceImpl implements RiderService {
	
	@Autowired
	RiderRepository riderRepository;
	
	Logger logger
    = LoggerFactory.getLogger(RiderServiceImpl.class);
	
	
	@Override
	public void registerRider(RiderTemplate newRider) {
		Rider rider = this.riderRepository.findById(newRider.getId()).orElse(null);
		
		if(rider == null) {
			this.riderRepository.saveAndFlush(newRider.convertToRider(newRider));
		}
		else {
			throw new UserAlreadyExistsException("Rider already exists!");
		}
	}

	@Override
	public RiderTemplate getRider(String name) {
		
		Rider rider = this.riderRepository.findByName(name);
		if(null == rider) {
			return null;
		}
		else {
			return rider.convertToTemplate(rider);
		}
		
	}
	
	@Override
	public List<RiderTemplate> getAvailableRiders() {
		List<Rider> riders = List.copyOf(this.riderRepository.findByAvailablity());
		return riders.stream()
		.map(s->Rider.convertToTemplate(s))
		.toList();
		
	}

	@Override
	public void updateStatus(int riderId,String status) {
		Optional<Rider> optionalrider = riderRepository.findById(riderId);
		optionalrider.get().setAvailablity(status);
		riderRepository.save(optionalrider.get());
	}
}
