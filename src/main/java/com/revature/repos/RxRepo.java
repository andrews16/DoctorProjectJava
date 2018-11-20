package com.revature.repos;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Patient;
import com.revature.models.Prescription;
import com.revature.models.PrescriptionArchive;


@Repository
public class RxRepo {

	SessionFactory sf;
	
	@Autowired
	public RxRepo(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	
	/**
	 * CREATE - Add Prescription ot a patient
	 * @param rx
	 */
	@Transactional //(propagation = Propagation.REQUIRED)
	public Prescription addRx(Prescription rx) {
		Session session = sf.getCurrentSession();
		Patient patient = session.get(Patient.class, rx.getPatientId());
		rx.setPatient(patient);
		patient.getPrescriptions().add(rx);
		session.persist(rx);
		return rx;
	}


	/**
	 * READ - Gets a list of a patient's prescriptions
	 * @param patientId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Prescription> getListById(int patientId) {
		Session session = sf.getCurrentSession();
		List<Prescription> list = session.createNativeQuery("SELECT * FROM prescriptions WHERE patient_id = :patId", Prescription.class)
				.setParameter("patId", patientId)
				.getResultList();
		return list;
	}
	
	/**
	 * READ - Get a list of the patient's archived (previous) prescriptions
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<PrescriptionArchive> getArchive(int patientId) {
		Session session = sf.getCurrentSession();
		List<PrescriptionArchive> list = session.createNativeQuery("SELECT * FROM prescriptions_archive WHERE patient_id = :patId", PrescriptionArchive.class)
				.setParameter("patId", patientId)
				.getResultList();
		return list;
	}
	/**
	 * DELETE - Remove a prescription
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removeRx(Prescription rx) {
		Session session = sf.getCurrentSession();
		Prescription persistedRx = session.get(Prescription.class, rx.getId());
		PrescriptionArchive archive = new PrescriptionArchive(rx, Date.valueOf(LocalDate.now()));
		
		session.delete(persistedRx);
		session.save(archive);
		
	}

	
}
