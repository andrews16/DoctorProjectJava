package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.BadRequestException;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.services.LoginService;

@RestController
@RequestMapping("login")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true") //allowedHeaders = "*"
public class LoginController {
//	
	LoginService loginService;
 
	@Autowired
    public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}

	@PostMapping("/")
    public String authenticate(Credentials credentials) throws BadRequestException {
		User user = loginService.authenticate(credentials);
		return "JWT";
//		session.setAttribute("user_id",user.getId());
//		session.setAttribute("user_role_id",user.getRole());
//		response.setStatus(200);
//?		mapper.writeValue(response.getWriter(), user);
    }
	
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
