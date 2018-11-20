package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.AuthenticationException;
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
	 * POST in rx/list requires in input of a PATIENT
	 * @throws BadRequestException 
	 */
	@GetMapping("{patientId}")
	public List<Prescription> getListFor(@RequestParam int patientId) throws BadRequestException {
		//Testing post body:
		// {"id":3,"doctor":{"id":1}}
		return this.rxService.getList(patientId);
	}
	
	/**
	 * Returns prescription with new DB id.
	 * @param rx
	 * @return
	 */
	@PostMapping("add")
	public Prescription addRx(@RequestBody Prescription rx ) {
		//Testing post body:
		/*
		 {"dateStarted":"2018-10-15",
		"name":"buprinorphine/naloxone",
		"dose":"8mg/2mg",
		"frequency":"TWICE PER DAY UNDER TOUNGE",
		"patientId":3}
		
		OR 
		
		{
	"id": 0,
  "dose": "50mg",
  "frequency": "MORNING 45 MIN BEFORE BREAKFAST",
  "name": "Benadryl",
  "patientId": 25,
  "dateStarted": "2018-09-22"
}
		
		*/
		System.out.println("rx controller " + rx);
		return  this.rxService.addRx(rx);
	}
	
	/**
	 * Get archive of old prescriptions by patient
	 * @param patient
	 * @return
	 */
	@PostMapping("archive/{patientId}")
	public List<PrescriptionArchive> getArchiveFor(@RequestParam int patientId) {
		//Testing post body:
		// {"id":3,"doctor":{"id":1}}
		return this.rxService.getArchive(patientId);
	}
	@PostMapping("remove")
	public void removeRx(@RequestBody Prescription rx) {
		System.out.println("rx contoller remove : " + rx);
		this.rxService.removeRx(rx);
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No prescriptions found")
	public void handleBadRequestException(BadRequestException ex) {
	}

	 @ExceptionHandler(AuthenticationException.class)
	 @ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Not authorized to view")
	 public void handleAuthenticationException(BadRequestException ex) {
	 }

}

