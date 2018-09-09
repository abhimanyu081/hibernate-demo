package com.hibernate.notfound.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class NotFoundDemo {

	public static void main(String[] args) {

		UserDetails user1 =new UserDetails("User1");
		UserDetails user2 =new UserDetails("User2");
		
		Address a1 = new Address("s1","state1", "201301", "India");
		Address a2 = new Address("s1","state1", "201301", "India");
		Address a3 = new Address("so","stateo", "201301", "India");
		Address orphanAddress = new Address("s1","state1", "201301", "India");
		
		user1.getDeliveryAddresses().add(a1);
		user1.getDeliveryAddresses().add(a2);
		user2.getDeliveryAddresses().add(a3);
		
		SessionFactory sessionFactory = new Configuration().configure("/com/hibernate/notfound/demo/hibernate.notfound.cfg.xml")
			    .buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(user1);
		session.save(user2);
		
		session.save(a1);
		session.save(a2);
		session.save(a3);
		session.save(orphanAddress);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println("Fetching Address Date");
		
		session = sessionFactory.openSession();

		session.beginTransaction();
		Address address =  session.get(Address.class, 6l);
		
		System.out.println(address.getUser());
		session.getTransaction().commit();
		session.close();
		
		

	

	

	

	}

}
