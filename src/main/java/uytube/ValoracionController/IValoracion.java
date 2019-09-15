package uytube.ValoracionController;

import java.util.List;

import uytube.models.ValoracionVideo;
import uytube.models.Video;

public interface IValoracion {

	public void valorarVideo(ValoracionVideo vv);
	public long valoracionActual(String vv, String nick);
	public ValoracionVideo traerValoracion(String vv, String nick);
	public boolean existeValoracion(String vid, String nick);
	public List<ValoracionVideo> listaValoracionesVideo(int id);
	
}
