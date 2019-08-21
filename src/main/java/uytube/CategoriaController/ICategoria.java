package uytube.CategoriaController;

import java.util.List;

import uytube.models.Video;

public interface ICategoria {
	
	public void altaCategoria(String nombre);
	public List<Video> consultarCategoria(String nombreCategoria);
	public void listarCategoriasExistentes();
	
}
