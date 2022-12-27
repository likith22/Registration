package com.ola.registration.template;

import com.ola.registration.entity.User;

public class UserTemplate {
	int id;
	String name;
	String email;
	
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
	
	public User convertToUser(UserTemplate userTemplate) {
		User user = new User();
		user.setId(userTemplate.getId());
		user.setEmail(userTemplate.getEmail());
		user.setName(userTemplate.getName());
		return user;
	}
}

