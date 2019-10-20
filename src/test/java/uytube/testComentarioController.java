package uytube;
//comentario 32 y video "Thriller" en la prueba de respuestas, se usa el comentario id 36 tel video inauguracion estadio peñarol de tony pacheco y se ingreso una respuesta quedo con id 44
import static org.junit.Assert.*;

import org.junit.Test;

import uytube.ComentarioController.ComentarioController;
import uytube.ValoracionController.ValoracionController;
import uytube.models.Comentario;
import uytube.models.Lista;
import uytube.models.ValoracionVideo;
import uytube.models.Video;

public class testComentarioController {

	private ComentarioController ControllerComentario = new ComentarioController(); 
	
	
	@Test
	public void testComentarioController() {
		assertNotNull(new ComentarioController());
	}

	@Test
	public void testGetcomentario() {
		assertNotNull(ControllerComentario.getcomentario(32));
	}

	@Test
	public void testListarRespuestas() {
		assertNotNull(ControllerComentario.ListarRespuestas((long)36));
	}

	@Test
	public void testListarComentarios() {
		assertNotNull(ControllerComentario.listarComentarios("Thriller"));
	}

	@Test
	public void testAgregarRespuesta() {
		Comentario RespuestaTest = new Comentario();
		RespuestaTest = ControllerComentario.getcomentario((long)44);
		RespuestaTest.setComentario("prueba junit");
		ControllerComentario.AgregarComentario(RespuestaTest);
		assertEquals(RespuestaTest.getComentario(),ControllerComentario.getcomentario((long)44).getComentario()); // 
	}

	@Test
	public void testExisteComentarioID() {
		assertTrue(ControllerComentario.ExisteComentarioID(32));
	}

	@Test
	public void testAgregarComentario() {
		Comentario ComentarioTest = new Comentario();
		ComentarioTest = ControllerComentario.getcomentario(32);
		ComentarioTest.setComentario("prueba junit");
		ControllerComentario.AgregarComentario(ComentarioTest);
		
		assertEquals(ComentarioTest.getComentario(),ControllerComentario.getcomentario(32).getComentario()); // 

	}

}
