package uytube.ListaController;

import java.util.List;

import uytube.models.Lista;

public interface ILista {

	public void crearLista(String listaName,String categoria,String nombreUser,boolean privado,boolean Default);
	public void modificarLista();
	public void consultarListas();
	public void agregarVideo();
	public void quitarVideo();
	public List<Lista> listarListas(String userName);

}
