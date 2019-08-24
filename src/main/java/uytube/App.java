package uytube;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Transaction;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;
import java.awt.event.ActionEvent;

import uytube.CategoriaController.CategoriaController;
import uytube.CategoriaController.ICategoria;
import uytube.ListaController.ILista;
import uytube.ListaController.ListaController;
import uytube.UsuarioController.IUsuario;
import uytube.UsuarioController.UsuarioController;
import uytube.models.HibernateUtil;
import uytube.models.Usuario;
import uytube.views.Frame;
import uytube.views.Inicio;

import javax.swing.JTextField;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import uytube.views.usuarios.Alta;
import org.hibernate.Session;
public class App extends JFrame{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		IUsuario controllerUser = new UsuarioController();
		controllerUser.crearUsuario(new Usuario("user2", "user2", "user", "user",new Date(),"path"));
		controllerUser.crearUsuario(new Usuario("userSecond", "userSecond", "user", "user",new Date(),"path"));
		controllerUser.crearUsuario(new Usuario("lorenzo", "lorenzo", "user", "user",new Date(),"path"));
		
		controllerUser.seguirUsuario("user2","userSecond");
		controllerUser.seguirUsuario("user2","lorenzo");
		
		
		ICategoria controllerCategoria = new CategoriaController();
		controllerCategoria.altaCategoria("Zombies");
		controllerCategoria.altaCategoria("Zombies");
		
		ILista controllerLista = new ListaController();
		
		//Lista default
		controllerLista.crearLista("Ver mas tardes", null, null, true, true);
		controllerLista.crearLista("listaPer", "Zombies", "user2", true, false);
		
		System.out.println("Fin");
	}

	/**
	 * Create the frame.
	 */
	public App() {
		Frame.frame = this;		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		Inicio inicio = new Inicio();
		setResizable(false);
		setVisible(true);
		add(inicio);
	}
	public void goToinit() {
		
	}

}
