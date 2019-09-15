package uytube.CanalController;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import uytube.CanalController.ICanal;
import uytube.models.Canal;
import uytube.models.HibernateUtil;
import uytube.models.Usuario;
import uytube.models.manager.Manager;
public class CanalController implements ICanal{
	
		//Variables de conexion
		private Manager mng;
		private Session session;
		private Transaction transaction;
		private static EntityManager manager;
		private static EntityManagerFactory emf;

		public CanalController() {
			mng = Manager.getInstance();
		}
	public Canal obtenerCanalUsuario(String nickname) {
		System.out.println(nickname);
		Canal canal = (Canal)mng.getSessionManager().createQuery("From Canal where usuario_nickname = :nickname").
		setParameter("nickname", nickname).getSingleResult();
		mng.closeSession();
		return canal;
			
	}
	public void actualizarCanal(Canal canal) {
		this.session = null;
		this.transaction = null;
        session = HibernateUtil.getSessionFactory().openSession();
		try {
	        transaction = session.beginTransaction();
	        if(!transaction.isActive())
	        	transaction.begin();
	        session.saveOrUpdate("Canal", canal);
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
}
