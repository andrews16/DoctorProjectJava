package com.revature.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.BadRequestException;
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
	public List<Prescription> getList(Integer patientId) throws BadRequestException {
		// @Session (?) this will require a session with validation
		// CHECK: user_id = patient.id OR user_id = patient.getDoctor.getId()
		if(patientId == 0) {
			return new ArrayList<Prescription>();
		} else if(patientId < 0 ) {
			throw new BadRequestException();
		}
		List<Prescription> list = rxRepo.getListById(patientId);
		return list;
	}

	public Prescription addRx(Prescription rx) throws BadRequestException {
		if (rx.getDose() == null ||
			rx.getFrequency() == null || 
			rx.getName() == null || 
			rx.getPatientId() == 0 ||
			rx.getDose().equals("") ||
			rx.getFrequency().equals("") ||
			rx.getName().equals("")
			) {
			throw new BadRequestException();
		}
			
		rx.setDateStarted(Date.valueOf(LocalDate.now()));
		return rxRepo.addRx(rx);
	}

	public void removeRx(Integer rxId) throws BadRequestException {
		if (rxId <= 0) {
			throw new BadRequestException();
		}
		rxRepo.removeRx(rxId);
	}

	public List<PrescriptionArchive> getArchive(int patientId) throws BadRequestException {
		if(patientId == 0) {
			return new ArrayList<PrescriptionArchive>();
		} else if(patientId < 0 ) {
			throw new BadRequestException();
		}
		List<PrescriptionArchive> list = rxRepo.getArchive(patientId);
		return list;
	}

}
