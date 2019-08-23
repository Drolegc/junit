package uytube.UsuarioController;

import java.util.ArrayList;
import java.util.List;

import uytube.models.Usuario;

public interface IUsuario {
	
	public void crearUsuario(Usuario usuario);
	public ArrayList<Usuario> consultarUsuario(String nickname);
	public List<Usuario> listaUsuarios();
	
	public void listCanalesSeguidos(String name);
	public void dejarDeSeguir(String name1,String name2);
	public void seguirUsuario(String name1,String name2);
}
