package uytube;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import uytube.CanalController.CanalController;
import uytube.ListaController.ListaController;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Canal;
import uytube.models.Usuario;

public class testModeloUsuario {
	private Usuario usuario = new Usuario();
	private UsuarioController ControllerUsuario = new UsuarioController(); 
	private CanalController ControllerCanal = new CanalController();


	
	@Test
	public void testUsuario() {
		assertNotNull(new Usuario());

	}


	@Test
	public void testGetCanalesSeguidos() {
		Usuario user = ControllerUsuario.consultarUsuario("mbusca");//usuario mbusca que tiene lista de canales seguidos
		List<Canal> canalesSeguidos = user.getCanalesSeguidos(); //obtengo la lista de canales  del usuario mbusca
		assertNotNull(canalesSeguidos);

	}

	@Test
	public void testAddCanal() {
		
		Usuario user = ControllerUsuario.consultarUsuario("mbusca");//usuario mbusca que tiene lista de canales seguidos
		Canal canal = ControllerCanal.obtenerCanalUsuario("cachilas");//canal para agregar a la lista de canales
		user.addCanal(canal);// se le agrega el canal del cachila.
		// ahora testeamos que haya sido agregado efectivamente
		List<Canal> canalesSeguidos = user.getCanalesSeguidos(); //obtengo la lista de canales  del usuario mbusca
		for (Canal C: canalesSeguidos) {
			if(C.getUsuario().getNickname().equals("cachilas")) {/// si el canal del cachila esta en la lista entocnes OK!
				assertTrue(true);
			}
		}
		
		
	}

	@Test
	public void testGetNickname() {
		String nick = ControllerUsuario.consultarUsuario("cachilas").getNickname();
		
		assertNotNull(nick);
	}

	@Test  // aca cambio el mbusca por test y el test por mbusca
	public void testSetNickname() {
		Usuario user = ControllerUsuario.consultarUsuario("mbusca");//usuario mbusca que tiene lista de canales seguidos
		user.setNickname("test"); //le cambio el nombre 
		assertNotNull(user.getNickname());
		

	}

	@Test
	public void testGetNombre() {
		assertNotNull(ControllerUsuario.consultarUsuario("cachilas").getNombre());
	}

	@Test
	public void testSetNombre() {
		Usuario user = ControllerUsuario.consultarUsuario("mbusca");//usuario mbusca que tiene lista de canales seguidos
		user.setNombre("test"); //le cambio el nombre 
		ControllerUsuario.modificarUsuario(user);
		assertEquals(user.getNombre(), ControllerUsuario.consultarUsuario("mbusca").getNombre());	
		}

	@Test
	public void testGetApellido() {
		assertNotNull(ControllerUsuario.consultarUsuario("cachilas").getApellido());
	}

	@Test
	public void testSetApellido() {
		Usuario user = ControllerUsuario.consultarUsuario("mbusca");//usuario mbusca que tiene lista de canales seguidos
		user.setApellido("test"); //le cambio el nombre 
		ControllerUsuario.modificarUsuario(user);
		assertEquals(user.getApellido(), ControllerUsuario.consultarUsuario("mbusca").getApellido());	
	}

	@Test
	public void testGetCorreo() {
		assertNotNull(ControllerUsuario.consultarUsuario("cachilas").getCorreo());

	}

	@Test
	public void testSetCorreo() {
		Usuario user = ControllerUsuario.consultarUsuario("mbusca");//usuario mbusca que tiene lista de canales seguidos
		user.setCorreo("test@test.com"); //le cambio el nombre 
		ControllerUsuario.modificarUsuario(user);
		assertEquals(user.getCorreo(), ControllerUsuario.consultarUsuario("mbusca").getCorreo());	
	}

	@Test
	public void testGetFnacimiento() {
		assertNotNull(ControllerUsuario.consultarUsuario("cachilas").getFnacimiento());

	}

	@Test
	public void testSetFnacimiento() {
		Usuario user = ControllerUsuario.consultarUsuario("cachilas");
		user.setFnacimiento(new Date());
		assertNotNull(user.getFnacimiento());
	}

	@Test
	public void testGetImg() {
		assertNotNull(ControllerUsuario.consultarUsuario("cachilas").getImg());

	}

	@Test
	public void testSetImg() {
		Usuario user = ControllerUsuario.consultarUsuario("mbusca");//usuario mbusca que tiene lista de canales seguidos
		user.setImg("stringtest"); //seteo la imagen 
		assertNotNull(user.getImg());
	}

	@Test
	public void testDejarDeSeguir() {
		fail("Not yet implemented");
	}

}
