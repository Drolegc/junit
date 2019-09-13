package uytube.ListaController;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import uytube.models.manager.Manager;
import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.Lista;
import uytube.models.Video;

public class ListaController implements ILista {

	private Manager mng;
	
	public ListaController() {
		mng = Manager.getInstance();
	}
	
	public boolean crearLista(String listaName,String categoria,String nickUser,boolean privado,boolean Default) {
		// TODO Auto-generated method stub
		
		//POR EL MOMENTO ESTAMOS CREANDO UNA LISTA DEFAULT
		
		/*
		 * Cuando una lista es default
		 * por cada canal se genera una lista nueva y el canal se agrega a la lista
		 * 
		 * lista default (nombre,privado,nulo)
		 * */
		
		//Obtener canales
		if(!this.tieneLista(nickUser, listaName)) {
			
			if(Default) {
				
				List<Canal> canales = (List<Canal>) mng.getSessionManager().createQuery("From Canal").getResultList();
				mng.closeSession();
	
				//Al ser default hay que buscar la categoria llamada "Sin Categoria"
				
				Categoria cat = (Categoria)mng.getSessionManager().createQuery("From Categoria where nombre = :nombre").setParameter("nombre", "Sin Categoria").getSingleResult();
				mng.closeSession();
				
				System.out.println(canales.size());
				for(Canal c: canales) {
					System.out.println("Lista default");
					Lista lista = new Lista(listaName,privado,cat,c,true);
					mng.startTransaction("Lista", lista);
					
				}
				
				System.out.println("Listas default creadas");
				
			}else {
				
				/*
				 * Cuando una lista es personalizada
				 * buscamos el canal 
				 * 
				 * */
				/*
				System.out.println("Canales: ");
				
				List<Canal> canales = (List<Canal>)mng.getSessionManager().createQuery("From Canal").getResultList();
				mng.closeSession();
				*/
				
				Canal canal = (Canal)mng.getSessionManager().createQuery("From Canal where nombre = :nombre").setParameter("nombre", nickUser).getSingleResult();
				mng.closeSession();
				
				Categoria cat = (Categoria)mng.getSessionManager().createQuery("From Categoria where nombre = :nombre").setParameter("nombre", categoria).getSingleResult();
				mng.closeSession();
				
				Lista lista = new Lista(listaName,privado,cat,canal,false);
				mng.startTransaction("Lista",lista);
				
				System.out.println("Lista agregada a un unico canal " + canal.getNombre());
			}
			
			return true;
		}else {
			JOptionPane.showMessageDialog(null, nickUser+" ya tiene la lista "+listaName);
			return false;

		}
		
		
	}

	public void modificarLista(int id,int id_categoria,boolean privacidad) {
		// TODO Auto-generated method stub
		
		//Obtener lista segun id
		
		
		Lista list = (Lista)mng.getSessionManager().createQuery("From Lista where id = :id").setParameter("id", id).getSingleResult();
		mng.closeSession();
		
		Categoria cat = (Categoria)mng.getSessionManager().createQuery("From Categoria where id = :id").setParameter("id", id_categoria).getSingleResult();
		mng.closeSession();
		
		System.out.println("Buscando categoria para modificar :: " + cat.getNombre());
		
		list.setCategoria(cat);
		list.setPrivado(privacidad);
		
		mng.startTransaction("Lista", list);
		
		System.out.println(" -- Lista modificada.");
	}

	public void consultarListas() {
		// TODO Auto-generated method stub

	}	

	public void agregarVideo(int idvid, int idList) {
		// TODO Auto-generated method stub
		/* Primero el admin elige un usuario U (viene del view)
		 * se listan los videos de U (viene del view)
		 * se elige el video a ingresar de U 
		 * se lista los usuarios, se elige usuario U2
		 * se elige la lista a insertar de U2
		 * se inserta video
		 */
		
		Lista listarepro = (Lista)mng.getSessionManager().createQuery("From Lista where id=:idList").setParameter("idList", idList).getSingleResult();
		mng.closeSession();
		
		Video vidd = (Video)mng.getSessionManager().createQuery("From Video where id=: idVideo").setParameter("idVideo", idvid).getSingleResult();
		mng.closeSession();
		
		listarepro.addVideo(vidd);
		
		mng.startTransaction("Lista", listarepro);
		
		System.out.println("El video: "+vidd.getNombre()+" ha sido insertado en la lista de reproduccion "+ listarepro.getNombre());
		
	}

