package com.revature.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Patient;
import com.revature.models.VisitInfo;
import com.revature.services.VisitService;

@RestController
@RequestMapping("visit")
public class VisitController {
	
	VisitService visitService;
	

	@Autowired
	public VisitController(VisitService visitService) {
		super();
		this.visitService = visitService;
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public VisitInfo createVisitInfo(@RequestBody VisitInfo vi) {
		return this.visitService.create(vi);
	}
	
	@PostMapping("list")
	public List<VisitInfo> getListFor(@RequestBody Patient patient) {
		return this.visitService.getList(patient);
	}
	
	@PostMapping("add")
	public VisitInfo addVisitInfo(@RequestBody VisitInfo vi) {
		System.out.println(" Visit Info " + vi);
		return this.visitService.addVisitInfo(vi);
	}
	
	@PostMapping("my-test")
	public String testpost() {
		return "peanuts";
	}


	

}