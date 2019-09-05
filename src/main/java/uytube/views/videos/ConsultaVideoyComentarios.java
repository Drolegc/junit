package uytube.views.videos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
import javax.swing.JTree;
import javax.swing.JCheckBox;

public class ConsultaVideoyComentarios extends JPanel {

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
	private JTextArea descripcion;
	private JTextField duracion1;
	private JTextField fechapublicacion;
	private JTextField categoria;
	
	
	public ConsultaVideoyComentarios(Video video) {
			setLayout(null);
			JLabel lblNickname = new JLabel("usuario/nickname");
			lblNickname.setBounds(10, 40, 206, 13);
			add(lblNickname);
			
			JLabel lblUrl = new JLabel("URL");
			lblUrl.setBounds(10, 125, 206, 13);
			add(lblUrl);
			
			this.userInfo = new JTextField();
			userInfo.setBounds(10, 55, 206, 20);
			add(userInfo);
			this.userInfo.setColumns(10);
			this.userInfo.setEditable(false);
			this.userInfo.setText(video.getCanal().getNombre());
			this.url = new JTextField();
			url.setEditable(false);
			url.setBounds(10, 139, 206, 20);
			this.url.setColumns(10);
			this.url.setText(video.getUrl());
			add(url);
			
			JLabel lblNombre = new JLabel("Titulo");
			lblNombre.setBounds(10, 85, 206, 14);
			add(lblNombre);
			
			JLabel lblDescripcion = new JLabel("Descripcion");
			lblDescripcion.setBounds(224, 40, 207, 14);
			add(lblDescripcion);
			
			this.titulo = new JTextField();
			titulo.setBounds(10, 100, 206, 19);
			add(titulo);
			this.titulo.setColumns(10);
			this.titulo.setText(video.getNombre());
			this.titulo.setEditable(false);
			
			this.descripcion = new JTextArea();
			descripcion.setBounds(226, 55, 205, 66);
			descripcion.setLineWrap(true);
			add(descripcion);
			this.descripcion.setText(video.getDescripcion());
			this.descripcion.setEditable(false);

			this.duracion = new JTextField();
			duracion.setBounds(10, 184, 206, 19);
			this.duracion.setColumns(10);
			this.duracion.setText(video.getDuracion());
			add(duracion);
			this.duracion.setEditable(false);
			
			JLabel lblDuracion = new JLabel("Duracion");
			lblDuracion.setBounds(10, 170, 206, 13);
			add(lblDuracion);
			
			JLabel lblFechaPublicacion = new JLabel("Fecha publicacion");
			lblFechaPublicacion.setBounds(225, 125, 206, 13);
			add(lblFechaPublicacion);
			lblFechaPublicacion.setFocusable(false);

			
			
			this.fechapublicacion = new JTextField();
			fechapublicacion.setBounds(226, 139, 206, 19);
			this.fechapublicacion.setColumns(10);
			this.fechapublicacion.setText(video.getFecha().toString());
			add(fechapublicacion);
			this.fechapublicacion.setEditable(false);
			
			
			
			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(10, 266, 206, 23);
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConsultaVideosUsuario listarVU = new ConsultaVideosUsuario(video.getCanal().getUsuario());
					Frame.frame.setContentPane(listarVU);
					Frame.frame.revalidate();
				}
			});
			
			
			//BOTTON DE ASIGNACION DE CATEGORIA
			/*
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
			categoriaAsig.setBounds(275, 228, 96, 22);
			add(categoriaAsig);
			
			
			*/
			
			JLabel lblCategoria = new JLabel("Categoria");
			lblCategoria.setBounds(224, 169, 207, 14);
			add(lblCategoria);
			
			
			this.categoria = new JTextField();
			categoria.setBounds(224, 184, 206, 19);
			this.categoria.setColumns(10);
			this.categoria.setText(video.getCategoria().getNombre());
			add(categoria);
			this.categoria.setEditable(false);
			
			
			
			
			
			
			//add(categoriaAsig, "4, 16, fill, default");
			add(btnVolver);
			
			JTree tree = new JTree();
			tree.setBounds(224, 214, 207, 75);
			add(tree);
			
			JLabel label = new JLabel("CONSULTA DE VIDEO");
			label.setBounds(160, 11, 191, 14);
			add(label);
			
		
			
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("Es Publico");
			chckbxNewCheckBox.setEnabled(false);
			chckbxNewCheckBox.setSelected(video.getEs_publico());
			chckbxNewCheckBox.setBounds(6, 214, 101, 23);
			add(chckbxNewCheckBox);
			JCheckBox chckbxNewCheckBox2 = new JCheckBox("Es Privado");
			chckbxNewCheckBox2.setEnabled(false);
			chckbxNewCheckBox2.setSelected(!(video.getEs_publico()));
			chckbxNewCheckBox2.setBounds(106, 214, 101, 23);
			add(chckbxNewCheckBox2);
			
			
			
			
		}
}


	


