package com.revature.aspects;

import javax.servlet.http.HttpServletRequest;

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
import com.revature.services.AuthenticationService;

@Aspect
@Component
public class SecurityAspect {

	AuthenticationService authService;
	
	@Autowired
	public SecurityAspect(AuthenticationService authService) {
		super();
		this.authService = authService;
	}
	
	
	@Pointcut("execution(@com.revature.annotations.RequireDoctorOrPatient * *(..))")
	protected void requireDoctorOrPatientPointcut() {
	}
	@Pointcut("execution(@com.revature.annotations.RequireDoctor * *(..))")
	protected void requireDoctorPointcut() {
	}


	/**
	 * Require either the patient that is actually involved or a doctor.
	 * @param patientId
	 * @param request
	 * @throws AuthenticationException
	 */
	@Before(value = "requireDoctorOrPatientPointcut() && args(patientId,request)")
	public void checkDocOrPat(Integer patientId, HttpServletRequest request) throws AuthenticationException {
		User user = authService.getUser(request);
		// User must be logged in AND either the patient the file is about OR a doctor
		System.out.println(user != null);
		System.out.println(user.getId() == patientId || user.getRole() == UserRole.DOCTOR);
		if (user != null && (user.getId() == patientId || user.getRole() == UserRole.DOCTOR) ) {
			// Do nothing.
		} else {
			throw new AuthenticationException();			 
		}
		 
	}
	
	@Before(value = "requireDoctorPointcut() && args(..,request)")
	public void checkDoc(HttpServletRequest request) throws AuthenticationException {
		User user = authService.getUser(request);
		// User must be logged in AND a doctor to vie wthis information
		if (user == null || user.getRole() != UserRole.DOCTOR) {
			throw new AuthenticationException();
		} 
	}
	
	
	
	

	////////////////////////////////////////////////////////////////////
	/////// 	Security Concept: Database-stored Session ID	///////
	//////////////////////////////////////////////////////////////////
//	@Pointcut("execution(@com.revature.annotations.RequireDoctor * *(..))")
//	protected void requireDoctorPointcut() {
//	}
//	
//	@Before(value = "requireDoctorPointcut() && args(*,request)")
//	public void trackDocSender(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			Cookie cookie = WebUtils.getCookie(request, "doc-site-hash");
//			User user = userRepo.checkSession(cookie.getValue());
//			/* userRepo(sitory).checkSession(HASHED CODE)
//			 * 1. Checks the database for a user where the hashed code equals the one input
//			 * 		AND that the CURRENT_TIMESTAMP is not greater than the session_expiration date
//			 * 2. Returns the user if it finds that match
//			 */
//			if (user == null || user.getRole() != UserRole.DOCTOR) {
//				throw new AuthenticationException();
//			} 
//	}
//			
			
//			// Sessions stored in Database Concept
//			// Login Authentication logic here.
//			if (loginSuccess) {
//				String hashedSession = userRepo.generateSession(user);
//				 /* generateSession triggers a SQL function with the input of username:
//				 					SELECT * log_in(username)
//				   This Function: 
//				 1. hashes user_name with pgcrypto
//				 2. UPDATE users SET login_session_id = crypt(user_name, bf(4)) WHERE username = user_name;
//				 3. Set a date in column session_expiration to this date + 7 days. 
//				 4. RETURNS login_session_id 
//				 */
//				Cookie cookie = new Cookie("doc-hashed-session", hashedSession);
//				response.addCookie(cookie);
//			}
//	}
	
	
	
//		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println(username);
//		User user = userRepo.getUserByUsername(username);
//		if (user.getRole() != UserRole.DOCTOR || user.getId() != patientId) {
//			throw new AuthenticationException();
//		}
//		Cookie[] cookies = request.getCookies();
//		if(cookies != null) {	
//			for(Cookie cookie : cookies) {
//				System.out.println(cookie.getName() + "," + cookie.getValue());
//			}
//			System.out.println(request.getPathInfo());
//		}
	
	 @ExceptionHandler(AuthenticationException.class)
	 @ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Not authorized to view")
	 public void handleAuthenticationException(BadRequestException ex) {
	 }

}
