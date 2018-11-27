package com.revature.repos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Doctor;
import com.revature.models.Patient;
import com.revature.models.User;
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
//	@Transactional
//	public VisitInfo findByIdRepo(int id) {
//		return sf.getCurrentSession().get(VisitInfo.class, id);
//	}
	
	@Transactional(propagation = Propagation.REQUIRED)
    public VisitInfo findByIdRepo(int id) {
    		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder(); 		
    		CriteriaQuery<VisitInfo> initQuery = cb.createQuery(VisitInfo.class);	
    		Root<VisitInfo> root = initQuery.from(VisitInfo.class);				
    		initQuery
    			.select(root)	
    			.where(cb.equal(root.get("id"), id)); 
    		Query<VisitInfo> query = sf.getCurrentSession().createQuery(initQuery);
    		List<VisitInfo> results = query.getResultList();
    		
    		return results.get(0);
    	
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