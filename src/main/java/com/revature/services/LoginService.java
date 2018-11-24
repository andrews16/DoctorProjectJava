package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.repos.LoginRepo;

@Service
public class LoginService {
	
	LoginRepo loginrepo;
	
	@Autowired
	public LoginService(LoginRepo loginrepo) {
		super();
		this.loginrepo = loginrepo;
	}

	public User authenticate(Credentials credentials) {
		// If the user does not input a username OR does not input a password
//		if(credentials.getUsername() == null || credentials.getPassword() == null) {
//			throw new BadRequestException();
//		}
//		User user = loginRepo.authenticate(credentials.getUsername().toLowerCase(), credentials.getPassword());
//		if(user == null) {
//			throw new NotFoundException();
//		}
//		
//		return user;
		return null;
	}

}
