package uytube;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Manager.Manager;
import uytube.CategoriaController.CategoriaController;
import uytube.CategoriaController.ICategoria;
import uytube.UsuarioController.IUsuario;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;

public class app {

	public static void main(String[] args) {
		
		/*List<Usuario> usuarios = (List<Usuario>) manager.createQuery("From Usuario").getResultList();*/
		
		Manager mdb = Manager.getInstance();
		
		ICategoria controllerCat = new CategoriaController();
		IUsuario controllerUser = new UsuarioController();
		
		controllerCat.altaCategoria("Pistando como un campeon");
		controllerCat.listarCategoriasExistentes();
		
//		controllerUser.crearUsuario(new Usuario("sa","leandro","gonz","d@gg",new GregorianCalendar(1997,2,27).getTime(),"drole.img"));
//		controllerUser.seguirUsuario("drol33", "sa");
		controllerUser.listUsuariosSeguidos("drol33");
		controllerUser.dejarDeSeguir("drol33", "sa");
		controllerUser.listUsuariosSeguidos("drol33");
		System.out.println("Fin");
	}

}
