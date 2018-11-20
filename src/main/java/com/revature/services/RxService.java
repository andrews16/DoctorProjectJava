package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.BadRequestException;
import com.revature.models.Patient;
import com.revature.models.Prescription;
import com.revature.models.PrescriptionArchive;
import com.revature.repos.RxRepo;

@Service
public class RxService {

	RxRepo rxRepo;
	
	@Autowired
	public RxService(RxRepo rxrepo) {
		super();
		this.rxRepo = rxrepo;
	}

	/**
	 * Method gets the list of prescriptions from the Repo after authentication.
	 * The session will be checked for either the matching patient IDs
	 * OR for matching Doctor IDs.
	 * @param patientId
	 * @return
	 * @throws BadRequestException 
	 */
	public List<Prescription> getList(int patientId) throws BadRequestException {
		// @Session (?) this will require a session with validation
		// CHECK: user_id = patient.id OR user_id = patient.getDoctor.getId()
		List<Prescription> list = rxRepo.getListById(patientId);
		if(list == null) {
			throw new BadRequestException();
		}
		return list;
	}

	public Prescription addRx(Prescription rx) {
		return rxRepo.addRx(rx);
	}

	public void removeRx(Prescription rx) {
		rxRepo.removeRx(rx);
		
	}

	public List<PrescriptionArchive> getArchive(int patientId) {
		return rxRepo.getArchive(patientId);
	}

}
