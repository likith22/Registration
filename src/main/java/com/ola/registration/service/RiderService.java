package com.ola.registration.service;

import java.util.List;

import com.ola.registration.entity.Rider;
import com.ola.registration.template.RiderTemplate;

public interface RiderService {
	public void registerRider(RiderTemplate newRider);
	
	public RiderTemplate getRider(String name);
	
	public List<RiderTemplate> getAvailableRiders();
	
	public void updateStatus(int riderId,String status);
}
