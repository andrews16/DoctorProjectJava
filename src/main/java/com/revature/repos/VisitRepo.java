package com.revature.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Doctor;
import com.revature.models.Patient;
import com.revature.models.VisitInfo;

@Repository
public class VisitRepo {
	
	SessionFactory sf;
	
	@Autowired
	public VisitRepo(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	
	@Transactional
	public void insert(VisitInfo vi) {
		sf.getCurrentSession().persist(vi);
	}
	

	public List<VisitInfo> getList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//single visit
	@Transactional(propagation = Propagation.REQUIRED)
	public VisitInfo findById(VisitInfo vi) {
		return sf.getCurrentSession().get(VisitInfo.class,  vi.getVisitId());
	}
	
//	public VisitInfo getVisit(int id) {
//			Session session = sf.getCurrentSession();
//			VisitInfo vi = session.createNativeQuery("SELECT * FROM visit_info WHERE visit_id = :visid", VisitInfo.class)
//					.setParameter("visid", id)
//					.getSingleResult();
//		return vi;
//	}

	@Transactional
	public VisitInfo addVisitInfo(VisitInfo vi) {
		Session session = sf.getCurrentSession();
		Patient patient = session.get(Patient.class, vi.getPatientId());
		vi.setPatient(patient);
		Doctor doctor = session.get(Doctor.class, vi.getDoctorId());
		vi.setDoctor(doctor);
		patient.getVisits().add(vi);
//		doctor.getVisits().add(vi);
		session.persist(vi);
		return vi;
	}

}