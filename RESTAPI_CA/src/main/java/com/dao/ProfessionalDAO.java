package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.db.HibernateTemplate;
import com.dto.Customer;
import com.dto.Professional;

public class ProfessionalDAO {
	private SessionFactory factory = null;


	public int register(Professional professional) {
		System.out.println("DAO"+professional); 
		return HibernateTemplate.addObject(professional);
	}

	

	public List<Professional> getAllCustomers() {
		// TODO Auto-generated method stub
		List<Professional> professionals=(List)HibernateTemplate.getObjectListByQuery("From Customer");
		System.out.println("Inside All Customers ..."+professionals);
		return professionals;
	}



	public static Professional getProfByUserPass(String mailId, String password) {
		// TODO Auto-generated method stub
		System.out.println("DAO "+mailId + " " + password);

		return (Professional)HibernateTemplate.getObjectByUserPassProf(mailId,password);
	}

}
