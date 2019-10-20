package uytube.CategoriaController;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import uytube.CategoriaController.ICategoria;
import uytube.models.Categoria;
import uytube.models.Video;
import uytube.models.manager.Manager;
public class CategoriaController implements ICategoria{
	
		//Variables de conexion
		private Manager mng;

		public CategoriaController() {
			mng = Manager.getInstance();
		}
	
   public Categoria obtenerCategoria(String categoria) {
	 Categoria cat = (Categoria) mng.getSessionManager().createQuery("from Categoria where nombre = :nombre").setParameter("nombre", categoria).getSingleResult();
	   mng.closeSession();
	   return cat;
   }
		
	public boolean altaCategoria(String nombre) {
		boolean existe = existeCategoria(nombre);
		if(!existe) {
			mng.startTransaction("Categoria", new Categoria(nombre));
			
		}
				
		return !existe;
		
	}
	
	private boolean existeCategoria(String nombre) {
		boolean existe = true;			
		

		try {
			mng.getSessionManager().createQuery("From Categoria where nombre = :nombre").setParameter("nombre",nombre).getSingleResult();
			mng.closeSession();
		} catch (Exception e) {
			existe = false;
		}
		 return existe;
	}

	/*public List<Video> consultarCategoria(String nombreCategoria) {
		
		/*
		 Caso de uso Consulta de Categoría
		 Actores Administrador del Sistema
			Descripción El caso de uso comienza cuando el administrador desea consultar una categoría.
			En primer lugar, el sistema lista todas las categorías existentes y
			al elegir una se mostrará un listado (nombre y usuario propietario) de todos los videos 
			y listas de reproducción que tenga dicha categoría.

		emf = Persistence.createEntityManagerFactory("uytube");
		manager = emf.createEntityManager();	
		
		List<Categoria> categorias = (List<Categoria>) manager.createQuery("From Categoria").getResultList();
		
		
		////// a implementar //////*/
		//List<Video> v = null;
		
		//return v;		
		
		
	//}*/
	
	
	
/*public void listarCategoriasExistentes() {
		
		List<Categoria> categorias = (List<Categoria>) mng.getSessionManager().createQuery("From Categoria").getResultList();
		mng.closeSession();
		System.out.println("Listando categoria");
		for(Categoria c:categorias) {
			System.out.println(c.getNombre());
		}
		
	}*/

	public String[] listarCategoriasName(){
		
		List<Categoria> categorias = (List<Categoria>) mng.getSessionManager().createQuery("From Categoria").getResultList();
		mng.closeSession();
		

		String [] names = new String[categorias.size()];
		
		for(int i = 0; i<categorias.size();i++) {
			names[i] = categorias.get(i).getNombre();
		}
		
		return names;
	}
	public ArrayList<Categoria> listarCategorias() {
		
		ArrayList<Categoria> categorias = (ArrayList<Categoria>) mng.getSessionManager().createQuery("From Categoria").getResultList();
		mng.closeSession();
		
		for(Categoria c:categorias) {
			System.out.println(c.getNombre());			
		}
		
		return categorias;
		
	}
	
	public void modificarCategoria(Categoria nuevoNombre) {
				mng.startTransaction("Categoria", nuevoNombre);
				System.out.println(nuevoNombre.getNombre()+" Editado Correctamente!");
	}

}
