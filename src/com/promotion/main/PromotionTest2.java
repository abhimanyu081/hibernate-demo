package com.promotion.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.onetoone.Page;
import com.model.onetoone.Platform;

public class PromotionTest2 {

	public static void main(String[] args) {
		
		Platform platform = new Platform();
		platform.setPlatformName("WEB");
		
		Page homePage = new Page();
		
		homePage.setPageName("Et Marlers home page");
		
		platform.setPage(homePage);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(platform);
		session.save(homePage);
		session.getTransaction().commit();
		session.close();
		

	}

}
