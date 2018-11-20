package com.revature.repos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.VisitInfo;

@Repository
public class VisitRepo {
	
	SessionFactory sf;
	
	@Transactional
	public void insert(VisitInfo vi) {
		sf.getCurrentSession().persist(vi);
	}
	
	@Autowired
	public VisitRepo(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	public List<VisitInfo> getList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<VisitInfo> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	public VisitInfo addVisitInfo(VisitInfo vi) {
		// TODO Auto-generated method stub
		return null;
	}

}