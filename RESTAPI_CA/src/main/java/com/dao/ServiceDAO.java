package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.db.HibernateTemplate;
import com.dto.Customer;
import com.dto.Professional;
import com.dto.Services;

public class ServiceDAO {
	private static SessionFactory sessionFactory;
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}

	public int updateServices(Services service) {
		// TODO Auto-generated method stub
		
		System.out.println("Service DAO"+service); 
		return HibernateTemplate.addObject(service);
	}

	public List<Services> getAllServices() {
		// TODO Auto-generated method stub
		System.out.println("Service DAO All Service .........");
		List<Services> s=(List)HibernateTemplate.getObjectListByQuery("From Services");
		System.out.println("Inside All Services ..."+s);
		return s;
		
	}

	public void updateServicesProf(Services service) {
		// TODO Auto-generated method stub
		System.out.println("service dao- update prof");
		Session session=sessionFactory.openSession();
		org.hibernate.Transaction tx= null;
		tx = session.beginTransaction();  
		org.hibernate.Query q=session.createQuery("update Services set professionalId=:p where serviceId=:i");  
		q.setParameter("p",service.getProfessional().getProfessionalId());  
		q.setParameter("i",service.getServiceId());
		  
		int status=q.executeUpdate();  
		System.out.println(status);  
		tx.commit();
	}
}
