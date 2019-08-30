package uytube.VideoController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.Usuario;
import uytube.models.ValoracionVideo;
import uytube.models.Video;
import uytube.models.manager.Manager;

public class VideoController implements IVideo{
	//Variables de conexion
			private Manager mana;

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
			System.out.println("Nombre: "+v.getNombre()+" Canal: "+v.getCanal()+" Categoria: "+v.getCategoria());
		}
		
	}

	public void modificarVideo() {
		// TODO Auto-generated method stub
		
	}

	public void consultaVideo(String titulito) {
		// TODO Auto-generated method stub
		List<Video> videos = (List<Video>) mana.getSessionManager().createQuery("From Video where nombre= :titu").setParameter("titu", titulito).getSingleResult();
		mana.closeSession();
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
}
