package com.ola.registration.template;

import com.ola.registration.entity.Rider;

public class RiderTemplate {
	int id;
	String name;
	String email;
	String availablity;
	
	public String getAvailablity() {
		return availablity;
	}
	public void setAvailablity(String availablity) {
		this.availablity = availablity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static Rider convertToRider(RiderTemplate riderTemplate) {
		Rider rider = new Rider();
		rider.setId(riderTemplate.getId());
		rider.setEmail(riderTemplate.getEmail());
		rider.setName(riderTemplate.getName());
		rider.setAvailablity(riderTemplate.getAvailablity());
		return rider;
	}
}
