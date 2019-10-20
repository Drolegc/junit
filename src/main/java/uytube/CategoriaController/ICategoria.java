package uytube.CategoriaController;

import java.util.ArrayList;
import java.util.List;

import uytube.models.Categoria;
import uytube.models.Video;

public interface ICategoria {
	
	public boolean altaCategoria(String nombre);
	//public List<Video> consultarCategoria(String nombreCategoria);
	//public void listarCategoriasExistentes();
	public ArrayList<Categoria> listarCategorias();
	public String[] listarCategoriasName();
	public void modificarCategoria(Categoria nuevoNombre);
	
}
