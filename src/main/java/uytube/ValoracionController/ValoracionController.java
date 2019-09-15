package uytube.ValoracionController;

import java.util.ArrayList;
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
	
	/*public void valorarVideo(ValoracionVideo vv) {
		mana.startTransaction("ValoracionVideo", vv);
		mana.closeSession();
	}*/
	
	
	
	public boolean existeValoracion(int idvid, String nick) {
		boolean exists = mana.getSessionManager().createQuery("select 'hola' as Message From ValoracionVideo where exists (From ValoracionVideo as vl where vl.video.id=: nombre and vl.usuario.nickname=:nickname)").setParameter("nombre", idvid).setParameter("nickname", nick).uniqueResult() != null;
		if (exists) {
			System.out.println("Tengo una Valoracion previa.");
		} else {
			System.out.println("NO tengo una Valoracion previa.");
		}
		return exists;
	}
	
	public ValoracionVideo traerValoracion(int idvid, String nick) {
		ValoracionVideo videoValorado = (ValoracionVideo)mana.getSessionManager().createQuery("From ValoracionVideo as vl where vl.video.id=: nombre and vl.usuario.nickname=:nickname").setParameter("nombre", idvid).setParameter("nickname", nick).getSingleResult();
		mana.closeSession();
		return videoValorado;
	}

	public long valoracionActual(int idvid, String nick) {
		// TODO Auto-generated method stub
		
		long valorVideo = (long)mana.getSessionManager().createQuery("select sum(valoracion) as valoracion From ValoracionVideo as vl where vl.video.id=: nombre and vl.usuario.nickname=:nickname").setParameter("nombre", idvid).setParameter("nickname", nick).getSingleResult();
		mana.closeSession();
		
		return valorVideo;
	}
	
	public List<ValoracionVideo> listaValoracionesVideo(int id) {
		List<ValoracionVideo> listaValoraciones = (List<ValoracionVideo>)mana.getSessionManager().createQuery("From ValoracionVideo as vl where vl.video.id=:idvideo").setParameter("idvideo",id).getResultList();
		mana.closeSession();
		return listaValoraciones;
	}
}
