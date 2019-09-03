package uytube.ValoracionController;

import uytube.models.ValoracionVideo;

public interface IValoracion {

	public void valorarVideo(ValoracionVideo vv);
	public long valoracionActual(String vv);
	
}
