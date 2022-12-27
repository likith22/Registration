package com.ola.registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ola.registration.template.RiderTemplate;

@Entity
@Table(name = "rider")
public class Rider {
	@Id
	@Column(name = "id")
	int id;
	@Column(name = "name")
	String name;
	@Column(name = "email")
	String email;
	@Column(name = "availablity")
	String availablity;
	
	
	
	public Rider() {
		super();
	}

	public Rider(int id, String name, String email,String availablity) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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
	public String getAvailablity() {
		return availablity;
	}

	public void setAvailablity(String availablity) {
		this.availablity = availablity;
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
	
	public static RiderTemplate convertToTemplate(Rider rider) {
		RiderTemplate riderTemplate = new RiderTemplate();
		riderTemplate.setId(rider.getId());
		riderTemplate.setName(rider.getName());
		riderTemplate.setEmail(rider.getEmail());
		riderTemplate.setAvailablity(rider.getAvailablity());
		
		return riderTemplate;
	}
}
