package uytube.ComentarioController;

import uytube.models.Comentario;
import uytube.models.Video;
import uytube.models.Comentario;

public interface IComentario {

	public void altaComentario(Video video, String comentario);

	void altaComentario(Video video, String comentario, Comentario padre);

	void altaComentario();
	
	
}
