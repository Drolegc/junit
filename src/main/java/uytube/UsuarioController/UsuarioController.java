package uytube.UsuarioController;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import uytube.models.Usuario;

public class UsuarioController implements IUsuario{
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	public UsuarioController() {
		emf = Persistence.createEntityManagerFactory("uytube");
		manager = emf.createEntityManager();
	}
	public void crearUsuario(Usuario usuario) {
		try {
			manager.getTransaction().begin();
			manager.persist(usuario);
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El usuario ya existe");
		}
	}

	public void consultaUsuario(String nickname) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Usuario> listaUsuarios() {
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) manager.createQuery("From Usuario").getResultList();
		System.out.println("hay" + usuarios.size() +" usuarios");
		return usuarios;
	}

	public void seguirUsuario() {
		// TODO Auto-generated method stub
		
	}

	public void dejarDeSeguir() {
		// TODO Auto-generated method stub
		
	}

	public void modificarDatos() {
		// TODO Auto-generated method stub
		
	}

}
