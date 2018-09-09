package com.hibernate.crud.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrudDemo {
	
	private static SessionFactory sessionFactory;
	
	static{
		sessionFactory = new Configuration()
				.configure("/com/hibernate/crud/demo/hibernate.crud.cfg.xml")
				.buildSessionFactory();

	}

	public CrudDemo() {
		super();
		
	}

	public static void main(String[] args) {

		System.out.println("Creating 10 dummy users");
		createUsers();
		System.out.println("Users created");
		
		System.out.println("Printing Username for UserId 6");
		UserDetails user = getUser(6l);
		
		System.out.println("Deleting user with Id 9");
		deleteUser(user);
		
		UserDetails user9 = getUser(9l);
		user9.setName("UpdatedUser");
		System.out.println("Updating User");
		updateUser(user9);
		
	}
	
	public static void createUsers() {
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		
		for (int i = 1; i <= 10; i++) {
			UserDetails user = new UserDetails();
			user.setName("User " + i);
			session.save(user);
		}

		session.getTransaction().commit();
		session.close();
	}
	
	public static UserDetails getUser(Long userId) {
		
		UserDetails user = null;
		
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		user = session.get(UserDetails.class, userId );

		session.getTransaction().commit();
		session.close();
		
		
		if(user!=null) {
			System.out.println("User with user Name "+user.getName()+" *READ*");
		}else {
			System.out.println("user Does not exist for Id"+userId);
		}
		return user;
	}
	
	public static void deleteUser(UserDetails user) {
		
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		
		if(user!=null) {
			session.delete(user);
			System.out.println("User with user Name "+user.getName()+" *DELETED*");
		}else {
			System.out.println("user Does not exist for Id");
		}
		

		session.getTransaction().commit();
		session.close();
		
		
		
	}
	
	public static void updateUser(UserDetails user) {
		
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		
		if(user!=null) {
			session.update(user);
			System.out.println("User with user Name "+user.getName()+" *UPDATED*");
		}else {
			System.out.println("user Does not exist for Id");
		}
		

		session.getTransaction().commit();
		session.close();
		
		
		
	}

}
