package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.db.HibernateTemplate;
import com.dto.Customer;
import com.dto.Professional;
import com.dto.Requests;

public class RequestsDAO {
	

	public void addRequests(int cid, int sid) {
		// TODO Auto-generated method stub
		
		List<Professional> profList = new ArrayList<Professional>();
		ProfessionalDAO professionalDAO = new ProfessionalDAO();
		profList = professionalDAO.getAllProfessionals();
		
		for(int i = 0 ; i < profList.size(); i++){
			Requests r = new Requests();
			r.setCid(cid);
			r.setSid(sid);
			r.setPid(profList.get(i).getProfessionalId());
			int x = HibernateTemplate.addObject(r);
		}
	}

	/*public List<Requests> viewRequests(int pid) {
		
		
		List<Requests> reqs = (List)HibernateTemplate.getObjectListByQuery("From Requests");
		
		List<Requests> res  = new ArrayList<Requests>();
		
		for(int i = 0 ; i < reqs.size() ; i++){
			if(reqs.get(i).getPid() == pid && reqs.get(i).getStatus() == 0){
				res.add(reqs.get(i));
			}
			System.out.println(reqs.get(i));
		}
		return reqs;
	}*/
	public List<Requests> viewRequests(int pid) {
		// TODO Auto-generated method stub
		List<Requests> reqs=(List)HibernateTemplate.getObjectListByQuery("From Requests");
List<Requests> res  = new ArrayList<Requests>();
		
		for(int i = 0 ; i < reqs.size() ; i++){
			if(reqs.get(i).getPid() == pid && reqs.get(i).getStatus() == 0){
				res.add(reqs.get(i));
			}
			
		}
		System.out.println("Inside All Requests..."+ reqs);
		return res;
	}

	private SessionFactory factory = null;
	private static SessionFactory sessionFactory;
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	public void updateRequestStatus(int ridi, int xi) {
		// TODO Auto-generated method stub
		System.out.println("Request dao- update profile");
		Session session=sessionFactory.openSession();
		org.hibernate.Transaction tx= null;
		tx = session.beginTransaction();  
		org.hibernate.Query q=session.createQuery("update Requests set status=:n where rid=:i");  
		q.setParameter("n",xi);  
		q.setParameter("i",ridi);
		
		  
		int status=q.executeUpdate();  
		System.out.println(status);  
		tx.commit();
	}

}
