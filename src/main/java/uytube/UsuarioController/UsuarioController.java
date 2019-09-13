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
import uytube.ListaController.ILista;
import uytube.ListaController.ListaController;
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
		
		Canal canal = new Canal(usuario.getNickname(),"My first channel",usuario);
		//Listas default a este canal
		
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
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

	    ILista controllerLista = new ListaController();
	    //"Favoritos", "Sin Categoria", null, true, true
	    controllerLista.crearLista("Ver mas tarde", "Sin Categoria", canal.getNombre(), true, false);
	    controllerLista.crearLista("Favoritos", "Sin Categoria", canal.getNombre(), true, false);
	    controllerLista.asignarListasDefault(canal.getNombre());
	    
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
	public Usuario consultarUsuario(String nickname) {
		this.session = null;
		System.out.println(nickname);
		Usuario usuario = null;
	    try {
	    	this.session = HibernateUtil.getSessionFactory().openSession();
	        usuario = (Usuario)session.createQuery("From Usuario where nickname=:nickname").setParameter("nickname", nickname).getSingleResult();
	    } catch (Exception e) {
	      } finally {
	        if (this.session != null) {
	          this.session.close();
	        }
	      }		
		return usuario;
	}	
	public Usuario consultarEmail(String correo) {
		this.session = null;
		Usuario usuario = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        usuario = (Usuario)session.createQuery("From Usuario where correo=:correo").setParameter("correo", correo).getSingleResult();
	    } catch (Exception e) {
	      } finally {
	        if (session != null) {
	          session.close();
	        }
	      }		
		return usuario;
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

	public void seguirUsuario(String nickUser,String nameCanal) {
		// TODO Auto-generated method stub	
		
		//Usuario sigue canales
		
		//Obtener el usuario y luego el canal
		
		Usuario user = (Usuario)mng.getSessionManager().createQuery("From Usuario where nickname = :nameUser").setParameter("nameUser", nickUser).getSingleResult();
		mng.closeSession();
		
		Canal canal = (Canal)mng.getSessionManager().createQuery("From Canal where nombre = :nombreCanal").setParameter("nombreCanal", nameCanal).getSingleResult();
		mng.closeSession();
		
		user.addCanal(canal);
		
		mng.startTransaction("Usuario", user);
		
		System.out.println(user.getNickname()+" sigue a "+canal.getNombre());
		
	}
	
	public List<Canal> listCanalesSeguidos(String nick) {
		
		Usuario user = (Usuario) mng.getSessionManager().createQuery("From Usuario where nickname = :nick").setParameter("nick",nick).getSingleResult();
		
		return user.getCanalesSeguidos();
		
	}
	public List<Usuario> listUsuariosSeguidores(String nickname){
		List<Usuario> users = (List<Usuario>) mng.getSessionManager().
								createQuery("select u From Usuario as u inner join u.canalesSeguidos as canalesSeguidos where canalesSeguidos.nombre = :nick").
								setParameter("nick",nickname).getResultList();
		return users;	
		
	}
	private Usuario getUser(String nick) {
		emf = Persistence.createEntityManagerFactory("uytube");
		manager = this.emf.createEntityManager();
		Usuario user = (Usuario)manager
		.createQuery("From Usuario Where nickname = :nick")
		.setParameter("nick", nick).getSingleResult();
		
		return user;
	}
	public void dejarDeSeguir(String nickUser,String nameCanal) {
		// TODO Auto-generated method stub
		Usuario user = (Usuario)mng.getSessionManager().createQuery("From Usuario where nickname = :nameUser").setParameter("nameUser", nickUser).getSingleResult();
		mng.closeSession();
		
		Canal canal = (Canal)mng.getSessionManager().createQuery("From Canal where nombre = :nombreCanal").setParameter("nombreCanal", nameCanal).getSingleResult();
		mng.closeSession();
		
		user.dejarDeSeguir(nameCanal);
		
		mng.startTransaction("Usuario", user);
		
		
	}

	public void listarSeguidores(String nickUser) {
		
		Usuario user = (Usuario)mng.getSessionManager().createQuery("From Usuario where nickname = :nameUser").setParameter("nameUser", nickUser).getSingleResult();
		mng.closeSession();
		
		System.out.println(" :: Siguiendo :: ");
		for(Canal c:user.getCanalesSeguidos()) {
			System.out.println(c.getNombre());
		}
		
	}
	
	public void modificarDatos() {
		// TODO Auto-generated method stub
		
	}

}
