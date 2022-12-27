package com.ola.registration.service;

import com.ola.registration.template.UserTemplate;

public interface UserService {
	public UserTemplate getUser(String name);
	
	public void createUser(UserTemplate newuser);
}
