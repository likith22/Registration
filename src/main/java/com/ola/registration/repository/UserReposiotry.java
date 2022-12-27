package com.ola.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ola.registration.entity.User;

public interface UserReposiotry extends JpaRepository<User, Integer> {
	public User findByName(String name);
}
