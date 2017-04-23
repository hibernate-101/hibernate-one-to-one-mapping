package com.hibernate.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Customer;
import com.hibernate.model.TransactionDemo;
import com.hibernate.util.HibernateUtil;

public class HibernateXMLOneToOneMain {
	public static void main(String[] args) {
		TransactionDemo txn = buildDemoTransaction();
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// Get Session
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Session created");
			// start transaction
			tx = session.beginTransaction();
			// Save the Model object
			session.save(txn);// it has txn as well as customer so only save the parent , due to cascade it will save the child otable too.git it..?yes
			// Commit transaction
			tx.commit();
			System.out.println("Transaction ID=" + txn.getId());// we will pass this txn id to retrive txn object

			// Get Saved Trasaction Data
			printTransactionData(txn.getId(), sessionFactory);// passing txn id here.

		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (!sessionFactory.isClosed()) {
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}

	private static void printTransactionData(long id,
			SessionFactory sessionFactory) {
		Session session = null;
		Transaction tx = null;
		try {
			// Get Session
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			// start transaction
			tx = session.beginTransaction();
			// Save the Model object
			TransactionDemo txn = (TransactionDemo) session.get(
					TransactionDemo.class, id);// calling get to retrieve txn data by passing txn id.
			// Commit transaction
			tx.commit();
			System.out.println("Transaction Details=\n" + txn);

		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	private static TransactionDemo buildDemoTransaction() {
		// TODO Auto-generated method stub
		TransactionDemo txn = new TransactionDemo();
		txn.setDate(new Date());
		txn.setTotal(2000);// txn created
		
		Customer cust = new Customer();
		cust.setAddress("Paris,France");
		cust.setName("SMITH");
		cust.setEmail("Smith@gmail.com");
		
		txn.setCustomer(cust);// seting cutomer to txn
		cust.setTransaction(txn);

		return txn;
	}

}
