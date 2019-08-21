package uytube.CategoriaController;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import uytube.CategoriaController.ICategoria;
import uytube.models.Categoria;
import uytube.models.Video;
public class CategoriaController implements ICategoria{
	
		//Variables de conexion
		private static EntityManager manager;
		private static EntityManagerFactory emf;

		public CategoriaController() {
		}
	public void altaCategoria(String nombre) {
			
		if(!existeCategoria(nombre)) {
			
		emf = Persistence.createEntityManagerFactory("uytube");
		manager = emf.createEntityManager();
		
		Categoria c = new Categoria(nombre);
		manager.getTransaction().begin();
		manager.persist(c);		
		manager.getTransaction().commit();
		
		System.out.println("La categoria: "+nombre+ " fue creada");
			
	}/*else if(consultarCategoria(nombre)) {
		System.out.println("La categoria: "+nombre+" ya existe.");
	}*/
}
	
	public boolean existeCategoria(String nombre) {
		emf = Persistence.createEntityManagerFactory("uytube");
		manager = emf.createEntityManager();	
		
		
		List<Categoria> categorias = (List<Categoria>) manager.createQuery("From Categoria").getResultList();
		
		for(Categoria c:categorias) {
			if(c.getNombre().equals(nombre)) {
				return true;
			}
		}
		
		return false;
	}

	public List<Video> consultarCategoria(String nombreCategoria) {
		
		/*
		 Caso de uso Consulta de Categoría
		 Actores Administrador del Sistema
			Descripción El caso de uso comienza cuando el administrador desea consultar una categoría.
			En primer lugar, el sistema lista todas las categorías existentes y
			al elegir una se mostrará un listado (nombre y usuario propietario) de todos los videos 
			y listas de reproducción que tenga dicha categoría.
*/
		emf = Persistence.createEntityManagerFactory("uytube");
		manager = emf.createEntityManager();	
		
		List<Categoria> categorias = (List<Categoria>) manager.createQuery("From Categoria").getResultList();
		
		
		////// a implementar //////
		List<Video> v = null;
		
		return v;
				
		
		
	}
	
public void listarCategoriasExistentes() {
		
		emf = Persistence.createEntityManagerFactory("uytube");
		manager = emf.createEntityManager();
		
		List<Categoria> categorias = (List<Categoria>) manager.createQuery("From Categoria").getResultList();
		System.out.println("Listando categoria");
		for(Categoria c:categorias) {
			System.out.println(c.getNombre());
		}
		
	}

	public List<Categoria> listarCategorias() {
		
		emf = Persistence.createEntityManagerFactory("uytube");
		manager = emf.createEntityManager();
		
		List<Categoria> categorias = (List<Categoria>) manager.createQuery("From Categoria").getResultList();
		
		for(Categoria c:categorias) {
			System.out.println(c.getNombre());
		}
		return categorias;
		
	}	

}
