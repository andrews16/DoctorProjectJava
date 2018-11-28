package com.revature.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import com.revature.exceptions.BadRequestException;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.repos.AuthenticationRepo;

@Service
public class AuthenticationService {
	
	AuthenticationRepo authRepo;
	
	@Autowired
	public AuthenticationService(AuthenticationRepo loginrepo) {
		super();
		this.authRepo = loginrepo;
	}

	public User login(Credentials credentials, HttpServletResponse response) throws BadRequestException {
		if (credentials.getUsername() == null || credentials.getPassword() == null) {
			throw new BadRequestException();
		}
		User user = authRepo.login(credentials);
		if(user == null) {
			throw new BadRequestException();
		}		
		return user;
	}
	
	/**
	 * Checks the cookie for the special string, which is parsed as the user's ID and the user's DB-stored session.
	 * @param cookie
	 * @return
	 */
	public User getUser(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, "doc-app");
		// If the user either is not logged in OR is logged out (has cookie overridden with "")
		// then return null
		if (cookie == null || cookie.getValue() == "") {
			return null;
		}
		// Special string is used so that the hash has virtually 0 chance of having it occur.
		String[] strings = cookie.getValue().split("num.!PC4!.sess");
		// The first string is the username, the second is the DB session.
		return authRepo.authenticate(strings[0], strings[1]);		
	}

}
