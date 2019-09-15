package uytube.CanalController;

import java.util.ArrayList;
import java.util.List;

import uytube.models.Categoria;
import uytube.models.Video;
import uytube.models.Canal;
public interface ICanal {
	
	public Canal obtenerCanalUsuario(String nombre);
	public void actualizarCanal(Canal canal);
}
