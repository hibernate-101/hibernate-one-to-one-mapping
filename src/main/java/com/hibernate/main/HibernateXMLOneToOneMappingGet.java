package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Customer;
import com.hibernate.model.TransactionDemo;
import com.hibernate.util.HibernateUtil;

public class HibernateXMLOneToOneMappingGet {
	public static void main(String[] args) {

		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		System.out.println("Session created");
		// start transaction
		tx = session.beginTransaction();
		TransactionDemo txn = (TransactionDemo) session.get(
				TransactionDemo.class, 22L);
		System.out.println(txn);

		tx.commit();
		sessionFactory.close();

		
	}

}
