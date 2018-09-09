package com.hibernate.cascade.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CascadeDemo {

	public static void main(String[] args) {

		UserDetails user1 =new UserDetails("User1");
		
		
		Address a1 = new Address("s1","state1", "201301", "India");
		Address a2 = new Address("s1","state1", "201301", "India");
		Address a3 = new Address("so","stateo", "201301", "India");
		
		
		user1.getDeliveryAddresses().add(a1);
		user1.getDeliveryAddresses().add(a2);
		user1.getDeliveryAddresses().add(a3);
		
		SessionFactory sessionFactory = new Configuration().configure("/com/hibernate/notfound/demo/hibernate.cascade.cfg.xml")
			    .buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.persist(user1);
		
		
		/*session.save(a1);
		session.save(a2);
		session.save(a3);
		*/
		
		session.getTransaction().commit();
		session.close();
		
	}

}
