 package com.qiheng.hibernate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Test1 {
	public static SessionFactory sessionFactory;

	static {
		try {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		Student student = new Student();
		student.setName("qiheng");
		student.setNumber(0);
		student.setAge(21);
		student.setTimestamp(new Timestamp(new Date().getTime()));
		
		Student student2 = new Student();
		student2.setName("qiheng");
		student2.setNumber(123314);
		student2.setAge(43);
		student2.setTimestamp(new Timestamp(new Date().getTime()));
		
		try{
			tx = session.beginTransaction();
			session.save(student);
			session.save(student2);
			
			
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
		
		
		}finally{
			session.close();
		}
		
		
	}
}

