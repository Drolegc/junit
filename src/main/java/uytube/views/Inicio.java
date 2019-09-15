package uytube.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import uytube.views.categorias.main;
import uytube.views.listas.inicio;
import uytube.views.usuarios.UserMain;
import uytube.views.videos.VideoMain;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class Inicio extends JPanel {

	/**
	 * Create the panel.
	 */
	JFrame frame;
	public Inicio() {
		setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setText("Utilize los menus para ingresar a las diferentes funciones de el administrador de la plataforma.\r\n\r\n- Usuarios - Aqui podra dar de alta, consultar(permite modificar el usuario), seguir usuario, dejar de seguir usuario y listar usuarios de la plataforma.\r\n\r\n-Videos - Esta categoria concierne en cuanto a los datos de los videos, agregar, modificar valorar, consultar y comentar los videos de la plataforma.\r\n\r\n- Categorias - En la seccion de categorias, el administrador  puede agregar, consultar y listar las mismas. \r\n\r\n- Listas - Por ultimo, en el menu de listas se puede crear, consultar y listar las listas de los usuarios de Uytube. Estas pueden ser las listas default o particulares.\r\n\r\n#  |'\u00AF\u00AF|\u00AF\u00AF'|\u00B0\\\u00AF\u00AF\u00AF|\u00AF\u00AF\u00AF/||\u00AF\u00AF\u00AF\u00AF\u00AF|\u00B0|'\u00AF\u00AF|\u00AF\u00AF'|\u00B0|\u00AF\u00AF\u00AF|      /\u00AFx\u00AF\u00AF\\ \r\n#  |        |  \\       /  |       | |      | |    \u00AF\u00AF\\'|   (\\__/|\r\n#   \\____/ '   |____|'   \u00AF|__|\u00AF   \\____/ '|__x__/\u00B0 \\____\\   ");
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		textArea.setBounds(10, 100, 782, 313);
		add(textArea);
		
		JLabel label = new JLabel("UYTUBE ADMINISTRADOR");
		label.setBounds(10, 51, 165, 14);
		add(label);
	/*	
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();
				
			}
		});
		add(btnUsuarios, "4, 6");
		
		JButton btnVideos = new JButton("Videos");
		btnVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VideoMain videos = new VideoMain();
				Frame.frame.setContentPane(videos);
				Frame.frame.validate();
			}
		});
		add(btnVideos, "4, 8");
		
		JButton btnCategorias = new JButton("Categorias");
		btnCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main categorias = new main();
				Frame.frame.setContentPane(categorias);
				Frame.frame.validate();				
			}
		});
		add(btnCategorias, "4, 10");
		
		JButton btnListas = new JButton("Listas");
		btnListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				inicio init = new inicio();
				Frame.frame.setContentPane(init);
				Frame.frame.validate();
				
			}
		});
		add(btnListas, "4, 12");
		
		
		
		
		
		
		*/
	}
}
