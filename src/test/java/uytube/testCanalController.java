package uytube;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import org.junit.Test;

import uytube.CanalController.CanalController;
import uytube.models.Canal;

public class testCanalController {

	private CanalController controller = new CanalController();
	@Test
	public void testCanalController() {
		assertNotNull("Constructor no nulo",new CanalController());
	}

	@Test
	public void testObtenerCanalUsuario() {
		assertNotNull(controller.obtenerCanalUsuario("juliob"));		
	}

	@Test
	public void testActualizarCanal() {
		fail("Not yet implemented");
	}

}
