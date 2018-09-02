package com.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {

		UserDetails user = new UserDetails();
		user.setUserName("Kumar");

		Vehicle car1 = new Vehicle("jeep");
		Vehicle car2 = new Vehicle("maruti");

		user.getVehicle().add(car1);
		user.getVehicle().add(car2);

		car1.setUser(user);
		car2.setUser(user);

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
	/*	session.save(user);
		session.save(car1);
		session.save(car2);*/
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		UserDetails v =	session.get(UserDetails.class, 4);
		System.out.println(v.getVehicle());

	}

}
