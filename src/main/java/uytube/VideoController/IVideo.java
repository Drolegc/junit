package uytube.VideoController;

import java.util.Date;

import uytube.models.ValoracionVideo;
import uytube.models.Video;

public interface IVideo {
	public void altaVideo(Video vid, String usr, String cate);
	public void modificarVideo();
	public void consultaVideo(String titulito);
	public void comentarVideo();
	public void valorarVideo(ValoracionVideo vv);

}

