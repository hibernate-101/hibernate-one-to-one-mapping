package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.TransactionAnnotationDemo;
import com.hibernate.model.TransactionDemo;
import com.hibernate.util.HibernateAnnotationUtil;


public class HibernateAntnOneToOneMappingGet {
public static void main(String[] args) {
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction tx = null;
	sessionFactory = HibernateAnnotationUtil.getSessionFactory();
	session = sessionFactory.getCurrentSession();
	System.out.println("Session created");
	// start transaction
	tx = session.beginTransaction();
	TransactionAnnotationDemo txn = (TransactionAnnotationDemo) session.get(
			TransactionAnnotationDemo.class, 16L);
	System.out.println(txn);

	tx.commit();
	sessionFactory.close();
}
}
