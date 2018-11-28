package com.revature.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.BadRequestException;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.services.AuthenticationService;

@RestController
@RequestMapping("")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class AuthenticationController {
//	
	AuthenticationService authService;
 
	@Autowired
    public AuthenticationController(AuthenticationService authService) {
		super();
		this.authService = authService;
	}

	
	@PostMapping("login")
	public User login(@RequestBody Credentials credentials, HttpServletResponse response) throws BadRequestException {
		User user = this.authService.login(credentials, response);
		// If the user fails to log in, an exception will be thrown so the following code will never be reached
		// Special string is used so that the hash has virtually 0 chance of having it occur.
		Cookie cookie = new Cookie("doc-app", user.getId() + "num.!PC4!.sess" + user.getSession());
		response.addCookie(cookie);
		return user;
	}
	
	@GetMapping("logout")
	public void logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("doc-app", "");
		response.addCookie(cookie);
		
	}
	
	@GetMapping("session")
	public User getSession(HttpServletRequest request) {
		return this.authService.getUser(request);
		
	}
	
	
	
	
	
	
//	@PostMapping("/")
//    public String authenticate(Credentials credentials) throws BadRequestException {
//		User user = loginService.authenticate(credentials);
//		return "JWT";
//		session.setAttribute("user_id",user.getId());
//		session.setAttribute("user_role_id",user.getRole());
//		response.setStatus(200);
//?		mapper.writeValue(response.getWriter(), user);
//    }
	
//	public void getSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//		ObjectMapper mapper = new ObjectMapper();
//		User user = new User();
//		if(session.getAttribute("user_id") != null) {
////			user = userService.getUser((int)session.getAttribute("user_id"));
//		}
//		try {
//			mapper.writeValue(response.getWriter(), user);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
