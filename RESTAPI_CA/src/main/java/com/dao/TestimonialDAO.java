package com.dao;

import java.util.List;

import com.db.HibernateTemplate;
import com.dto.Customer;
import com.dto.Testimonials;

public class TestimonialDAO {

	public int store(Testimonials x) {
		// TODO Auto-generated method stub
		return HibernateTemplate.addObject(x);
	}

	
	public List<Testimonials> getAllTestimonials() {
		// TODO Auto-generated method stub
		List<Testimonials> testimonials=(List)HibernateTemplate.getObjectListByQuery("From Testimonials");
		System.out.println("Inside All Testimonials ..."+testimonials);
		return testimonials;
	}
}
