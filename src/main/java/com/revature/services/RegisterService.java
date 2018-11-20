package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repos.RegisterRepository;

@Service
public class RegisterService {
	
	RegisterRepository registerRepository;
	
	@Autowired
	public RegisterService(RegisterRepository registerRepository) {
		super();
		this.registerRepository = registerRepository;
	}
	
	public User create(User rg) {
		registerRepository.insert(rg);
		return rg;
	}
}
