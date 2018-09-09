package com.model.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class OneToManyTest {

	public static void main(String[] args) {

		
		Platform platform = new Platform();
		platform.setPlatformName("WEB");
		
		Page homePage = new Page();
		
		homePage.setPageName("Et Marlers home page");
		
		Page wealthPage = new Page();
		wealthPage.setPageName("ET Wealth Home Page");
		
		
		platform.getPages().add(homePage);
		platform.getPages().add(wealthPage);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(platform);
		session.save(homePage);
		session.save(wealthPage);
		session.getTransaction().commit();
		session.close();
		

	

	}

}
