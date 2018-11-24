package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	// single visit
	public VisitInfo getVisit(int id) {
		VisitInfo visit = visitRepo.getVisit(id);
		return visit;
	}
	
	public List<VisitInfo> getList(Patient patient) {
		List<VisitInfo> list = visitRepo.getList(patient.getId());
		return list;
	}
	
	// adds a single note
	public VisitInfo addVisitInfo(VisitInfo vi) {
		return visitRepo.addVisitInfo(vi);
	}
	
	
	
}