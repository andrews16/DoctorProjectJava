package com.revature.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.revature.models.Bill;
import com.revature.models.Doctor;
import com.revature.models.Fee;
import com.revature.models.Insurance;
import com.revature.models.Patient;
import com.revature.models.Prescription;
import com.revature.models.PrescriptionArchive;
import com.revature.models.User;
import com.revature.models.VisitInfo;



@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class HibernateConfig {

	@Bean
	public LocalSessionFactoryBean getSessionFactory(@Autowired DataSource dataSource) {
		System.out.println("Configuring session factory");
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		//factoryBean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
		// set package with the annotated classes
		//factoryBean.setAnnotatedPackages("com.revature.models");
		factoryBean.setAnnotatedClasses(User.class, Doctor.class, Patient.class,
				Prescription.class, PrescriptionArchive.class, VisitInfo.class, 
				Bill.class, Fee.class, Insurance.class);
		factoryBean.setDataSource(dataSource);
		return factoryBean;
	}
	
//	@Bean(name="dataSource")
//	public DataSource getDataSource() {
//		System.out.println("Configuring data source");
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		dataSource.setUrl("jdbc:postgresql://classdb.csvriryt3t5w.us-east-2.rds.amazonaws.com:5432/project2");
//		dataSource.setUsername("project2");
//		dataSource.setPassword("jdbcp455w0rd");
//		return dataSource;
//	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager(@Autowired LocalSessionFactoryBean sessionFactory) {
		System.out.println("Configuring Transaction Manager... (This typically takes 30-60 seconds)");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory.getObject());
		return transactionManager;
	}
	
	
	
}