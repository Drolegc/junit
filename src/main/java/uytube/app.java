package uytube;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import uytube.models.Usuario;
import java.util.List;
import java.util.GregorianCalendar;
public class app {
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("uytube");
		manager = emf.createEntityManager();
		Usuario u = new Usuario("brunod","bruno","bruno","bruno", new GregorianCalendar(1996,2,27).getTime(),"bruno");
		manager.getTransaction().begin();
		manager.persist(u);
		manager.getTransaction().commit();
		List<Usuario> usuarios = (List<Usuario>) manager.createQuery("From Usuario").getResultList();
		System.out.println("hay" + usuarios.size() +" usuarios");
		for(Usuario user:usuarios) {
			System.out.println(user.getNombre());
		}
	}

}
