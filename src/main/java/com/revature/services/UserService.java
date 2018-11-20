package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Patient;
import com.revature.repos.UserRepo;

@Service
public class UserService {
	
	UserRepo userRepo;

	@Autowired
	public UserService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	public List<Patient> getPatient(Patient patient) {
		if(patient.getId() == 0) {
			return userRepo.getPatientLike(patient);
		} else {
			patient = userRepo.getPatient(patient);
			List<Patient> result = new ArrayList<Patient>();
			result.add(patient);
			return result;
		}
		
	}
	

}
