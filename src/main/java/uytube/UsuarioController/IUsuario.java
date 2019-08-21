package uytube.UsuarioController;

import uytube.models.Usuario;

public interface IUsuario {
	
	public void crearUsuario(Usuario usuario);
	public void consultaUsuario(String nickname);
	public void listaUsuarios();
	public void seguirUsuario();
	public void dejarDeSeguir();
	public void modificarDatos();
	
}
