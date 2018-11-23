package com.revature.repos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * ==> This file contains generic CRUD operations for individual objects.
 *		You don't have to use them. You're welcome to build your own in 
 *		your section's repository.
 * 
 * ==> Please don't edit this file without clearing it first with me
 * 
 * @author Clay
 *
 */

@Repository
public class GenericRepo {
    
	SessionFactory sf;
    
    
    @Autowired
    public GenericRepo(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	/**
     * CREATE 
     * Saves mapped object into the DB
     * @param Object ( should be mapped... ) 
     * @return transient stock (tracked by hibernate) with DB id#
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> T save(T obj) {
        	sf.getCurrentSession().persist(obj);
        	return obj;
    }
    
    /** 
     * READ
     * Gets any Object by ID, just for reading data.
     */
	@Transactional(propagation = Propagation.REQUIRED)
	public <T> T read(Class<T> myClass, int id) {
		return sf.getCurrentSession().get(myClass, id);
    }

	/**
	 * READ
	 * Get object of a class by a specified field
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public <T> List<T> criteriaGetObjectsByField(Class<T> class_, Object search, String variableName) {
		/*
		 * 1. Gets the Criteria Builder singleton instance - a utility class for creating critera
		 * 2. Creating an instance of the CirteriaQuery object for type input
		 * 3. Setting the root of the query - because we may be getting out info by joining data
		 * 	it's necessary to specify which table the query actually begins on
		 * 4. the query logic itself
		 * 5. Filter applies equal operand (==) and checks object variable name, not the column!
		 * 		You can add multiple filters separated by commas.
		*/
		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder(); 		//1
		CriteriaQuery<T> initQuery = cb.createQuery(class_);	//2
		Root<T> root = initQuery.from(class_);				//3
		initQuery												//4
			//.select(root)	
			.where(cb.equal(root.get(variableName), search)); // 5 
		Query<T> query = sf.getCurrentSession().createQuery(initQuery);
		List<T> results = query.getResultList();
		return results;
	}

	/**
	 * Gets object for any hibernate mapped string attribute, and will ignore the case of the result.
	 * @param class - Object.class for the result and search
	 * @param search - Search term, a string
	 * @param variableName - name of the variable within the object that is specified
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public <T> List<T> criteriaGetObjectsByFieldIgnoreCase(Class<T> class_, String search, String variableName) {
		/*
		 * 1. Gets the Criteria Builder singleton instance - a utility class for creating critera
		 * 2. Creating an instance of the CirteriaQuery object for type input
		 * 3. Setting the root of the query - because we may be getting out info by joining data
		 * 	it's necessary to specify which table the query actually begins on
		 * 4. the query logic itself
		 * 5. Filter applies equal operand (==) and checks object variable name, not the column!
		 * 		You can add multiple filters separated by commas.
		*/
		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder(); 		//1
		CriteriaQuery<T> initQuery = cb.createQuery(class_);	//2
		Root<T> root = initQuery.from(class_);				//3
		initQuery												//4
			//.select(root)	
			.where(cb.equal(cb.lower(root.get(variableName)), search.toLowerCase())); // 5 
		Query<T> query = sf.getCurrentSession().createQuery(initQuery);
		List<T> results = query.getResultList();
		return results;
	}

	//UPDATE method will go here

	//generic DELETE method will go here

}