package com.revature.aspects;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * For service layer or methods which require a session, this will check for one or else throw an errror.
 * @author Admin
 *
 */
public class RequiredActiveSessionHandler {
//	
//	@Before("within(com.revature.controller..*)")
//	@Pointcut("execution (* RxController.*(..) && args(.., HttpSessionResponse)")
//	
//	pointcut permissionCheckMethods(Session sess) : 
//		(execution(public * *(..)) && args(.., sess));
//	
}
