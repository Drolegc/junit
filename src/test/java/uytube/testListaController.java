package uytube;

import static org.junit.Assert.*;

import org.junit.Test;

import uytube.CategoriaController.CategoriaController;
import uytube.ListaController.ListaController;
import uytube.models.Categoria;
import uytube.models.Lista;
import uytube.models.Video;

public class testListaController {
  private int idLista=17;//numero de la lista a utilizr
	private ListaController controller = new ListaController();
	
	@Test
	public void testListaController() {
		assertNotNull(new ListaController());
	}

	@Test
	public void testCrearLista() {
		assertTrue(controller.crearLista("ListaTesteo", "Sin Categoria", "juliob", true, false));
	}

	@Test
	public void testModificarLista() {
		
		Lista l = controller.obtenerListaPorId(idLista);
		boolean privacidad = l.getPrivado();
		controller.modificarLista(idLista, 13, !l.getPrivado());
		l = controller.obtenerListaPorId(idLista);
		assertNotEquals(privacidad, l.getPrivado());
	}

	@Test
	public void testAgregarVideo() {
		int id_video = 1;
		controller.agregarVideo(id_video, idLista);
		Lista l = controller.obtenerListaPorId(idLista);
		for(Video v: l.getVideos()) {
			if(v.getId()==id_video) {
				assertEquals(id_video,v.getId());
			}
		}
	}

	@Test
	public void testQuitarVideo() {
		int id_video = 1;
		controller.quitarVideo(id_video, idLista);
		Lista l = controller.obtenerListaPorId(idLista);
		for(Video v: l.getVideos()) {
			if(v.getId()==id_video) {
				assertNotEquals(id_video,v.getId());
			}
		}
		
		assertTrue(true);
	}

	@Test
	public void testListarListas() {
		assertNotNull(controller.listarListas("juliob"));
	}

	@Test
	public void testListaPorCategoria() {
		CategoriaController cat = new CategoriaController();
		Categoria c = cat.obtenerCategoria("Sin Categoria");
		assertNotNull(controller.ListaPorCategoria(c));
	}

	@Test
	public void testAsignarListasDefault() {
		fail("Not yet implemented");
	}

	@Test
	public void testTieneLista() {
		assertTrue(controller.tieneLista("kairoh", "Nostalgia"));
	}

	@Test
	public void testObtenerListaPorId() {
		assertNotNull(controller.obtenerListaPorId(idLista));
	}

}
