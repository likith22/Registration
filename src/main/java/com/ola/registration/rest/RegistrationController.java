package com.ola.registration.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ola.registration.entity.Rider;
import com.ola.registration.service.RiderService;
import com.ola.registration.service.UserService;
import com.ola.registration.template.RiderTemplate;
import com.ola.registration.template.UserTemplate;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RiderService riderService;
	
	@ApiOperation(value = "Requesting for user details")
	@GetMapping("/dataRequest/user/{name}")
	public ResponseEntity<?> getUser(@PathVariable("name") String  name){
		Map<String,Object> map = new HashMap<>();
		
		if(null == name) {
			map.put("status",400);
			map.put("message", "name cannot be empty");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		else {
			UserTemplate userTemplate = this.userService.getUser(name);
			if(userTemplate == null) {
				map.put("status",400);
				map.put("message", "User not found");
				return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<>(userTemplate,HttpStatus.ACCEPTED);
			}
		}
	}
	
	@ApiOperation(value = "Requesting for rider details")
	@GetMapping("/dataRequest/rider/{name}")
	public ResponseEntity<?> getRider(@PathVariable("name") String  name){
		Map<String,Object> map = new HashMap<>();
		
		if(null == name) {
			map.put("status",400);
			map.put("message", "name cannot be empty");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		else {
			RiderTemplate riderTemplate = this.riderService.getRider(name);
			if(riderTemplate == null) {
				map.put("status",400);
				map.put("message", "User not found");
				return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<>(riderTemplate,HttpStatus.ACCEPTED);
			}
		}
	}
	
	@ApiOperation(value = "creating a new user")
	@PostMapping("/register/user")
	public ResponseEntity<?> registerUser(@RequestBody UserTemplate newUser){
		Map<String,Object> map = new HashMap<>();
		if(newUser.getId() == 0|| newUser.getEmail().isEmpty() || newUser.getName().isEmpty()) {
			
			map.put("status",400);
			map.put("message", "Required Feilds Cannot be Empty!");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
		else {
			this.userService.createUser(newUser);
			map.put("status",201);
			map.put("message", "User created Successfully!");
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		}
	}
	
	@ApiOperation(value = "creating a new rider")
	@PostMapping("/register/rider")
	public ResponseEntity<?> registerRider(@RequestBody RiderTemplate newRider){
		Map<String,Object> map = new HashMap<>();
		    if(newRider.getId() == 0|| newRider.getEmail().isEmpty() || newRider.getName().isEmpty() || newRider.getAvailablity().isEmpty()) {	
				map.put("status",400);
				map.put("message", "Required Feilds Cannot be Empty!");
				return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
			}
			else {
				this.riderService.registerRider(newRider);
				map.put("status",201);
				map.put("message", "User created Successfully!");
				return new ResponseEntity<>(map,HttpStatus.CREATED);
			}
		}
	@ApiOperation(value = "requesting for available riders")
	@GetMapping("/dataRequest/availableRiders")
	public ResponseEntity<List<RiderTemplate>> getAvailableRidersList(){
		List<RiderTemplate> riders = this.riderService.getAvailableRiders();
		return new ResponseEntity<>(riders,HttpStatus.OK);
	}
	@PostMapping("/register/changeStatus/riderId/{riderId}/status/{status}")
	public ResponseEntity<?> changeRiderStatus(@PathVariable("riderId") int riderId,@PathVariable("status") String status){
		 this.riderService.updateStatus(riderId,status);
		 Map<String,Object> map = new HashMap<>();
		 map.put("status",201);
		 map.put("message", "Updated the status as"+" " + status+"for rid");
		 return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
	}
}
