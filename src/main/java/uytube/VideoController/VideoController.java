package uytube.VideoController;

import java.util.Date;

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
		
		vid.setCanal(canal);
		
		mana.startTransaction("Video", vid);
		
		
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
