package com.dto;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {
		
		UserDetails user = new UserDetails();
		user.setName("user Name");
		user.setJoinedDate(new Date());
		user.setOfficeAddress(new Address("st1", "Noida14", "UP", "201310"));
		user.setHomeAddress(new Address("D148", "GZB", "UP", "201310"));
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

}