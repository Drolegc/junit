package uytube.ListaController;

import java.util.List;

import javax.persistence.EntityManager;

import uytube.models.manager.Manager;
import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.Lista;

public class ListaController implements ILista {

	private Manager mng;
	
	public ListaController() {
		mng = Manager.getInstance();
	}
	
	public void crearLista(String listaName,String categoria,String nombreUser,boolean privado,boolean Default) {
		// TODO Auto-generated method stub
		
		//POR EL MOMENTO ESTAMOS CREANDO UNA LISTA DEFAULT
		
		/*
		 * Cuando una lista es default
		 * por cada canal se genera una lista nueva y el canal se agrega a la lista
		 * 
		 * lista default (nombre,privado,nulo)
		 * */
		
		//Obtener canales
		
		if(Default) {
			
			List<Canal> canales = (List<Canal>) mng.getSessionManager().createQuery("From Canal").getResultList();
			mng.closeSession();

			//Al ser default hay que buscar la categoria llamada "Sin Categoria"
			
			Categoria cat = (Categoria)mng.getSessionManager().createQuery("From Categoria where nombre = :nombre").setParameter("nombre", "Sin Categoria").getSingleResult();
			mng.closeSession();
			
			System.out.println(canales.size());
			for(Canal c: canales) {
				System.out.println("Lista default");
				Lista lista = new Lista(listaName,privado,null,c);
				mng.startTransaction("Lista", lista);
				
			}
			
			System.out.println("Listas default creadas");
			
		}else {
			
			/*
			 * Cuando una lista es personalizada
			 * buscamos el canal 
			 * 
			 * */
			Canal canal = (Canal)mng.getSessionManager().createQuery("From Canal where nombre = :nombre").setParameter("nombre", nombreUser).getSingleResult();
			mng.closeSession();
			
			Categoria cat = (Categoria)mng.getSessionManager().createQuery("From Categoria where nombre = :nombre").setParameter("nombre", categoria).getSingleResult();
			mng.closeSession();
			
			Lista lista = new Lista(listaName,privado,cat,canal);
			mng.startTransaction("Lista",lista);
			
			System.out.println("Lista agregada a un unico canal " + canal.getNombre());
		}
		
		
		
	}

	public void modificarLista(int id,String categoria,boolean privacidad) {
		// TODO Auto-generated method stub
		
		//Obtener lista segun id
		
		Lista list = (Lista)mng.getSessionManager().createQuery("From Lista where id = :id").setParameter("id", id).getSingleResult();
		mng.closeSession();
		
		Categoria cat = (Categoria)mng.getSessionManager().createQuery("From Categoria where nombre = :nombre").setParameter("nombre", categoria).getSingleResult();
		mng.closeSession();
		
		list.setCategoria(cat);
		list.setPrivado(privacidad);
		
		mng.startTransaction("Lista", list);
	}

	public void consultarListas() {
		// TODO Auto-generated method stub
		
	}

	public void agregarVideo() {
		// TODO Auto-generated method stub
		
	}

	public void quitarVideo() {
		// TODO Auto-generated method stub
		
	}

	public List<Lista> listarListas(String userName) {
		// TODO Auto-generated method stub
		
		List<Lista> listas = (List<Lista>)mng.getSessionManager().createQuery("From Lista").getResultList();
		mng.closeSession();
		
		System.out.println("Listando listas");
		for(Lista l: listas) {
			if(l.getCategoria()!=null) {
				System.out.println(l.getNombre() + " " + l.getCanal().getNombre()+" "+l.getId()+" "+l.getCategoria().getNombre());
			}
			else {
				System.out.println(l.getNombre()+" "+l.getCanal().getNombre()+" "+l.getId());
			}
				
		}
		
		return listas;
	}

}
