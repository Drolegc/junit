package uytube;

import static org.junit.Assert.*;

import org.junit.Test;

import uytube.UsuarioController.UsuarioController;
import uytube.ValoracionController.ValoracionController;
import uytube.models.Usuario;
import uytube.models.ValoracionVideo;
import uytube.models.Video;

public class testValoracionVideoController {
	
	private ValoracionController controllerValoracion = new ValoracionController();
	
	// EN TODO EL TESTEO VOY A USAR LA VALORACION DEL VIDEO_ID=7 Y NICKNAME=sergiop;

	@Test
	public void testValoracionController() {
		assertNotNull(new ValoracionController());
	}

	@Test
	public void testValorarVideo() {

		ValoracionVideo valoracionTest = new ValoracionVideo();
		valoracionTest = controllerValoracion.traerValoracion(7, "sergiop");
		valoracionTest.setValoracion(valoracionTest.getValoracion()*(-1)); // le pongo de prepo -1 (dislike) //Leandro: Cambiado por tomar el contrario al que tenia
		controllerValoracion.valorarVideo(valoracionTest); //valoro el video, mando a bd

		assertEquals(valoracionTest.getValoracion(),controllerValoracion.traerValoracion(7, "sergiop").getValoracion()); // si se subi� bien, esto deberia ser TRUE=EQUALS
	}

	@Test
	public void testExisteValoracion() {
		assertTrue(controllerValoracion.existeValoracion(7, "sergiop"));
	}

	@Test
	public void testTraerValoracion() {
		assertNotNull(controllerValoracion.traerValoracion(7, "sergiop"));
	}

	@Test
	public void testValoracionActual() {
		assertEquals(1,controllerValoracion.valoracionActual(8));
	}

	@Test
	public void testListaValoracionesVideo() {
		assertNotNull(controllerValoracion.listaValoracionesVideo(8));
	}

}
