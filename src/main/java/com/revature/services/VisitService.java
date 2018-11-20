package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Patient;
import com.revature.models.Prescription;
import com.revature.models.VisitInfo;
import com.revature.repos.VisitRepo;


@RestController
@RequestMapping("visits")
public class VisitService {

	VisitRepo visitRepo;

	@Autowired
	public VisitService(VisitRepo visitRepo) {
		super();
		this.visitRepo = visitRepo;
	}
	
	@Transactional
	public VisitInfo create(VisitInfo vi) {
		visitRepo.insert(vi);
		return vi;
	}
	
	public List<VisitInfo> getAllList(Patient patient) {
		List<VisitInfo> list = visitRepo.getAllList();
		return list;
	}
	
	public List<VisitInfo> getList(Patient patient) {
		List<VisitInfo> list = visitRepo.getList(patient.getId());
		return list;
	}
	
	public VisitInfo addVisitInfo(VisitInfo vi) {
		return visitRepo.addVisitInfo(vi);
	}
	
	
	
}