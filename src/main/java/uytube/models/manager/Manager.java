package uytube.models.manager;

import org.hibernate.Session;
import org.hibernate.Transaction;

import uytube.models.HibernateUtil;

public class Manager {
	
	/*
	 * Manager para ahorrar lineas de codigo al hacer la transferencia
	 * */
	
	private Session session;
	private Transaction transaction;
	private static Manager instance = null;
	
	private Manager() {
		this.instance = this;
		this.session = null;
		this.transaction = null;
	}
	
	public static Manager getInstance() {
		if (instance == null) {
			System.out.println("Creating new Manager database");
			return new Manager();
		}
		System.out.println("Manager already created");
		return instance;
	}
	
	public void startTransaction(String columna,Object obj) {
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction();
	        if(!transaction.isActive())
	        	transaction.begin();
	        session.saveOrUpdate(columna, obj);
	        transaction.commit();
	      } catch (Exception e) {
	        if (transaction != null) {
	          transaction.rollback();
	        }
	        e.printStackTrace();
	      } finally {
	        if (session != null) {
	          session.close();
	        }
	      }	
	}
	
	
	//Cerrar session
	public void closeSession() {
		session.close();
	}
	
	//Recordar cerrar session luego de pedirla y usarla
	public Session getSessionManager() {
		return this.session;
	}
	

}
