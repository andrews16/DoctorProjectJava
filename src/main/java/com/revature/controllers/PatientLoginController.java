package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.javainuse.model.Employee;
//import com.javainuse.service.EmployeeService;

@Controller
public class PatientLoginController {

//	@Autowired
//	EmployeeService employeeService;

//	@RequestMapping("/welcome")
//	public ModelAndView firstPage() {
//		return new ModelAndView("welcome");
//	}
//
//	@RequestMapping(value = "/addNewPatient", method = RequestMethod.GET)
//	public ModelAndView show() {
//		return new ModelAndView("addPatient", "pat", new Patient());
//	}
//
//	@RequestMapping(value = "/addNewPatient", method = RequestMethod.POST)
//	public ModelAndView processRequest(@ModelAttribute("pat") Patient pat) {
//		
//		patientService.insertPatient(pat);
//		List<Patient> patient = patientService.getAllPatients();
//		ModelAndView model = new ModelAndView("getPatients");
//		model.addObject("patients", patients);
//		return model;
//	}
//
//	@RequestMapping("/getPatients")
//	public ModelAndView gePatients() {
//		List<Patient> patients = patientService.getAllPatients();
//		ModelAndView model = new ModelAndView("getPatients");
//		model.addObject("patients", patients);
//		return model;
//	}

}
