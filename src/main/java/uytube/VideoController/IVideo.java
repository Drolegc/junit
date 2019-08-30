package uytube.VideoController;

import java.util.ArrayList;
import java.util.Date;

import uytube.models.Categoria;
import uytube.models.Video;

public interface IVideo {
	public void altaVideo(Video vid, String usr);
	public void modificarVideo();
	public void consultaVideo(String titulito);
	public void comentarVideo();
	public void valorarVideo();
	public ArrayList<Video> listaVideos();
	public ArrayList<Video> videoPorCategoria(Categoria cat);
}

