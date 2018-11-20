package com.revature.testing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.revature.util.HibernateUtil;

/**\
 * This is a class for testing. 
 * @author Admin
 *
 */
public class Launcher {
//	static HibernateUtil hu = new HibernateUtil();
//	private static InputStream in;
	
	public static void main(String[] args) {
		/*
		 * This try statement will add a doctor and a user.
		 * You will have to change the data to avoid violating the constraints.
		 * 
		 * This Launcher is strictly for testing
		 * Each feature will have its own speciifc method.
		 * The method to add doctors to users will be in one of the ORM files in the persistence package.
		 * 
		 * You will only work on the ORM relating to your section of the project.
		 * Each part will be modular -- Meaning if one section fails, the others will not be affected.
		 * 
		 * The only shared class will be the front controller and the Hibernate Util.
		 */
//		try(Session session = hu.getSession().openSession()){
//			Doctor doc = new Doctor(0,"dr_martinez","password","Jorge","Martinez");
//			
//			Patient pat = new Patient();
//			pat.setId(0);
//			pat.setUsername("vegan_diet");
//			pat.setPassword("password2");
//			pat.setFirstName("Jill");
//			pat.setLastName("Jillson");
//			pat.setAddress("12 Jill Street Tampa, FL");
//			LocalDate birthday = LocalDate.of(1985, 9, 10);
//			Date bday = Date.valueOf(birthday);
//			pat.setBirthday(bday);
//			pat.setGender(Gender.FEMALE);
//			
//			pat.setDoctor(doc);
//			doc.getPatients().add(pat);
//					
//			session.beginTransaction();
//			session.save(doc);
//			session.save(pat);
//			session.getTransaction().commit();
//			// :breed is a named procedure, like using ? in the Perpared statements
//			//select alias-v           v---specify what the alisa means here
//			List<?> list = session.createQuery("select usr from User usr where usr.username like :username")
//									.setParameter("username", "gooddoctor", StringType.INSTANCE)
//									.list();
//			System.out.println(list);
//
//		}
		
		/*
		 * This create a user and adds a rx to it 
		 */
//		try(Session session = hu.getSession().openSession()){
//			Patient pat = new Patient();
//			pat.setId(0);
//			pat.setUsername("my_username");
//			pat.setPassword("gr8passwrd");
//			pat.setFirstName("Tom");
//			pat.setLastName("Thompson");
//			pat.setAddress("12 Tomsy Street Tampa, FL");
//			LocalDate birthday = LocalDate.of(1995, 9, 10);
//			Date bday = Date.valueOf(birthday);
//			pat.setBirthday(bday);
//			pat.setGender(Gender.MALE);
//			Doctor doc = (Doctor) (hu.criteriaGetObjectsByField(Doctor.class, "dr_martinez", "username").get(0));
//			pat.setDoctor(doc);
//			
//			Prescription rx = new Prescription(0, pat, "Acetominephen", "500mg", "Twice a day my mouth");
//			Prescription rx2 = new Prescription(0, pat, "Heartmeticationaitol", "5mg", "PRN");
//			
//			
//			session.beginTransaction();
//			session.save(pat);
//			session.save(rx);
//			session.save(rx2);
//			session.getTransaction().commit();
//
//		}
/*
 * Creates a patient and gives them a bill		
 */
//		try(Session session = hu.getSession().openSession()){
//			Patient patient = (Patient) (hu.criteriaGetObjectsByField(Patient.class, "my_username", "username").get(0));
//			patient = (Patient) session.merge(patient);
//			LocalDate date = LocalDate.of(1985, 9, 10);
//			Date day = Date.valueOf(date);
//			Fee fee1 = new Fee();
//			fee1.setService("Appointment");
//			fee1.setPrice(50.20f);
//			Fee fee2 = new Fee();
//			fee1.setService("Some scan");
//			fee1.setPrice(12.88f);			
//			List<Fee> fees = Arrays.asList(fee1,fee2);
//			
//			Bill bill= new Bill(0,day,patient,fees);
//			fee1.setBill(bill);
//			fee2.setBill(bill);
//			patient.getBills().add(bill);
//			
//			session.beginTransaction();
//			session.saveOrUpdate(patient);
//			session.save(bill);
//			session.getTransaction().commit();
//			
//		}
//			To make persistent a detached object		
//			Patient patient = (Patient) session.merge(pat);
		

		/*
		 * Updates a fee be getting it as a deetached object, marging it, setting new values, then saving it.
		 */
//		try(Session session = hu.getSession().openSession()){
//			Fee fee = hu.read(Fee.class, 2);
//			fee = (Fee) session.merge(fee);
//			fee.setService("Appointment charge");
//			fee.setPrice(55);
//
//			session.beginTransaction();
//			session.update(fee);
//			session.getTransaction().commit();
//		}
/*
 * Gets a patient and adds a visit information		
 */
//		try(Session session = hu.getSession().openSession()){
//			Patient patient = (Patient) (hu.criteriaGetObjectsByField(Patient.class, "my_username", "username").get(0));
//			patient = (Patient) session.merge(patient);
//			Doctor doc = (Doctor) (hu.criteriaGetObjectsByField(Doctor.class, "dr_martinez", "username").get(0));
//			patient.setDoctor(doc);
//			
//			VisitInfo visit = new VisitInfo();
//			visit.setBloodpressure("100/80");
//			Date day = Date.valueOf(LocalDate.of(1985, 9, 10));
//			visit.setDate(day);
//			visit.setDoctor(doc);
//			visit.setPatient(patient);
//			visit.setDoctorDescription("Patient expressed concerns about weight. Suggested cutting calories and light exercise");
//			visit.setWeight(200);
//			
//			session.beginTransaction();
//			session.save(visit);
//			session.getTransaction().commit();
//		
//
//		}

		
	}
}
