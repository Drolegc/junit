package uytube.VideoController;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import uytube.models.Canal;
import uytube.models.Video;
import uytube.models.manager.Manager;

public class VideoController implements IVideo{
	//Variables de conexion
			private Manager mana;

			public VideoController() {
				mana = Manager.getInstance();
			}

	public void altaVideo(Video vid, String usr) {
		// TODO Auto-generated method stub
		
		Canal canal = (Canal)mana.getSessionManager().createQuery("From Canal where nombre = :nombre").setParameter("nombre", usr).getSingleResult();
		mana.closeSession();
		
		if(canal == null) {
			System.out.println("Canal nulo");
		}else {
			System.out.println(canal.getDescripcion());
		}
		
		vid.setCanal(canal);
		
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
		/*Video vidReturn = (Video)mana.getSessionManager().createQuery("From Video where titulo= :titu").setParameter("titu", titulito).getSingleResult();
		mana.closeSession();
		JOptionPane.showConfirmDialog(null, vidReturn.toString());*/
	}

	public void comentarVideo() {
		// TODO Auto-generated method stub
		
	}

	public void valorarVideo() {
		// TODO Auto-generated method stub
		
	}

}
