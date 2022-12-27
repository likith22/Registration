package com.ola.registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ola.registration.template.UserTemplate;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "user_id")
	int id;
	@Column(name = "user_name")
	String name;
	@Column(name = "user_email")
	String email;
	
	
	public User() {
		super();
	}

	public User(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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
	
	public UserTemplate convertToTemplate(User user) {
		UserTemplate userTemplate = new UserTemplate();
		userTemplate.setId(user.getId());
		userTemplate.setName(user.getName());
		userTemplate.setEmail(user.getEmail());
		
		return userTemplate;
	}
}
