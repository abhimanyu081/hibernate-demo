package com.hibernate.inheritance.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class InheritanceDemo {

	public static void main(String[] args) {
		Vehicle v = new Vehicle();
		v.setVehicleName("Car");

		TwoWheeler tw = new TwoWheeler();
		tw.setVehicleName("Bike");
		tw.setSteeringHandle("Bike steering handle");
		
		FourWheeler fw = new FourWheeler();
		fw.setVehicleName("Car");
		fw.setSteeringHandle("Porsche steering handle");
		

		SessionFactory sessionFactory = new Configuration().configure("/com/hibernate/inheritance/demo/hibernate.inheritance.cfg.xml")
			    .buildSessionFactory();
		
		
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(v);
		session.save(fw);
		session.save(tw);
		
		
		session.getTransaction().commit();
		session.close();
		
	}

}
