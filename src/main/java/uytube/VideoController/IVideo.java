package uytube.VideoController;

import java.util.ArrayList;
import java.util.Date;

import uytube.models.ValoracionVideo;
import uytube.models.Categoria;
import uytube.models.Video;

public interface IVideo {
	public void altaVideo(Video vid, String usr, String cate);
	public void modificarVideo(Video v);
	public Video consultaVideo(String titulito, String user);
	public void comentarVideo();

	public ArrayList<Video> listaVideos();
	public ArrayList<Video> videoPorCategoria(Categoria cat);
}

