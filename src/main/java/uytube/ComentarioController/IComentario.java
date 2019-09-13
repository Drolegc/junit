package uytube.ComentarioController;

import java.util.List;

import uytube.models.Comentario;
import uytube.models.Video;
import uytube.models.Comentario;

public interface IComentario {

	public void AgregarRespuesta(Long idComentario, Comentario respuesta);
	public boolean ExisteComentarioID(long IdComentario);
	public List<Comentario> listarComentarios(String nombreVideo);
	public void AgregarComentario(Comentario c);
	
	
}
