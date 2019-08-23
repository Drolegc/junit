package uytube.ListaController;

import java.util.List;

import javax.persistence.EntityManager;

import uytube.models.manager.Manager;
import uytube.models.Canal;
import uytube.models.Lista;

public class ListaController implements ILista {

	private Manager mng;
	
	public ListaController() {
		mng = Manager.getInstance();
	}
	
	public void crearLista(String listaName) {
		// TODO Auto-generated method stub
		
		//POR EL MOMENTO ESTAMOS CREANDO UNA LISTA DEFAULT
		
		
		
	}

	public void modificarLista() {
		// TODO Auto-generated method stub
		
	}

	public void consultarListas() {
		// TODO Auto-generated method stub
		
	}

	public void agregarVideo() {
		// TODO Auto-generated method stub
		
	}

	public void quitarVideo() {
		// TODO Auto-generated method stub
		
	}

}
