package uytube.UsuarioController;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import Manager.Manager;
import uytube.models.Usuario;

public class UsuarioController implements IUsuario{
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public UsuarioController() {
	}
	
	public void crearUsuario(Usuario usuario) {
		try {
			Manager mng = Manager.getInstance();
			mng.startTransaccion(usuario);
			JOptionPane.showMessageDialog(null, "Usuario creado");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El usuario ya existe");
		}
	}

	public void consultaUsuario(String nickname) {
		// TODO Auto-generated method stub
		
	}

	public void listaUsuarios() {
		// TODO Auto-generated method stub
		
	}

	public void seguirUsuario(String name1,String name2) {
		// TODO Auto-generated method stub
		
	}

	public void dejarDeSeguir() {
		// TODO Auto-generated method stub
		
	}

	public void modificarDatos() {
		// TODO Auto-generated method stub
		
	}

}
