package com.revature.tests.services;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.revature.models.User;
import com.revature.repos.GenericRepo;
import com.revature.repos.UserRepo;
import com.revature.services.UserService;

public class UserServiceTest {
	

	UserRepo userRepo = mock(UserRepo.class);
	GenericRepo genRepo = mock(GenericRepo.class);
	UserService userService =  new UserService(userRepo, genRepo);
	
	@Test
	public void getUserGood() throws Exception {
		String username = "goodUsername";
		when(userRepo.getUserByUsername(username))
			.thenReturn(new User());
		assertNotNull(userService.getUser(username));
	}

}
