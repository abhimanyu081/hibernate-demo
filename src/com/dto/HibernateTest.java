package com.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {

		UserDetails user = new UserDetails();
		user.setUserName("Kumar");
	

		RentedVehicle v1 = new RentedVehicle();
		RentedVehicle v2 = new RentedVehicle();

		v1.setVehicleName("jeep");
		v1.setVehicleName("maruti");
		
		user.getVehiclesList().add(v1);
		user.getVehiclesList().add(v2);
		
		//user2.getVehiclesList().add(v1);
		//user2.getVehiclesList().add(v2);
		
		
		v1.getUserList().add(user);
		v2.getUserList().add(user);
		

	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(user);
		session.save(v1);
		session.save(v2);
		session.getTransaction().commit();
		session.close();
		
		/*session = sessionFactory.openSession();
		UserDetails v =	session.get(UserDetails.class, 4);
		System.out.println(v.getVehicle());*/

	}

}
