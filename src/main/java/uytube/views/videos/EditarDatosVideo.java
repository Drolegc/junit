package uytube.views.videos;

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

public class EditarDatosVideo extends JPanel {

	/**
	 * Create the panel.
	 * @param video 
	 */
	
	
	private JTextField userInfo;
	private JTextField titulo;
	private JTextField duracion;
	private JTextField url;
	private JTextField descrip;
	private VideoMain main;
	private Video videito;
	private String userInfoStr;
	private String catAsignar;
	private JTextField nickname;
	private JTextField nombre;
	private JTextField descripcion;
	private JTextField duracion1;
	private JDateChooser fecPub;
	
	public EditarDatosVideo(Video video) {
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
			descripcion.setBounds(236, 22, 205, 62);
			this.descripcion.setColumns(10);
			add(descripcion);
			this.descripcion.setText(video.getDescripcion());
			
			this.duracion = new JTextField();
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
			add(fecPub);
			
			JButton btnCancelar = new JButton("cancelar");
			btnCancelar.setBounds(10, 261, 200, 23);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VideoMain videos = new VideoMain();
					Frame.frame.setContentPane(videos);
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
			catAsignar = "Sin Categoria"; // SI NO TOCA EL BOTON, SIMPLEMENTE LO CARGA COMO SIN CATEGORIAS
			
			JComboBox categoriaAsig = new JComboBox(array1);		
			categoriaAsig.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox comboBox12 = (JComboBox)e.getSource();
					catAsignar = (String)comboBox12.getSelectedItem();
			        System.out.println(catAsignar);
				}
			});
			categoriaAsig.setSelectedIndex(video.getCategoria().getId()-1);
			categoriaAsig.setBounds(275, 228, 96, 22);
			add(categoriaAsig);
			
			
			
			
			JLabel lblCategoria = new JLabel("Categoria");
			lblCategoria.setBounds(240, 138, 180, 14);
			add(lblCategoria);
			
			
			
			
			
			//add(categoriaAsig, "4, 16, fill, default");
			add(btnCancelar);
			
			JButton btnAgregar = new JButton("Editar");
			btnAgregar.setBounds(240, 261, 201, 23);
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UsuarioController usercontroller = new UsuarioController();		
					Usuario usuario = usercontroller.consultarUsuario(userInfo.getText());
					Video v = new Video();
					
					v.setCanal(video.getCanal());
					v.setNombre(titulo.getText());
					v.setUrl(url.getText());
					v.setDescripcion(descripcion.getText());
					v.setDuracion(duracion.getText());
					v.setFecha(fecPub.getDate());
					v.setCategoria(video.getCategoria());
					VideoController ControlerV = new VideoController();
					ControlerV.modificarVideo(v);
					
					
					ListarVideosUsuario Lvu = new ListarVideosUsuario(usuario);
					JOptionPane.showMessageDialog(Frame.frame, "Video Editado");
					Frame.frame.setContentPane(Lvu);
					Frame.frame.revalidate();
				}
			});
			add(btnAgregar);
		}
}


	


