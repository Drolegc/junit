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
import uytube.VideoController.IVideo;
import uytube.VideoController.VideoController;
import uytube.models.HibernateUtil;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.Inicio;
import uytube.views.categorias.alta;
import uytube.views.categorias.consulta;
import uytube.views.categorias.listar;

import javax.swing.JTextField;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import uytube.views.usuarios.Alta;
import uytube.views.usuarios.Buscar;
import uytube.views.usuarios.DejarSeguir;
import uytube.views.usuarios.Listar;
import uytube.views.usuarios.Seguir;
import uytube.views.usuarios.consultar.ConsultarMain;
import uytube.views.videos.AltaVideo;
import uytube.views.videos.ConsultaVideo;
import uytube.views.videos.ModificarDatosVideo;
import uytube.views.videos.ValorarVideo;

import org.hibernate.Session;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class App extends JFrame{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);	
					frame.setTitle("Uytube");
					//frame.setSize(900, 400);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		IUsuario controllerUser = new UsuarioController();
		controllerUser.crearUsuario(new Usuario("user2", "Silvio", "Sanchez", "silvio_69@hotmail.com",new Date(),"path"));
		controllerUser.crearUsuario(new Usuario("juancho", "Juan", "Garcia", "juanchi2@email.com",new Date(),"path"));
		controllerUser.crearUsuario(new Usuario("lolo", "Lorenzo", "user", "lolo2@email.com",new Date(),"path"));
		controllerUser.crearUsuario(new Usuario("youtuber", "Marcelo", "Castro", "marcelo@email.com",new Date(),"path"));
		
		/*
		controllerUser.seguirUsuario("user2","juancho");
		controllerUser.seguirUsuario("user2","lorenzo");
		controllerUser.seguirUsuario("lorenzo","user2");
		controllerUser.seguirUsuario("juancho","user2");
		
		controllerUser.dejarDeSeguir("user2", "lorenzo");
		*/
		ICategoria controllerCategoria = new CategoriaController();
		controllerCategoria.altaCategoria("Sin Categoria");
		controllerCategoria.altaCategoria("Estilo de vida");
		controllerCategoria.altaCategoria("Musica");
		controllerCategoria.altaCategoria("Ciencia");
		controllerCategoria.altaCategoria("Tecnologia");
		controllerCategoria.altaCategoria("Vlog");
		controllerCategoria.altaCategoria("Virales");
		
		
		ILista controllerLista = new ListaController();
		//Lista default
		controllerLista.crearLista("Ver mas tardes", "Sin Categoria", null, true, true);
		controllerLista.crearLista("listaPer", "Ciencia", "user2", true, false);

		
		
		//videos
		VideoController controllervideo = new VideoController();
		
		Video vid = new Video();
		vid.setNombre("Mi primer bicicleta");
		vid.setDuracion("2:20");
		vid.setDescripcion("Mi primer bicicleta era una chopera de los 80s.");
		vid.setUrl("https://www.videos.com/2e2re3w3er");
		vid.setFecha(new Date());			
		vid.setEs_publico(false);
		controllervideo.altaVideo(vid, "youtuber", "Estilo de vida");
		
		
		Video vid2 = new Video();
		vid2.setNombre("uno");
		vid2.setDuracion("2:20");
		vid2.setDescripcion("Un video que tiene una descripcion larga.. para lo que estamos acostumbrados a tener aca.");
		vid2.setUrl("https://www.videos.com/2e2re3w3er");
		vid2.setFecha(new Date());
		vid2.setEs_publico(true);
		
		controllervideo.altaVideo(vid2, "youtuber", "Musica");
		
		
		
		
		
		
		
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
		getContentPane().add(inicio);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAgregarUsuario = new JMenuItem("Agregar usuario");
		mntmAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alta alta = new Alta();
				Frame.frame.setContentPane(alta);
				Frame.frame.revalidate();
			}
		});
		mnUsuarios.add(mntmAgregarUsuario);
		
		JMenuItem mntmConsultarUsuario = new JMenuItem("Consultar usuario");
		mntmConsultarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarMain consultar = new ConsultarMain();
				Frame.frame.setContentPane(consultar);
				Frame.frame.revalidate();		
			}
		});
		mnUsuarios.add(mntmConsultarUsuario);
		
		JMenuItem mntmListarUsuarios = new JMenuItem("Listar usuarios");
		mntmListarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listar listar = new Listar();
				Frame.frame.setContentPane(listar);
				Frame.frame.revalidate();	
			}
		});
		mnUsuarios.add(mntmListarUsuarios);
		
		JMenuItem mntmSeguirUsuario = new JMenuItem("Seguir usuario");
		mntmSeguirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seguir seguir = new Seguir();
				Frame.frame.setContentPane(seguir);
				Frame.frame.revalidate();	
			}
		});
		mnUsuarios.add(mntmSeguirUsuario);
		
		JMenuItem mntmDejarDeSeguir = new JMenuItem("Dejar de seguir ");
		mntmDejarDeSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DejarSeguir dejarSeguir = new DejarSeguir();
				Frame.frame.setContentPane(dejarSeguir);
				Frame.frame.revalidate();
			}
		});
		mntmDejarDeSeguir.setHorizontalAlignment(SwingConstants.LEFT);
		mnUsuarios.add(mntmDejarDeSeguir);
		
		JMenu mnNewMenu = new JMenu("Videos");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAgregarVideo = new JMenuItem("Agregar video");
		mntmAgregarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaVideo altaV = new AltaVideo();
				Frame.frame.setContentPane(altaV);
				Frame.frame.revalidate();
			}
		});
		mnNewMenu.add(mntmAgregarVideo);
		
		JMenuItem mntmModificarVideo = new JMenuItem("Modificar video");
		mntmModificarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDatosVideo ModifVideo = new ModificarDatosVideo();
				Frame.frame.setContentPane(ModifVideo);
				Frame.frame.revalidate();
			}
		});
		mnNewMenu.add(mntmModificarVideo);
		
		JMenuItem mntmValorarVideo = new JMenuItem("Valorar video");
		mntmValorarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValorarVideo valorov = new ValorarVideo();
				Frame.frame.setContentPane(valorov);
				Frame.frame.revalidate();
			}
		});
		mnNewMenu.add(mntmValorarVideo);
		
		JMenuItem mntmVisualizar = new JMenuItem("Consulta de video ");
		mntmVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaVideo consultaV = new ConsultaVideo();
				Frame.frame.setContentPane(consultaV);
				Frame.frame.revalidate();
			}
		});
		mnNewMenu.add(mntmVisualizar);
		
		JMenuItem mntmComentarUnVideo = new JMenuItem("Comentar un video");
		mntmComentarUnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu.add(mntmComentarUnVideo);
		
		JMenu mnCategorias = new JMenu("Categorias");
		menuBar.add(mnCategorias);
		
		JMenuItem mntmAgregarCategoria = new JMenuItem("Agregar categoria");
		mntmAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alta alta = new alta();
				Frame.frame.setContentPane(alta);
				Frame.frame.revalidate();
			}
		});
		mnCategorias.add(mntmAgregarCategoria);
		
		JMenuItem mntmConsultarCategoria = new JMenuItem("Consultar categoria");
		mntmConsultarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta con = new consulta();
				Frame.frame.setContentPane(con);
				Frame.frame.revalidate();
			}
		});
		mnCategorias.add(mntmConsultarCategoria);
		
		JMenuItem mntmListarCategorias = new JMenuItem("Listar categorias");
		mntmListarCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listar lista = new listar();
				Frame.frame.setContentPane(lista);
				Frame.frame.revalidate();
			}
		});
		mnCategorias.add(mntmListarCategorias);
		
		JMenu mnListas = new JMenu("Listas");
		menuBar.add(mnListas);
		
		JMenuItem mntmCrearLista = new JMenuItem("Crear lista");
		mnListas.add(mntmCrearLista);
		
		JMenuItem mntmModificarLista = new JMenuItem("Modificar lista");
		mnListas.add(mntmModificarLista);
	}
	public void goToinit() {
		
	}

}
