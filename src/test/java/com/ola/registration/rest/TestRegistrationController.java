package com.ola.registration.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ola.registration.service.UserServiceImpl;
import com.ola.registration.template.UserTemplate;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(RegistrationController.class)
class TestRegistrationController {

	@MockBean
	UserServiceImpl service;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void testGetUserSuccess() throws Exception {
		UserTemplate usertemplate = new UserTemplate();
		usertemplate.setId(101);
		usertemplate.setName("Likith");
		usertemplate.setEmail("vedhlikhith@gmail.com");
		
		when(service.getUser("Likith")).thenReturn(usertemplate);
		String uri = "/api/v1/registration/dataRequest/user/{name}";
		mockMvc.perform(get(uri,"Likith") 
		          .accept(MediaType.APPLICATION_JSON))
		          .andExpect(status().isAccepted())
		          .andExpect(content().json("{'id':101,'name':'Likith','email':'vedhlikhith@gmail.com'}"));
	}
	
	@Test
	void testGetUserErrorResponse() throws Exception {
		UserTemplate usertemplate = new UserTemplate();
		usertemplate.setId(101);
		usertemplate.setName("Likith");
		usertemplate.setEmail("vedhlikhith@gmail.com");
		
		when(service.getUser("Likith")).thenReturn(usertemplate);
		String uri = "/api/v1/registration/dataRequest/user/{name}";
		mockMvc.perform(get(uri,"Nikhil") 
		          .accept(MediaType.APPLICATION_JSON))
		          .andExpect(status().isBadRequest())
		          .andExpect(content().json("{'status' : 400,'message' : 'User not found'}"));
	}
	
	@Test
	void testregisterUserSuccessResponse() throws Exception {
		UserTemplate usertemplate = new UserTemplate();
		usertemplate.setId(101);
		usertemplate.setName("Likith");
		usertemplate.setEmail("vedhlikhith@gmail.com");
		
		//mocking void methods
		Mockito.doNothing().when(service).createUser(usertemplate);
		
		String uri = "/api/v1/registration/register/user";
		mockMvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(usertemplate)))
				.andExpect(status().isCreated())
				.andExpect(content().json("{'status' : 201,'message' : 'User created Successfully!'}"));
	}
	
	@Test
	void testregisterUserErrorResponse() throws Exception{
		UserTemplate usertemplate = new UserTemplate();
		usertemplate.setId(0);
		usertemplate.setName("");
		usertemplate.setEmail("");
		
		Mockito.doNothing().when(service).createUser(usertemplate);
		String uri = "/api/v1/registration/register/user";
		mockMvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(usertemplate)))
				.andExpect(status().isBadRequest())
				.andExpect(content().json("{'status' : 400,'message' : 'Required Feilds Cannot be Empty!'}"));
		
	}

}
