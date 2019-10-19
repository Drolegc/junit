package uytube;

import static org.junit.Assert.*;

import org.junit.Test;

import uytube.CategoriaController.CategoriaController;
import uytube.VideoController.VideoController;
import uytube.models.Video;

public class testVideoController {

	private VideoController controllerVideo = new VideoController();
	
	// EN TODO EL TESTEO VOY A USAR LA VALORACION DEL VIDEO_ID=11, nombre_video=Show de goles Y NICKNAME=juliob; sabiendo que existe
		// como se modifica el show de goles, paso a tomar en algunas pruebas de obtencion nombre_video="Etapa A contramano Liguilla"

	@Test
	public void testVideoController() {
		assertNotNull(new VideoController());
	}

	@Test
	public void testAltaVideo() {
		Video vid = controllerVideo.consultaVideoPorID(11);
		String vidNombre = vid.getNombre();
		vid.setNombre(vid.getNombre()+"TEST");
		controllerVideo.altaVideo(vid, "juliob", vid.getCategoria().getNombre());
		assertNotEquals(vidNombre,controllerVideo.consultaVideoPorID(11).getNombre());	
	}

	@Test
	public void testModificarVideo() {
		Video vid = controllerVideo.consultaVideoPorID(11);
		String vidNombre = vid.getNombre();
		vid.setNombre(vid.getNombre()+"TEST");
		controllerVideo.modificarVideo(vid);
		assertNotEquals(vidNombre,controllerVideo.consultaVideoPorID(11).getNombre());	
	}

	@Test
	public void testConsultaVideo() {
		assertNotNull(controllerVideo.consultaVideo("Etapa A contramano Liguilla", "cachilas"));
	}

	@Test
	public void testObtenerVideosUsuario() {
		assertNotNull(controllerVideo.obtenerVideosUsuario("cachilas"));
	}

	@Test
	public void testListaVideosUsuario() {
		assertNotNull(controllerVideo.listaVideosUsuario("cachilas"));
	}

	@Test
	public void testConsultaVideoPorID() {
		assertNotNull(controllerVideo.consultaVideoPorID(11));
	}

	@Test
	public void testListaVideos() {
		assertNotNull(controllerVideo.listaVideos());
	}

	@Test
	public void testVideoPorCategoria() {
		CategoriaController controllerCategoria = new CategoriaController();
		assertNotNull(controllerVideo.videoPorCategoria(controllerCategoria.obtenerCategoria(controllerVideo.consultaVideoPorID(11).getCategoria().getNombre())));
	}

}
