package com.ola.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.registration.entity.User;
import com.ola.registration.exceptions.UserAlreadyExistsException;
import com.ola.registration.repository.UserReposiotry;
import com.ola.registration.template.UserTemplate;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserReposiotry userRepo;

	@Override
	public UserTemplate getUser(String name) {
		User user = this.userRepo.findByName(name);
		if(null == user) {
			return null;
		}
		return user.convertToTemplate(user);
	}

	@Override
	public void createUser(UserTemplate newuser) {
		User user = this.userRepo.findById(newuser.getId()).orElse(null);
		if(user == null) {
			this.userRepo.saveAndFlush(newuser.convertToUser(newuser));
		}
		else {
			throw new UserAlreadyExistsException("User already exists!");
		}
	}

}
