package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.RequireDoctor;
import com.revature.annotations.RequireDoctorOrPatient;
import com.revature.exceptions.BadRequestException;
import com.revature.models.Prescription;
import com.revature.models.PrescriptionArchive;
import com.revature.services.RxService;

@RestController
@RequestMapping("rx")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true") //allowedHeaders = "*"
public class RxController {
	
	RxService rxService;
	
	@Autowired
	public RxController(RxService rxservice) {
		super();
		this.rxService = rxservice;
	}
	
	/**
	 * Returns a list of prescriptions by patient Id.
	 * POST in rx/{id} requires in input of a PATIENT
	 * @throws BadRequestException 
	 */
	@RequireDoctorOrPatient
	@GetMapping("{patientId}")
	public List<Prescription> getListFor(@PathVariable Integer patientId, HttpServletRequest request) throws Exception {
		return this.rxService.getList(patientId);
	}
	
	/**
	 * Returns prescription with new DB id.
	 * @param rx
	 * @return
	 * 	Testing : {
	*		"id": 0,
	*	  "dose": "50mg",
	*	  "frequency": "MORNING 45 MIN BEFORE BREAKFAST",
	*	  "name": "Benadryl",
	*	  "patientId": 25,
	*	  "dateStarted": "2018-09-22"
	*	}
	 * @throws BadRequestException 
	 */
	@RequireDoctor
	@PostMapping("add")
	public Prescription addRx(@RequestBody Prescription rx, HttpServletRequest request ) throws BadRequestException {
		return  this.rxService.addRx(rx);
	}
	
	/**
	 * Get archive of old prescriptions by patient
	 * @param patient
	 * @return
	 * @throws BadRequestException 
	 */
	@RequireDoctorOrPatient
	@GetMapping("archive/{patientId}")
	public List<PrescriptionArchive> getArchiveFor(@PathVariable int patientId, HttpServletRequest request) throws BadRequestException {
		return this.rxService.getArchive(patientId);
	}
	
	@RequireDoctor
	@GetMapping("remove/{rxId}")
	public void removeRx(@PathVariable Integer rxId, HttpServletRequest request ) throws BadRequestException {
		this.rxService.removeRx(rxId);
	}

}

