package com.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dto.Customer;
import com.dto.Professional;


public class HibernateTemplate {

	private static SessionFactory sessionFactory;
	
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public static int addObject(Object obj)
	{
		System.out.println("Inside Template...");
		int result=0;
		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.save(obj);
			
			tx.commit();
			
			result=1;
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Object getObject(Class c,Serializable serializable)
	{
		Object obj=null;
		
		try {			
			return sessionFactory.openSession().get(c,serializable);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public static Object getObjectByUserPass(String emailId,String password) {
		System.out.println("mailId" + emailId + " password " + password);
		
		String queryString = "from Customer where emailId = :emailId and password =:password";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("emailId", emailId);
		  query.setString("password", password);
		  Object queryResult = query.uniqueResult();
		  Customer customer = (Customer)queryResult;
		  System.out.println(customer);
		  return customer; 
	}
	
	public static Object getObjectByUserPassProf(String emailId,String password) {
		System.out.println("mailId" + emailId + " password " + password);
		
		String queryString = "from Professional where emailId = :emailId and password =:password";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("emailId", emailId);
		  query.setString("password", password);
		  Object queryResult = query.uniqueResult();
		  Professional professional = (Professional)queryResult;
		  System.out.println(professional);
		  return professional; 
	}
	/*public static Object getObjectByUserPass(String emailId,String password) {
		
		System.out.println("mailId" + emailId + " password " + password);
		
		String queryString = "from customer where emailId = :emailId and password = :password";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("emailId", emailId);
		  query.setString("password", password);
		  Object queryResult = query.uniqueResult();
		  Customer customer = (Customer)queryResult;
		  System.out.println(customer);
		  return customer; 
		}
	
	public static Object getObjectByUserPass(String loginId,String password) {
	
	String queryString = "from Employee where email = :loginId and password =:password";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("loginId", loginId);
	  query.setString("password", password);
	  Object queryResult = query.uniqueResult();
	  Employee employee = (Employee)queryResult;
	  return employee; 
	}
	public static Object getObjectByEmail(String email) {
		
		String queryString = "from Employee where email = :email";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("email", email);
		  Object queryResult = query.uniqueResult();
		  Employee employee = (Employee)queryResult;
		  return employee; 
		}*/
	
	public static List<Object> getObjectListByQuery(String query)
	{
		return sessionFactory.openSession().createQuery(query).list();
	}

	
	
	public static int updateObject(Object obj)
	{
		int result=0;
		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.saveOrUpdate(obj);
			
			tx.commit();
			
			result=1;
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int deleteObject(Class c,Serializable serializable)
	{
		int result=0;
		
		Session session=sessionFactory.openSession();
		
		Transaction tx=session.beginTransaction();
		
		try {
			
			Object obj=session.get(c,serializable);
			
			session.delete(obj);
			
			tx.commit();
			
			result=1;
						
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tx.rollback();
		}
		
		return result;
	}

	public static List<Object> getObjectListByName(Class c, String columName, String value) {
		Session session=sessionFactory.openSession();
		  Criteria criteria = session.createCriteria(c);
			Criterion nameCriterion = Restrictions.eq(columName, value);
			criteria.add(nameCriterion);
			return criteria.list();
	}
}
