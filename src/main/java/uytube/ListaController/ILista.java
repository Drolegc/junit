package uytube.ListaController;

import java.util.ArrayList;
import java.util.List;

import uytube.models.Lista;

public interface ILista {

	public boolean crearLista(String listaName,String categoria,String nickUser,boolean privado,boolean Default);
	public void modificarLista(int id,int id_categoria,boolean privacidad);
	public void consultarListas();
	public void agregarVideo();
	public void quitarVideo();
	public List<String> asignarListasDefault(String name);
	public boolean tieneLista(String nickUser,String nameList);
	public List<Lista> listarListas(String userName);
}