	public void quitarVideo(int idvid, int idList) {
		// TODO Auto-generated method stub
		/*
		 * Se elige usuario U
		 * se listan las listas de U
		 * Se elige la lista y se listan los videos
		 * se elige el video y se borra de la lista
		 */
		Lista listarepro = (Lista)mng.getSessionManager().createQuery("From Lista where id=:idList").setParameter("idList", idList).getSingleResult();
		mng.closeSession();
		
		Video vidd = (Video)mng.getSessionManager().createQuery("From Video where id=: idVideo").setParameter("idVideo", idvid).getSingleResult();
		mng.closeSession();
		
		listarepro.removerVideo(idvid);
		
		mng.startTransaction("Lista", listarepro);
		
		System.out.println("El video: "+vidd.getNombre()+" ha deleteado de la lista "+ listarepro.getNombre());
		
	}


	public List<Lista> listarListas(String nickUser) {
		// TODO Auto-generated method stub
		
		List<Lista> listas = (List<Lista>)mng.getSessionManager().createQuery("From Lista where canal_nombre = :nombre").setParameter("nombre", nickUser).getResultList();
		mng.closeSession();
		
		System.out.println("Listando listas");
		for(Lista l: listas) {
			if(l.getCategoria()!=null) {
				System.out.println(l.getNombre() + " | " + l.getCanal().getNombre()+" | "+l.getId()+" | "+l.getCategoria().getNombre()+" | "+l.getCategoria().getId()+" | "+" Privacidad: "+l.getPrivado());
			}
			else {
				System.out.println(l.getNombre()+" | "+l.getCanal().getNombre()+" | "+l.getId()+" | "+l.getCategoria().getNombre()+" | "+l.getCategoria().getId());
			}
				
		}
		
		return listas;
	}
	
	public ArrayList<Lista> ListaPorCategoria(Categoria cat){
		System.out.println(cat.getId());
		ArrayList<Lista> listas = (ArrayList<Lista>)mng.getSessionManager().
		createQuery("select l From Lista as l, Categoria as c where c.id = l.categoria and c.id =:cat").
		setParameter("cat", cat.getId()).
		getResultList();
		mng.closeSession();
		return listas;
	}

	public List<String> asignarListasDefault(String name) {
		// TODO Auto-generated method stub
		
		//Obtener el nombre de todas las listas default
		//Menos ver mas tarde y favoritos
		
		List<String> nombresListas = (List<String>)mng.getSessionManager()
				.createQuery("Select l.nombre_lista from Lista as l where l.nombre_lista != 'Ver mas tarde' and l.nombre_lista != 'Favoritos' and l.tipo = true group by l.nombre_lista")
				.getResultList();
		
		for(String s:nombresListas) {
		    this.crearLista(s, "Sin Categoria", name, true, false);
		}
		
		return nombresListas;
	}

	@Override
	public boolean tieneLista(String nickUser, String nameList) {
		// TODO Auto-generated method stub
		
		try {
		Object obj= mng.getSessionManager()
				.createQuery("From Lista as L where L.canal.nombre = :nameCanal and L.nombre_lista = :nameList")
				.setParameter("nameCanal", nickUser)
				.setParameter("nameList", nameList)
				.getSingleResult();
		}catch (Exception NoResultException) {
			return false;
		}

		System.out.println("LISTA YA ASOCIADA");
		return true;
	}
	
	public Lista obtenerListaPorId(int idList) {
		Lista listarepro = (Lista)mng.getSessionManager().createQuery("From Lista where id=:idList").setParameter("idList", idList).getSingleResult();
		mng.closeSession();
		return listarepro;
	}

}
