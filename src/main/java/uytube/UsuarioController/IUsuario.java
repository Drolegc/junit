package uytube.UsuarioController;

import uytube.models.Usuario;

public interface IUsuario {
	
	public void crearUsuario(Usuario usuario);
	public void consultaUsuario(String nickname);
	public void listaUsuarios();
	public void seguirUsuario(String name1,String name2);
	public void dejarDeSeguir(String name1,String name2);
	public void modificarDatos();
	public void listUsuariosSeguidos(String name);
}
