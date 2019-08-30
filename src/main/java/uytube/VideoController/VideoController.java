package uytube.VideoController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.HibernateUtil;
import uytube.models.Usuario;
import uytube.models.ValoracionVideo;
import uytube.models.Video;
import uytube.models.manager.Manager;

public class VideoController implements IVideo{
	//Variables de conexion
			private Manager mana;
	        private Object session;

			public VideoController() {
				mana = Manager.getInstance();
			}

	public void altaVideo(Video vid, String usr, String cate) {
		// TODO Auto-generated method stub
		
		Canal canal = (Canal)mana.getSessionManager().createQuery("From Canal where nombre = :nombre").setParameter("nombre", usr).getSingleResult();
		mana.closeSession();
		vid.setCanal(canal);
		
		Categoria cat = (Categoria)mana.getSessionManager().createQuery("From Categoria where nombre = :nombre").setParameter("nombre", cate).getSingleResult();
		mana.closeSession();
		vid.setCategoria(cat);
		
		// PARA LUEGO,PASAR CANAL Y CATEGORIA AL VIEWS
		
		System.out.println(cat.getNombre());
		
		if(canal == null) {
			System.out.println("Canal nulo");
		}else {
			System.out.println(canal.getDescripcion());
		}
		
		mana.startTransaction("Video", vid);
		
		
		List<Video> videos = (List<Video>) mana.getSessionManager().createQuery("From Video where nombre = :nombre").setParameter("nombre", vid.getNombre()).getResultList();
		mana.closeSession();
		
		for(Video v: videos) {
			//System.out.println("Nombre: "+v.getNombre()+" Canal: "+v.getCanal()+" Categoria: "+v.getCategoria());
			System.out.println(v.toString());
			
		}
		
	}

	public void modificarVideo() {
		// TODO Auto-generated method stub
		
	}

	public Video consultaVideo(String titulito) {
		// TODO Auto-generated method stub
		List<Video> videos = (List<Video>) mana.getSessionManager().createQuery("From Video where nombre= :titu").setParameter("titu", titulito).getSingleResult();
		mana.closeSession();
		return v;
	}

	public void comentarVideo() {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Video> obtenerVideosUsuario(String usuario) {
		ArrayList<Video> v = (ArrayList<Video>)mana.getSessionManager().
							createQuery("from Video where canal_nombre = :canal").setParameter("canal", usuario).getResultList();
		return v;
	}
	public void valorarVideo(ValoracionVideo vv) {
		// TODO Auto-generated method stub
		
		mana.startTransaction("ValoracionVideo", vv);
		
	}
	public ArrayList<Video> listaVideosUsuario(String nombre) {
		// TODO Auto-generated method stub
		ArrayList<Video> Videos = (ArrayList<Video>)mana.getSessionManager().createQuery("from Video where canal.nombre = :nombre").setParameter("nombre", nombre).getResultList();
		mana.closeSession();
		return Videos;	}
	public void editarVideo(Video video) {
		// TODO Auto-generated method stub
		
	      	
		
	}
	
	public ArrayList<Video> listaVideos(){
		
		ArrayList<Video> videos = (ArrayList<Video>)mana.getSessionManager().createQuery("From Video").getResultList();
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
