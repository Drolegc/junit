package uytube.ValoracionController;

import java.util.List;

import uytube.models.ValoracionVideo;
import uytube.models.Video;
import uytube.models.manager.Manager;

public class ValoracionController implements IValoracion {
	private Manager mana;
    private Object session;

	public ValoracionController() {
		mana = Manager.getInstance();
	}
	
	public void valorarVideo(ValoracionVideo vv) {
		mana.startTransaction("ValoracionVideo", vv);
		mana.closeSession();
	}

	public long valoracionActual(String vv) {
		// TODO Auto-generated method stub
		
		long valorVideo = (long)mana.getSessionManager().createQuery("select sum(valoracion) as valoracion From ValoracionVideo where video.nombre=: nombre").setParameter("nombre", vv).getSingleResult();
		mana.closeSession();
		
		return valorVideo;
	}
}
