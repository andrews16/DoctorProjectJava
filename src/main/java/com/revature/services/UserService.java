package com.revature.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.BadRequestException;
import com.revature.models.Patient;
import com.revature.models.User;
import com.revature.repos.GenericRepo;
import com.revature.repos.UserRepo;

@Service
public class UserService {
	
	UserRepo userRepo;
	GenericRepo genericRepo;

	@Autowired
	public UserService(UserRepo userRepo, GenericRepo genericRepo) {
		super();
		this.userRepo = userRepo;
		this.genericRepo = genericRepo;
	}

	public User getUser(String username) {
		return userRepo.getUserByUsername(username);
	}

	public List<Patient> getPatient(Integer id, String lastName, String birthday) throws BadRequestException {
		Patient patient = new Patient();
		if((id == null || id == 0) && birthday == null && lastName == null) {
			return new ArrayList<Patient>(Arrays.asList(patient));
		}
		
		patient.setId(id == null? 0 : id);
		patient.setLastName(lastName);
		if(birthday != null ) {
			patient.setBirthday(Date.valueOf(LocalDate.parse(birthday)));
		}
		
		if(patient.getId() != 0) {
			patient = userRepo.getPatient(patient);
			List<Patient> result = new ArrayList<Patient>();
			result.add(patient);
			return result;
		} else if (patient.getLastName() != null && patient.getBirthday() != null){
			return userRepo.getPatientByLastNameAndBirthday(patient);
		} else if (patient.getLastName() != null ) {
			 return genericRepo.criteriaGetObjectsByFieldIgnoreCase(Patient.class, lastName, "lastName");
		} else if (patient.getBirthday() != null ) {
			 return genericRepo.criteriaGetObjectsByField(Patient.class, patient.getBirthday(), "birthday");
		}
		throw new BadRequestException();
	}

}
