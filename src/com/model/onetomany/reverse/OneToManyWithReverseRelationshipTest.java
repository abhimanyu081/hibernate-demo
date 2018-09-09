package com.model.onetomany.reverse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class OneToManyWithReverseRelationshipTest {

	public static void main(String[] args) {
		


		
		Platform platform = new Platform();
		platform.setPlatformName("WEB");
		
		Page homePage = new Page();
		
		homePage.setPageName("Et Marlers home page");
		
		Page wealthPage = new Page();
		wealthPage.setPageName("ET Wealth Home Page");
		
		Page newPage = new Page();
		newPage.setPageName("Orphan Page");
		
		platform.getPages().add(homePage);
		platform.getPages().add(wealthPage);
		
		wealthPage.setPlatform(platform);
		homePage.setPlatform(platform);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(platform);
		session.save(homePage);
		session.save(wealthPage);
		session.save(newPage);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("Fetching Page Date");
		platform=null;
		session = sessionFactory.openSession();

		session.beginTransaction();
		Page loadedPAge =  session.get(Page.class, 3l);
		Platform p =loadedPAge.getPlatform();
		
		System.out.println(p.getPlatformName());
		session.getTransaction().commit();
		session.close();
		
		

	

	

	}

}
