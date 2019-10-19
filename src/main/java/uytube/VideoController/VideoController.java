package uytube.VideoController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;

import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.HibernateUtil;
import uytube.models.Usuario;
import uytube.models.ValoracionVideo;
import uytube.models.Video;
import uytube.models.manager.Manager;

public class VideoController implements IVideo{
	//Variables de conexion
private Manager mng;
	
	private Session session2;
	private Transaction transaction2;
	private static EntityManager manager;
	private static EntityManagerFactory emf;
			private Manager mana;
	        private Object session;
			private Object transaction;
        
			public VideoController() {
				mana = Manager.getInstance();
			}

	public void altaVideo(Video vid, String usr, String cate) {
		// TODO Auto-generated method stub
		
		// tengo que buscar en canal.usuario.nombre
		Canal canal = (Canal)mana.getSessionManager().createQuery("From Canal where nombre.usuario.nickname = :nombre").setParameter("nombre", usr).getSingleResult();
		mana.closeSession();
		vid.setCanal(canal);
		
		Categoria cat = (Categoria)mana.getSessionManager().createQuery("From Categoria where nombre = :nombre").setParameter("nombre", cate).getSingleResult();
		mana.closeSession();
		vid.setCategoria(cat);
		
		// PARA LUEGO,PASAR CANAL Y CATEGORIA AL VIEWS
		
		System.out.println(cat.getNombre());

		mana.startTransaction("Video", vid);
		
		
		List<Video> videos = (List<Video>) mana.getSessionManager().createQuery("From Video where nombre = :nombre").setParameter("nombre", vid.getNombre()).getResultList();
		mana.closeSession();
		
		for(Video v: videos) {
			//System.out.println("Nombre: "+v.getNombre()+" Canal: "+v.getCanal()+" Categoria: "+v.getCategoria());
			System.out.println(v.toString());
			
		}
		
	}

	public void modificarVideo(Video V) {
		// TODO Auto-generated method stub
		this.session2 = null;
		this.transaction2 = null;
		System.out.println(this.session2);
		try {
	        session2 = HibernateUtil.getSessionFactory().openSession();
			transaction2 = session2.beginTransaction();
	        if(!transaction2.isActive())
	        	transaction2.begin();
	        session2.saveOrUpdate("Video", V);
	        transaction2.commit();
	      } catch (Exception e) {
	        if (transaction2 != null) {
	          transaction2.rollback();
	        }
	        e.printStackTrace();
	        
	      } finally {
	        if (session2 != null) {
	          session2.close();
	        }
	      }		
	}

	public Video consultaVideo(String titulito, String user) {
		// TODO Auto-generated method stub .. para volver a mirar
		Video v = (Video)mana.getSessionManager().createQuery("select v From Video as v, Canal as c where v.nombre=:titu and c.usuario.nickname=:nombre").setParameter("titu", titulito).setParameter("nombre",user).getSingleResult();
		mana.closeSession();
		System.out.println("---------Nombre video: "+v.getNombre()+" Canal: "+v.getCanal().getNombre());
		return v;
	} 
	
	public ArrayList<Video> obtenerVideosUsuario(String usuario) {
		ArrayList<Video> v = (ArrayList<Video>)mana.getSessionManager().
							createQuery("from Video where canal.usuario.nickname = :canal").setParameter("canal", usuario).getResultList();
		mana.closeSession();
		return v;
	}
	public ArrayList<Video> listaVideosUsuario(String nombre) {
		// TODO Auto-generated method stub
		ArrayList<Video> Videos = (ArrayList<Video>)mana.getSessionManager().createQuery("from Video where canal.usuario.nickname = :nombre").setParameter("nombre", nombre).getResultList();
		mana.closeSession();
		return Videos;	
	}
		
	public Video consultaVideoPorID(int id) {
		
		Video v = (Video)mana.getSessionManager().createQuery("select v From Video as v where v.id=: id").setParameter("id", id).getSingleResult();
		mana.closeSession();
		return v;
	}
	
	public ArrayList<Video> listaVideos(){
		ArrayList<Video> videos = (ArrayList<Video>)mana.getSessionManager().createSQLQuery("select * from Video").getResultList();
		mana.closeSession();
		return videos;
	}
	
	public ArrayList<Video> videoPorCategoria(Categoria cat){
		System.out.println(cat.getId());
		ArrayList<Video> videos = (ArrayList<Video>)mana.getSessionManager().
		createQuery("select v From Video as v, Categoria as c where c.id = v.categoria and c.id =:cat").
		setParameter("cat", cat.getId()).
		getResultList();
		System.out.println(videos);
		mana.closeSession();
		return videos;
	}
		
}
