package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.BadRequestException;
import com.revature.services.LoginService;

@RestController
@RequestMapping("login")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true") //allowedHeaders = "*"
public class LoginController {
	
//	LoginService loginService;
// 
//	@Autowired
//    public LoginController(LoginService loginService) {
//		super();
//		this.loginService = loginService;
//	}

	@PostMapping("/")
    public String home(HttpServletRequest request) throws BadRequestException {
 
//		Account account = AccountResolver.INSTANCE.getAccount(request);

        //User account = loginService.login(request);
//        if (account == null) {
//            throw new BadRequestException();
//        }
 
        //model.addAttribute("name", name);
 
        return "hello";
    }
}
