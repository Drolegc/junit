package uytube.ListaController;

import java.util.List;

import javax.persistence.EntityManager;

import Manager.Manager;
import uytube.models.Canal;
import uytube.models.Lista;

public class ListaController implements ILista {

	private Manager mng;
	
	public ListaController() {
		mng = Manager.getInstance();
	}
	
	public void crearLista(String listaName) {
		// TODO Auto-generated method stub
		
		//POR EL MOMENTO ESTAMOS CREANDO UNA LISTA DEFAULT
		EntityManager em = mng.getEntityManager();
		
		try {
			Lista lista = (Lista) em.createQuery("From Lista where nombre = :name").setParameter("name", listaName).getSingleResult();
		}catch(Exception NoResultException) {
			List<Canal> canales = (List<Canal>)em.createQuery("From Canal").getResultList();
			Lista l = new Lista(listaName,true,null);
			for(Canal c: canales) {
				c.addLista(l);
			}
			System.out.println("Nueva lista creada para todos los usuarios");
		}
		
		
	}

	public void modificarLista() {
		// TODO Auto-generated method stub
		
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

}
