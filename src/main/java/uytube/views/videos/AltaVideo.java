package uytube.views.videos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import uytube.CategoriaController.CategoriaController;
import uytube.UsuarioController.UsuarioController;
import uytube.VideoController.VideoController;
import uytube.models.Categoria;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import javax.swing.JCheckBox;

public class AltaVideo extends JPanel {
	private JTextField userInfo;
	private JTextField titulo;
	private JTextField duracion;
	private JTextField url;
	private JTextField descrip;
	private VideoMain main;
	private Video videito;
	private String nickInfoStr;
	private String catAsignar;
	
	/**
	 * Create the panel.
	 */
	
	public AltaVideo() {
		main = new VideoMain();
		setLayout(null);
		
		JLabel TITULOPANEL = new JLabel("ALTA VIDEO");
		TITULOPANEL.setBounds(178, 23, 87, 14);
		add(TITULOPANEL);
		
		JLabel lblUsuarioCreador = new JLabel("Usuario creador");
		lblUsuarioCreador.setBounds(10, 48, 192, 14);
		add(lblUsuarioCreador);
		
		JLabel lblTituloVideo = new JLabel("Titulo Video");
		lblTituloVideo.setBounds(10, 104, 192, 14);
		add(lblTituloVideo);
		
		titulo = new JTextField();
		titulo.setBounds(10, 118, 200, 20);
		add(titulo);
		titulo.setColumns(10);
		
		JLabel lblDuracion = new JLabel("Duracion(hh:mm:ss)");
		lblDuracion.setBounds(10, 149, 192, 14);
		add(lblDuracion);
		
		duracion = new JTextField();
		duracion.setBounds(10, 162, 200, 20);
		add(duracion);
		duracion.setColumns(10);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(220, 48, 220, 14);
		add(lblUrl);
		
		url = new JTextField();
		url.setBounds(220, 62, 220, 20);
		add(url);
		url.setColumns(10);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setBounds(219, 104, 207, 14);
		add(descripcion);
		
		descrip = new JTextField();
		descrip.setBounds(220, 118, 220, 20);
		add(descrip);
		descrip.setColumns(10);
		
		JLabel lblFechaPublicacion = new JLabel("Fecha Publicacion");
		lblFechaPublicacion.setBounds(220, 149, 200, 14);
		add(lblFechaPublicacion);
		
		JDateChooser fecPub = new JDateChooser();
		fecPub.setBounds(220, 162, 220, 20);
		add(fecPub);
		
		
		JLabel lblPrivacidad = new JLabel("Privacidad");
		lblPrivacidad.setBounds(220, 193, 214, 14);
		add(lblPrivacidad);
		
		
		
		JCheckBox chckbxNewCheckBox2 = new JCheckBox("Es Privado");
		JCheckBox chckbxNewCheckBox = new JCheckBox("Es Publico");

		chckbxNewCheckBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxNewCheckBox.setSelected(!(chckbxNewCheckBox2.isSelected()));	

			}
		});
		chckbxNewCheckBox2.setEnabled(true);
		chckbxNewCheckBox2.setSelected(true);
		chckbxNewCheckBox2.setBounds(323, 210, 101, 23);
		add(chckbxNewCheckBox2);
		
		
	//	JCheckBox chckbxNewCheckBox = new JCheckBox("Es Publico");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxNewCheckBox2.setSelected(!(chckbxNewCheckBox.isSelected()));	
			}
		});
		chckbxNewCheckBox.setEnabled(true);
		chckbxNewCheckBox.setSelected(false);
		chckbxNewCheckBox.setBounds(220, 210, 101, 23);
		add(chckbxNewCheckBox);
		
		
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				videito = new Video();
				
				videito.setNombre(titulo.getText());
				videito.setDuracion(duracion.getText());
				videito.setDescripcion(descripcion.getText());
				videito.setUrl(url.getText());
				videito.setFecha(fecPub.getDate());	
				videito.setEs_publico(chckbxNewCheckBox.isSelected());
				
				VideoController controladorVideo = new VideoController();
				System.out.println(nickInfoStr);
				if (nickInfoStr == null ) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario");
				} else {
					controladorVideo.altaVideo(videito, nickInfoStr, catAsignar);
					JOptionPane.showMessageDialog(null, "Video dado de alta correctamente");
					Frame.frame.setContentPane(main);
					Frame.frame.revalidate();
				}
			}
		});
		
		btnOk.setBounds(400, 431, 390, 23);
		add(btnOk);
		
		JLabel lblCategoraopcional = new JLabel("Categoria (Opcional)");
		lblCategoraopcional.setBounds(10, 193, 122, 14);
		add(lblCategoraopcional);
		
		// BOTON DE ASIGNACION DE USUARIO
		
		UsuarioController controladorUsuario = new UsuarioController();
		ArrayList<Usuario> usuarios = controladorUsuario.listaUsuarios();
		int tamanio =  usuarios.size()+1;
		String[] array = new String[tamanio];
		array[0]="Debe elegir usuario";
		for(int i = 1; i < array.length; i++) {
		    array[i] = usuarios.get(i-1).getNickname();
		}		
		
		JComboBox userInfo1 = new JComboBox(array);
		userInfo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox1 = (JComboBox)e.getSource();
		        nickInfoStr = (String)comboBox1.getSelectedItem();
		        System.out.println("ELEG� USER Y ES: "+ nickInfoStr);  
				}
		});
		userInfo1.setBounds(10, 61, 200, 22);
		add(userInfo1);
		
		
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
		categoriaAsig.setBounds(10, 207, 200, 22);
		add(categoriaAsig);
		
		JButton btnVolver = new JButton("Cancelar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VideoMain videos = new VideoMain();
				Frame.frame.setContentPane(videos);
				Frame.frame.validate();
			}
		});
		btnVolver.setBounds(10, 431, 368, 23);
		add(btnVolver);
		
		
		
		

	}
}
