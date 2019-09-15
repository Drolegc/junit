package uytube.UsuarioController;

import java.util.ArrayList;
import java.util.List;

import uytube.models.Canal;
import uytube.models.Usuario;
import uytube.models.Video;

public interface IUsuario {
	
	public void crearUsuario(Usuario usuario, Canal canal);
	public Usuario consultarUsuario(String nickname);
	public List<Usuario> listaUsuarios();
	public List<Canal> listCanalesSeguidos(String nick);
	public List<Usuario> listUsuariosSeguidores(String nickname);
	public void dejarDeSeguir(String name1,String name2);
	public void seguirUsuario(String name1,String name2);
}
