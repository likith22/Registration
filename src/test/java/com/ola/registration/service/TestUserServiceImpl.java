package com.ola.registration.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.ola.registration.entity.User;
import com.ola.registration.repository.UserReposiotry;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserServiceImpl.class)
public class TestUserServiceImpl {
	@MockBean
	UserReposiotry repository;
	
	@Autowired
	UserServiceImpl service;
	
	@Test
	void testGetUserSuccessResponse() {
		User newuser = new User(101,"Likith","vedh@gmail.com");
		when(this.repository.findByName("Likith")).thenReturn(newuser);
		assertEquals(newuser.getId(), service.getUser("Likith").getId());
		
	}
//	@Test
//	void testGetUserErrorResponse() {
//		User newuser = new User(101,"Likith","vedh@gmail.com");
//		when(this.repository.findByName("Lik")).thenThrow(new NullPointerException());
//		exceptE
//	}
}
