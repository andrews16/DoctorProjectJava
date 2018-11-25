package com.revature.repos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;

@Repository
public class LoginRepo {

	SessionFactory sf;
	
	@Autowired
	public LoginRepo(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User authenticate(String username, String password) {
//		List<User> bears = sf.getCurrentSession()
//				.createNativeQuery("SELECT * ", Bear.class)
//				.setParameter("id", parentId)
//				.getResultList();
//		return bears;
		return null;
		
	}
}
