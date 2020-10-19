package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;


import com.db.HibernateTemplate;
import com.dto.Customer;

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

}
