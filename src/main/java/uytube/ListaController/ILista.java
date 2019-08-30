package uytube.ListaController;

import java.util.List;

import uytube.models.Lista;

public interface ILista {

	public void crearLista(String listaName,String categoria,String nickUser,boolean privado,boolean Default);
	public void modificarLista(int id,String categoria,boolean privacidad);
	public void consultarListas();
	public void agregarVideo();
	public void quitarVideo();
	public List<Lista> listarListas(String userName);

}
