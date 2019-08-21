package Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("uytube");
	private EntityManager manager = emf.createEntityManager();
	
	private static Manager instance = null;
	
	private Manager() {
		this.instance = this;
	}
	
	public static Manager getInstance() {
		if (instance == null) {
			System.out.println("Creating new Manager database");
			return new Manager();
		}
		System.out.println("Manager already created");
		return instance;
	}
	
	public void startTransaccion(Object obj) {
		this.manager.getTransaction().begin();
		this.manager.persist(obj);
		this.manager.getTransaction().commit();
	}
	
	public EntityManager getEntityManager() {
		return this.manager;
	}
	
	
	
}
