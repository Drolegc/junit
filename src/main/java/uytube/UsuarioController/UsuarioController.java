package uytube.UsuarioController;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import org.hibernate.Transaction;
import org.hibernate.Session;
import uytube.models.Usuario;
import uytube.models.manager.Manager;
import uytube.models.Canal;
import uytube.models.HibernateUtil;
public class UsuarioController implements IUsuario{
	
	private Manager mng;
	
	private Session session;
	private Transaction transaction;
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	public UsuarioController() {
		mng = Manager.getInstance();
	}
	
	public void crearUsuario(Usuario usuario) {
		this.session = null;
		this.transaction = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction();
	        if(!transaction.isActive())
	        	transaction.begin();
	        session.saveOrUpdate("Usuario", usuario);
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

	public void consultaUsuario(String nickname) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Usuario> listaUsuarios() {
		this.session = null;
		ArrayList<Usuario> usuarios = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        usuarios = (ArrayList<Usuario>)session.createQuery("From Usuario").getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	      } finally {
	        if (session != null) {
	          session.close();
	        }
	      }		
		return usuarios;
	}
	public ArrayList<Usuario> consultarUsuario(String nickname) {
		this.session = null;
		System.out.println(nickname);
		ArrayList<Usuario> usuarios = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        usuarios = (ArrayList<Usuario>)session.createQuery("From Usuario where nickname=:nickname").setParameter("nickname", nickname).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	      } finally {
	        if (session != null) {
	          session.close();
	        }
	      }		
		return usuarios;
	}	
	public void modificarUsuario(Usuario usuario) {
		this.session = null;
		this.transaction = null;
        session = HibernateUtil.getSessionFactory().openSession();
		try {
	        transaction = session.beginTransaction();
	        if(!transaction.isActive())
	        	transaction.begin();
	        session.saveOrUpdate("Usuario", usuario);
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

	public void seguirUsuario(String nameUser,String nameCanal) {
		// TODO Auto-generated method stub	
		
		//Usuario sigue canales
		
		//Obtener el usuario y luego el canal
		
		Usuario user = (Usuario)mng.getSessionManager().createQuery("From Usuario where nombre = :nameUser").setParameter("nameUser", nameUser).getSingleResult();
		mng.closeSession();
		
		Canal canal = (Canal)mng.getSessionManager().createQuery("From Canal where nombre = :nombreCanal").setParameter("nombreCanal", nameCanal).getSingleResult();
		mng.closeSession();
		
		user.addCanal(canal);
		
		mng.startTransaction("Usuario", user);
		
	}
	
	public void listCanalesSeguidos(String name) {
		
		Usuario user = (Usuario) mng.getSessionManager().createQuery("From Usuario where nombre = :nombre").setParameter("nombre",name).getSingleResult();
		for(Canal c:user.getCanalesSeguidos()) {
			System.out.println(c.getNombre());
		}
		
	}

	private Usuario getUser(String nick) {
		emf = Persistence.createEntityManagerFactory("uytube");
		manager = this.emf.createEntityManager();
		Usuario user = (Usuario)manager
		.createQuery("From Usuario Where nickname = :nick")
		.setParameter("nick", nick).getSingleResult();
		
		return user;
	}
	public void dejarDeSeguir(String nameUser,String nameCanal) {
		// TODO Auto-generated method stub
		Usuario user = (Usuario)mng.getSessionManager().createQuery("From Usuario where nombre = :nameUser").setParameter("nameUser", nameUser).getSingleResult();
		mng.closeSession();
		
		Canal canal = (Canal)mng.getSessionManager().createQuery("From Canal where nombre = :nombreCanal").setParameter("nombreCanal", nameCanal).getSingleResult();
		mng.closeSession();
		
		if(user.getCanalesSeguidos().remove(canal)) {
			System.out.println("Canal removido");
		}
		
		mng.startTransaction("Usuario", user);
		
	}

	public void modificarDatos() {
		// TODO Auto-generated method stub
		
	}

}
