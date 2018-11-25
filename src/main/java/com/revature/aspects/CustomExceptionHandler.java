package com.revature.aspects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.BadRequestException;

@Aspect
@Component
public class CustomExceptionHandler {
//	@Pointcut(value="@annotation(security.annotation.RequireValidUser) && args(name,..)",  argNames="datasetName")
//	private void methodAnnotatedForValidDatasetName(String datasetName) {
//	}

//	@Before("within(com.revature.controllers..*)") // <- point cut is expression to target METHODS
//	public void logging(JoinPoint jp) {
//		System.out.println("OK! customexceptionhandler test");
//		
//	}
////	
////	@Pointcut("execution (public StringBuilder MyBean.*(..))")
////	@Around("within(com.revature.beans..StringBuilder)")
////	public void aroundEx(ProceedingJoinPoint pjp) {
////		
////	}
////	@AfterThrowing(pointcut = "execution(* com.revature.controllers...* (..))", throwing = "ex")
	@AfterThrowing(pointcut = "execution(* com.revature.controllers..*(..))", throwing = "ex")
	public void logExceptions(AuthenticationException ex) throws Exception {
		System.out.println("Exception thrown and advice reached." );
	}
	
	@AfterThrowing(pointcut = "execution(* com.revature.controllers..*(..)) && args(*,request)", throwing = "ex")
	public void logExceptions(BadRequestException ex, HttpServletRequest request) throws Exception {
		System.out.println("Bad Request!");
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + "," + cookie.getValue());
		}
		System.out.println(request.getPathInfo());
	}
//	
//	@AfterThrowing(pointcut = "execution(* com.revature.controller.* (..))", throwing = "ex")
//	public void errorInterceptor(AuthenticationException ex, HttpServletRequest request, HttpServletResponse response) {
//		log.warn("Authentication error!");
//		System.out.println("ARIVED HERE ASPECT LOGGING " + request.toString());
//	}
//	
	 @ExceptionHandler(BadRequestException.class)
	 public void handleBadRequestException(BadRequestException ex) {
		 System.out.println("Exception handled by aspect!! ");
	 }
}
