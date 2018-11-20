package com.revature.repos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;

@Repository
public class RegisterRepository {
	
	SessionFactory sf;
		
	@Autowired
	public RegisterRepository(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	@Transactional
	public void insert(User rg) {
		sf.getCurrentSession().persist(rg);
	}
	

}
