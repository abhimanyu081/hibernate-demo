package com.dto;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {
		
		UserDetails user = new UserDetails();
		user.setName("user Name");
		user.setJoinedDate(new Date());
		user.getListOfAddresses().add(new Address("st1", "Noida14", "UP", "201310"));
		
		user.getListOfAddresses().add(new Address("D148", "GZB", "UP", "201310"));
		
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		//session.save(user);
		session.getTransaction().commit();
		session.close();
		
		user=null;
		session = sessionFactory.openSession();
		user=session.get(UserDetails.class, 1);
		session.close();
		
		
		
		for(Address a : user.getListOfAddresses()) {
			System.out.println(a);
		}
		
	}

}
