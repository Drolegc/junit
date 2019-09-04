package uytube.ComentarioController;

import uytube.models.Comentario;
import uytube.models.Video;

public interface IComentario {

	public void altaComentario(Video video, String comentario,Comentario padre);
	
	
}
