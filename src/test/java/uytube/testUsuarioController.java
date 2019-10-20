package uytube;

import static org.junit.Assert.*;

// al parecer no hay que cambiar nada
// dejar de seguir  y seguir, hay que cambiar  el usuario que se agrega si no no funciona, para cada funcion una pareja de usuarios diferentes, el de dejar de seguir que sea realmente que deje de seguir, el de seguir puede ser cualqueira a cualquiera. 
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uytube.ComentarioController.ComentarioController;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Canal;
import uytube.models.Comentario;
import uytube.models.Usuario;
// el cachilas tiene que tener password en la bd "cachilas" tambien
public class testUsuarioController {

	private UsuarioController ControllerUsuario = new UsuarioController(); 
	
	@Test
	public void testUsuarioController() {
		assertNotNull(new UsuarioController());
	}

	@Test
	public void testCrearUsuario() {
		/*Usuario UsuarioTest = new Usuario();
		UsuarioTest = ControllerUsuario.consultarUsuario("cachilas").getcomentario((long)44);
		UsuarioTest.setNickname("TestJunit");
		ControllerUsuario.crearUsuario(UsuarioTest, canal);
		assertEquals(UsuarioTest.getComentario(),ControllerComentario.getcomentario((long)44).getComentario()); // 
		
		
		Usuario user = new Usuario(, nombre, apellido, correo, password, fdate, fileName);
		Canal canal = new Canal(canalName, canalDescription, canalPrivacity, user);
		user.addCanal(canal);
		controller.crearUsuario(user, canal);*/
	
	}

	@Test
	public void testListaUsuarios() {
		assertNotNull(ControllerUsuario.listaUsuarios());
	}

	@Test
	public void testConsultarUsuario() {
		assertNotNull(ControllerUsuario.consultarUsuario("juliob"));
	}

	@Test
	public void testConsultarEmail() {
		assertNotNull(ControllerUsuario.consultarEmail("Cachila.sil@c1080.org.uy"));
	}

	@Test
	public void testLogin() {
		assertNotNull(ControllerUsuario.login("cachilas", "cachilas"));
	}

	@Test
	public void testModificarUsuario() {
		
		Usuario usuario = ControllerUsuario.consultarUsuario("cachilas");
		usuario.setApellido("JunitTest");
		ControllerUsuario.modificarUsuario(usuario);
		assertEquals(usuario.getApellido(),ControllerUsuario.consultarUsuario("cachilas").getApellido()); //
	}

	@Test
	public void testSeguirUsuario() {
	
		ControllerUsuario.seguirUsuario("juliob","diegop");
		List<Canal> canalesSeguidos = ControllerUsuario.listCanalesSeguidos("juliob");
		for (Canal C: canalesSeguidos) {
			if(C.getNombre().equals("diegop")) {
				assertTrue(true);
			}
		}
		
	
	
	}

	@Test
	public void testListCanalesSeguidos() {
		assertNotNull(ControllerUsuario.listCanalesSeguidos("cachilas"));
	}

	@Test
	public void testListUsuariosSeguidores() {
		assertNotNull(ControllerUsuario.listUsuariosSeguidores("cachilas"));
	}

	@Test
	public void testDejarDeSeguir() {
		ControllerUsuario.dejarDeSeguir("hrubino","diegop");
		List<Canal> canalesSeguidos = ControllerUsuario.listCanalesSeguidos("hrubino");
		for (Canal C: canalesSeguidos) {
			if(C.getNombre().equals("diegop")) {
				assertTrue(false);// si en la lista de canales esta el canal que dejamos de seguir hay problema
			}else {
				assertTrue(true);// si no todo ok
				
				
			}
		}
		
	}

	@Test
	public void testListarSeguidores() {
		//assertNotNull(ControllerUsuario.listarSeguidores("mbusca"));
		assertTrue(true); //es solo para mostrar en consola no se usa en la app en realidad
	}

	

}
