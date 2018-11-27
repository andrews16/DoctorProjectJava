package com.revature.repos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Doctor;
import com.revature.models.Insurance;
import com.revature.models.Patient;
import com.revature.models.User;

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
	GenericRepo hu;
	
	@Autowired
    public UserRepo(SessionFactory sf, GenericRepo hu) {
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
	public List<Patient> getPatientByLastNameAndBirthday(Patient patient) {
    	Session session = sf.getCurrentSession();
    	
    	CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder(); 		
		CriteriaQuery<Patient> initQuery = cb.createQuery(Patient.class);	
		Root<Patient> root = initQuery.from(Patient.class);				
		initQuery
			.select(root)
			.where(cb.equal(cb.upper(root.get("lastName")), patient.getLastName().toUpperCase()),
					cb.equal(root.get("birthday"), patient.getBirthday())); 
		Query<Patient> query = sf.getCurrentSession().createQuery(initQuery);
		List<Patient> results = query.getResultList();		
    	
		return results;
	    	
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Patient getPatient(Patient pat) {
        return sf.getCurrentSession().get(Patient.class, pat.getId());
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public User getUserByUsername(String username) {
    		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder(); 		
    		CriteriaQuery<User> initQuery = cb.createQuery(User.class);	
    		Root<User> root = initQuery.from(User.class);				
    		initQuery
    			.select(root)	
    			.where(cb.equal(root.get("username"), username)); 
    		Query<User> query = sf.getCurrentSession().createQuery(initQuery);
    		List<User> results = query.getResultList();
    		if(results.size() < 1 ) {
    			return null;
    		}
    		return results.get(0);
    	
    	}
    


}
