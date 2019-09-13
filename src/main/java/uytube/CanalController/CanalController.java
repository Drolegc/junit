package uytube.CanalController;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import uytube.CanalController.ICanal;
import uytube.models.Canal;
import uytube.models.Usuario;
import uytube.models.manager.Manager;
public class CanalController implements ICanal{
	
		//Variables de conexion
		private Manager mng;

		public CanalController() {
			mng = Manager.getInstance();
		}
	public Canal obtenerCanalUsuario(String nickname) {
		System.out.println(nickname);
		Canal canal = (Canal)mng.getSessionManager().createQuery("From Canal where nombre = :nickname").
		setParameter("nickname", nickname).getSingleResult();
		mng.closeSession();
		return canal;
			
	}
}
