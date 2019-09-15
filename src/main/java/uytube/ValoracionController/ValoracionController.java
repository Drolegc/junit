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
	
	public ValoracionVideo traerValoracion(String vv, String nick) {
		ValoracionVideo videoValorado = (ValoracionVideo)mana.getSessionManager().createQuery("From ValoracionVideo as vl where vl.video.nombre=: nombre and vl.video.canal.nombre=:nickname").setParameter("nombre", vv).setParameter("nickname", nick).getSingleResult();
		mana.closeSession();
		return videoValorado;
	}
	
	public boolean existeValoracion(String vid, String nick) {
		boolean exists = mana.getSessionManager().createQuery("select id from ValoracionVideo where exists (From ValoracionVideo as vl where vl.video.nombre=: nombre and vl.video.canal.nombre=:nickname)").setParameter("nombre", vid).setParameter("nickname", nick).uniqueResult() != null;
		return exists;
	}

	public long valoracionActual(String vv, String nick) {
		// TODO Auto-generated method stub
		
		long valorVideo = (long)mana.getSessionManager().createQuery("select sum(valoracion) as valoracion From ValoracionVideo as vl where vl.video.nombre=: nombre and vl.usuario.nickname=:nickname").setParameter("nombre", vv).setParameter("nickname", nick).getSingleResult();
		mana.closeSession();
		
		return valorVideo;
	}
}
