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
	private Manager mng;
	
	public UsuarioController() {
		mng = Manager.getInstance();
	}
	
	public void crearUsuario(Usuario usuario) {
		try {
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
		EntityManager em = this.mng.getEntityManager();
		Usuario user1 = (Usuario)em
		.createQuery("From Usuario Where nickname = :nick")
		.setParameter("nick", name1).getSingleResult();
		
		Usuario user2 = (Usuario)em
		.createQuery("From Usuario Where nickname = :nick")
		.setParameter("nick", name2).getSingleResult();
		
		user1.addUsuario(user2);
		
		this.mng.startTransaccion(user1);
		
	}
	
	public void listUsuariosSeguidos(String name) {
		
		Usuario user = this.getUser(name);
		
		for(Usuario u:user.getusuariosSeguidos()) {
			System.out.println(u.getNickname());
		}
	}

	private Usuario getUser(String nick) {
		EntityManager em = this.mng.getEntityManager();
		Usuario user = (Usuario)em
		.createQuery("From Usuario Where nickname = :nick")
		.setParameter("nick", nick).getSingleResult();
		
		return user;
	}
	public void dejarDeSeguir(String nick1,String nick2) {
		// TODO Auto-generated method stub
		
		Usuario user = this.getUser(nick1);
		Usuario user2 = this.getUser(nick2);
		
		user.getusuariosSeguidos().remove(user2);
		
		this.mng.startTransaccion(user);
		
	}

	public void modificarDatos() {
		// TODO Auto-generated method stub
		
	}

}
