package uytube.ValoracionController;

import java.util.List;

import uytube.models.ValoracionVideo;
import uytube.models.Video;

public interface IValoracion {

	public void valorarVideo(ValoracionVideo vv);
	public long valoracionActual(int idvid, String nick);
	public ValoracionVideo traerValoracion(int idvid, String nick);
	public boolean existeValoracion(int idvid, String nick);
	public List<ValoracionVideo> listaValoracionesVideo(int id);
	
}
