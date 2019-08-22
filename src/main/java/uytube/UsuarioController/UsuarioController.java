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
import uytube.models.HibernateUtil;
public class UsuarioController implements IUsuario{
	private Session session;
	private Transaction transaction;
	public UsuarioController() {
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
	public void modificarUsuario(Usuario usuario) {
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

	public void seguirUsuario(String name1,String name2) {
		// TODO Auto-generated method stub
		EntityManager em = this.mng.getEntityManager();
		Usuario user1 = (Usuario)em
		.createQuery("From Usuario Where nickname = :nick")
		.setParameter("nick", name1).getSingleResult();
		
		Usuario user2 = (Usuario)em
		.createQuery("From Usuario Where nickname = :nick")
		.setParameter("nick", name2).getSingleResult();
		
		user1.addUsuario(user2);
		
		this.mng.startTransaccion(user1);
		
	}
	
	public void listUsuariosSeguidos(String name) {
		
		Usuario user = this.getUser(name);
		
		for(Usuario u:user.getusuariosSeguidos()) {
			System.out.println(u.getNickname());
		}
	}

	private Usuario getUser(String nick) {
		EntityManager em = this.mng.getEntityManager();
		Usuario user = (Usuario)em
		.createQuery("From Usuario Where nickname = :nick")
		.setParameter("nick", nick).getSingleResult();
		
		return user;
	}
	public void dejarDeSeguir(String nick1,String nick2) {
		// TODO Auto-generated method stub
		
		Usuario user = this.getUser(nick1);
		Usuario user2 = this.getUser(nick2);
		
		user.getusuariosSeguidos().remove(user2);
		
		this.mng.startTransaccion(user);
		
	}

	public void modificarDatos() {
		// TODO Auto-generated method stub
		
	}

}
