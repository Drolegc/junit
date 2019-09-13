package uytube.views.usuarios.consultar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

import uytube.CategoriaController.CategoriaController;
import uytube.UsuarioController.UsuarioController;
import uytube.VideoController.VideoController;
import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.usuarios.Listar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class verVideo extends JPanel {

	/**
	 * Create the panel.
	 * @param video 
	 */
	
	
	private JTextField userInfo;
	private JTextField titulo;
	private JTextField duracion;
	private JTextField url;
	private JTextField descrip;
	private Video videito;
	private String userInfoStr;
	private String catAsignar;
	private JTextField nickname;
	private JTextField nombre;
	private JTextField descripcion;
	private JTextField duracion1;
	private JDateChooser fecPub;
	private JTextField textField;
	
	public verVideo(Video video, Usuario user) {
			setLayout(null);
			JLabel lblNickname = new JLabel("usuario/nickname");
			lblNickname.setBounds(10, 11, 114, 13);
			add(lblNickname);
			
			this.userInfo = new JTextField();
			userInfo.setBounds(10, 22, 200, 19);
			add(userInfo);
			this.userInfo.setColumns(10);
			this.userInfo.setEditable(false);
			this.userInfo.setText(video.getCanal().getNombre());
			
			JLabel lblUrl = new JLabel("URL");
			lblUrl.setBounds(10, 95, 19, 13);
			add(lblUrl);
			this.url = new JTextField();
			url.setBounds(10, 109, 200, 19);
			this.url.setColumns(10);
			this.url.setEditable(false);
			this.url.setText(video.getUrl());
			add(url);
			
			JLabel lblNombre = new JLabel("Titulo");
			lblNombre.setBounds(10, 52, 28, 14);
			add(lblNombre);
			
			JLabel lblDescripcion = new JLabel("Descripcion");
			lblDescripcion.setBounds(240, 10, 56, 14);
			add(lblDescripcion);
			
			this.titulo = new JTextField();
			titulo.setEditable(false);
			titulo.setBounds(10, 65, 200, 19);
			add(titulo);
			this.titulo.setColumns(10);
			this.titulo.setText(video.getNombre());
			
			this.descripcion = new JTextField();
			this.descripcion.setEditable(false);
			descripcion.setBounds(236, 22, 205, 62);
			this.descripcion.setColumns(10);
			add(descripcion);
			this.descripcion.setText(video.getDescripcion());
			
			this.duracion = new JTextField();
			this.duracion.setEditable(false);
			duracion.setBounds(10, 151, 200, 19);
			this.duracion.setColumns(10);
			this.duracion.setText(video.getDuracion());
			add(duracion);
			
			JLabel lblDuracion = new JLabel("Duracion");
			lblDuracion.setBounds(10, 139, 43, 13);
			add(lblDuracion);
			
			JLabel lblFechaPublicacion = new JLabel("Fecha publicacion");
			lblFechaPublicacion.setBounds(240, 95, 87, 13);
			add(lblFechaPublicacion);
			
			fecPub = new JDateChooser();
			fecPub.setBounds(240, 109, 201, 19);
			fecPub.setDate(video.getFecha());
			fecPub.setEnabled(false);
			add(fecPub);
			
			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(240, 267, 87, 23);
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Consultar consultar = new Consultar(user);
					Frame.frame.setContentPane(consultar);
					Frame.frame.validate();
				}
			});
			
			
			//BOTTON DE ASIGNACION DE CATEGORIA
						CategoriaController controladorCategoria = new CategoriaController();
			ArrayList<Categoria> categorias = controladorCategoria.listarCategorias();
			
			
			String[] array1 = new String[categorias.size()];
			for(int i = 0; i < array1.length; i++) { 
				array1[i] = categorias.get(i).getNombre(); 
				
				
			};
			
			
			//add(categoriaAsig, "4, 16, fill, default");
			add(btnVolver);
			
			textField = new JTextField();
			textField.setBounds(240, 151, 201, 19);
			add(textField);
			textField.setText(video.getCategoria().getNombre());
			textField.setEditable(false);
			textField.setColumns(10);
			
			JButton btnEditarVideo = new JButton("Editar video");
			btnEditarVideo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editarVideo editar = new editarVideo(video, user);
					Frame.frame.setContentPane(editar);
					Frame.frame.revalidate();					
				}
			});
			btnEditarVideo.setBounds(337, 266, 104, 23);
			add(btnEditarVideo);
		}
}


	


