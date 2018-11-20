package com.revature.repos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Doctor;
import com.revature.models.Insurance;
import com.revature.models.Patient;
import com.revature.util.HibernateUtil;

/**
 * This UserRepo shows an example of how we will set up all the repositories.
 * 1. @Repository annotation
 * 2. SessionFactory sf;
 * 3. Constructor gets autowired theSessionFactory
 * 
 * Methods inside will have to do only with the objects you need for your part.
 * @author Clay
 */

@Repository
public class UserRepo {
	
	SessionFactory sf;
	HibernateUtil hu;
	
	@Autowired
    public UserRepo(SessionFactory sf, HibernateUtil hu) {
		super();
		this.sf = sf;
		this.hu = hu;
	}
	/**
     * One doctor to Many Patients relationship
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Doctor addPatientsToDoctor(Doctor doc, Patient...pats) {
		Hibernate.initialize(doc.getPatients());
		for(Patient pat : pats) {
			doc.getPatients().add(pat);
		}
		sf.getCurrentSession().update(doc);
		return doc;
    }

    /**
     * One to Many; Add insurance to patient
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Patient newInsurance(Patient pat, Insurance insr) {
        pat = sf.getCurrentSession().get(Patient.class, pat.getId());
        
        insr.setPatient(pat);
        pat.getInsurances().add(insr);
        
        sf.getCurrentSession().save(insr);
        
        return pat;
    
    }
    
    /**
     * Get user with Last name and Birthday.
     * @param patient
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
	public List<Patient> getPatientLike(Patient patient) {
    	Session session = sf.getCurrentSession();
    	

    	List<Patient> list = session.createQuery("select p from Patient p "
    			+ " where p.lastName like :lastName")
			.setParameter("lastName", patient.getLastName(), StringType.INSTANCE)
			.list();

    	List<Patient> results = new ArrayList<>();
		for(Patient pat : list) {
			if (pat.getBirthday() == patient.getBirthday()) {
				results.add(pat);
			}
		}
		List<Patient> result = (List<Patient>)list;
		return result;
	    	
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Patient getPatient(Patient pat) {
        return sf.getCurrentSession().get(Patient.class, pat.getId());
    }
    
    
    
}
