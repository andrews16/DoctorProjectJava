package com.revature.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.enums.UserRole;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.BadRequestException;
import com.revature.models.User;
import com.revature.repos.UserRepo;

@Aspect
@Component
public class SecurityAspect {

	UserRepo userRepo;
	
	@Autowired
	public SecurityAspect(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Pointcut("execution(@com.revature.annotations.RequireDoctorOrPatient * *(..))")
	protected void myPointcut() {
	}

	@Before(value = "myPointcut() && args(patientId,..)")
	public void afterSuccessfulReturn(JoinPoint joinPoint, Integer patientId) throws AuthenticationException {
		
		
//		
//		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println(username);
//		User user = userRepo.getUserByUsername(username);
//		if (user.getRole() != UserRole.DOCTOR || user.getId() != patientId) {
//			throw new AuthenticationException();
//		}
//		
//		
//	    System.out.println(patientId.toString());
	}
	
	 @ExceptionHandler(AuthenticationException.class)
	 @ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Not authorized to view")
	 public void handleAuthenticationException(BadRequestException ex) {
	 }

}
