package uytube.ValoracionController;

import uytube.models.ValoracionVideo;

public interface IValoracion {

	public void valorarVideo(ValoracionVideo vv);
	public long valoracionActual(String vv, String nick);
	public ValoracionVideo traerValoracion(String vv, String nick);
	public boolean existeValoracion(String vid, String nick);
	
}
