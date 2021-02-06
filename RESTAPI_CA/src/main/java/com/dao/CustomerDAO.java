package com.dao;

import java.util.List;


import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.db.HibernateTemplate;
import com.dto.Customer;
import com.mysql.cj.Query;

public class CustomerDAO {
	private SessionFactory factory = null;


	public int register(Customer customer) {
		System.out.println("DAO"+customer); 
		return HibernateTemplate.addObject(customer);
	}

	public Customer getCustByUserPass(String mailId,String password) {
		
		System.out.println("DAO "+mailId + " " + password);

		return (Customer)HibernateTemplate.getObjectByUserPass(mailId,password);
	}

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		List<Customer> customers=(List)HibernateTemplate.getObjectListByQuery("From Customer");
		System.out.println("Inside All Customers ..."+customers);
		return customers;
	}

	public Customer getCustByEmail(String email) {
		// TODO Auto-generated method stub
		System.out.println("THERERERERE......DAO "+email);
		return (Customer)HibernateTemplate.getCustByEmail(email);
		
	}

	private static SessionFactory sessionFactory;
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}

	public void updateProfile(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("customer dao- update profile");
		Session session=sessionFactory.openSession();
		org.hibernate.Transaction tx= null;
		tx = session.beginTransaction();  
		org.hibernate.Query q=session.createQuery("update Customer set customerName=:n, district=:d,"
				+ "emailId=:e, landmark=:l, mobNum=:m, state=:s where customerId=:i");  
		q.setParameter("n",customer.getCustomerName());  
		q.setParameter("i",customer.getCustomerId());
		q.setParameter("d",customer.getDistrict());
		q.setParameter("l",customer.getLandmark());
		q.setParameter("m",customer.getMobNum());
		q.setParameter("s",customer.getState());
		q.setParameter("e", customer.getEmailId());
		
		  
		int status=q.executeUpdate();  
		System.out.println(status);  
		tx.commit();
	}

}
