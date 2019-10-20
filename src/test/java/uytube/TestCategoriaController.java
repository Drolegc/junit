package uytube;

import static org.junit.Assert.*;

import org.junit.Test;

import uytube.CategoriaController.CategoriaController;
import uytube.ListaController.ListaController;
import uytube.models.Categoria;
import uytube.models.Lista;

public class TestCategoriaController {
	
	private CategoriaController controller = new CategoriaController();
	
	@Test
	public void testCategoriaController() {
		assertNotNull(new CategoriaController());		
	}

	@Test
	public void testObtenerCategoria() {
		assertNotNull(controller.obtenerCategoria("Musica"));		
	}

	@Test
	public void testAltaCategoria() {
		/*hay que poner un nuevo dato para que no genere error*/
		assertTrue(controller.altaCategoria("Otro"));		
	}

	
	@Test
	public void testListarCategoriasName() {
		assertNotNull(controller.listarCategoriasName());
	}

	@Test
	public void testListarCategorias() {
		assertNotNull(controller.listarCategorias());
	}

	@Test
	public void testModificarCategoria() {
		//hay que insertar nuevos nombres en cada prueba nueva que se haga.
		//sino queda en rojo.
		Categoria categoria = controller.obtenerCategoria("Noticias");
	    categoria.setNombre("Testol");
	    controller.modificarCategoria(categoria);		
		assertEquals(categoria.getNombre(),controller.obtenerCategoria("Testol").getNombre());		
		
	}

}
