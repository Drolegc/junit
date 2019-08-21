package uytube.UsuarioController;

import java.util.List;

import uytube.models.Usuario;

public interface IUsuario {
	
	public void crearUsuario(Usuario usuario);
	public void consultaUsuario(String nickname);
	public List<Usuario> listaUsuarios();
	public void seguirUsuario();
	public void dejarDeSeguir();
	public void modificarDatos();
	
}
