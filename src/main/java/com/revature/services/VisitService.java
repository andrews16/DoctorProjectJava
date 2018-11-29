package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Patient;
import com.revature.models.VisitInfo;
import com.revature.repos.VisitRepo;


@Service
public class VisitService {

	VisitRepo visitRepo;

	@Autowired
	public VisitService(VisitRepo visitRepo) {
		super();
		this.visitRepo = visitRepo;
	}
	
	public VisitInfo create(VisitInfo vi) {
		visitRepo.insert(vi);
		return vi;
	}
	
	// gets single visit
	public VisitInfo findById(int id) {
//	VisitInfo vi = visitRepo.getVisit(id);
		System.out.println("this in VisitService service");
		return visitRepo.findByIdRepo(id);
	}
	
//	public User getUser(String username) {
//		return userRepo.getUserByUsername(username);
//	}
	
//	public VisitInfo getVisit(int id) {
//		VisitInfo visit = visitRepo.getVisit(id);
//		return visit;
//	}
	
	public List<VisitInfo> getList(Patient patient) {
		List<VisitInfo> list = visitRepo.getList(patient.getId());
		return list;
	}
	
	// adds a single note
	public VisitInfo addVisitInfo(VisitInfo vi) {
		return visitRepo.addVisitInfo(vi);
	}
	
	
	
}