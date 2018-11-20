package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.BadRequestException;
import com.revature.models.Patient;
import com.revature.services.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true") //allowedHeaders = "*"
public class UserController {

	UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("patient")
	public List<Patient> searchPatient(@RequestBody Patient patient) throws BadRequestException {
			return this.userService.getPatient(patient);			
	}
	
}
