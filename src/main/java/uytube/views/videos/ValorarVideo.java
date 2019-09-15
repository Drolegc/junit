package uytube.views.videos;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.CanalController.CanalController;
import uytube.UsuarioController.UsuarioController;
import uytube.ValoracionController.ValoracionController;
import uytube.VideoController.VideoController;
import uytube.models.Canal;
import uytube.models.Usuario;
import uytube.models.ValoracionVideo;
import uytube.models.Video;
import uytube.models.manager.Manager;
import uytube.views.Frame;
import uytube.views.Inicio;
import uytube.views.listas.QuitarVideo2;

import com.jgoodies.forms.layout.FormSpecs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ValorarVideo extends JPanel {
	private JTable table;
	private String nickInfoStr;
	private String videoInfoStr;
	private String [] nombreColumnas = {"Titulo","Descripcion","Duracion","URL","Categoria","Canal"};
	private JTable table_1;
	private ArrayList<Video> videos;
	private String [] ArrayVideos;
	private Manager mana;
	private String userQueValora;
	private int idVideos;
	
	/**
	 * Create the panel.
	 */
	public ValorarVideo() {

	// Controladores 
		VideoController controladorVideo = new VideoController();
		UsuarioController controladorUsuario = new UsuarioController();
		mana = Manager.getInstance();
		videoInfoStr=null;
		
		JLabel labelUsuario = new JLabel("Usuario para mostrar videos");
		labelUsuario.setBounds(24, 11, 200, 15);
		
	// CREACION LISTA USUARIO PARA ELEGIR
		ArrayList<Usuario> usuarios = controladorUsuario.listaUsuarios();
		int tamanio =  usuarios.size()+1;
		String[] array = new String[tamanio];
		array[0]="Debe elegir usuario";
		for(int i = 1; i < array.length; i++) {
		    array[i] = usuarios.get(i-1).getNickname();
		}
		
		//FIN SELECCION
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 87, 414, 130);
		add(scrollPane);
				
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		

		CanalController controladorCanal = new CanalController();
		JComboBox selectUser = new JComboBox(array);
		selectUser.setBounds(24, 32, 200, 24);
		selectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox1 = (JComboBox)e.getSource();
				String infoStringNick = (String)comboBox1.getSelectedItem();
		        
		        nickInfoStr = controladorCanal.obtenerCanalUsuario(infoStringNick).getNombre();
		        System.out.println("ELEGI USER Y ES: " + nickInfoStr);
	
				DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
				
		//CREACION DE LISTA DE VIDEOS
				videos = controladorVideo.listaVideosUsuario(nickInfoStr);
			//FIN
				for(Video v:videos) {							
					tablemodel.addRow(
						new Object[] {
								v.getNombre(),
								v.getDescripcion(),
								v.getDuracion(),
								v.getUrl(),
								v.getCategoria().getNombre(),
								v.getCanal().getNombre()
						});
				}
				table_1.setModel(tablemodel);
				
				table_1.revalidate();
				table_1.repaint();
						
				//SI EL USUARIO TIENE VIDEOS, LO CARGO AL COMBOBOX
							
			} //FIN DEL ACCION DEL BOTON
		});
		
		
		JButton btnLIKE = new JButton("LIKE");
		btnLIKE.setBounds(97, 323, 103, 25);
		btnLIKE.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (videoInfoStr != null) {
				Usuario usercito = (Usuario)mana.getSessionManager().createQuery("From Usuario where nickname =: nombre").setParameter("nombre", userQueValora).getSingleResult();
				mana.closeSession();
				
				System.out.println("Usuario elegido para valorar: "+ userQueValora);
				Video vid = controladorVideo.consultaVideo(videoInfoStr, userQueValora);
				ValoracionController controladorValoracion = new ValoracionController();
				
				
				if (controladorValoracion.existeValoracion(videoInfoStr,userQueValora)) {
					ValoracionVideo valorVideo = controladorValoracion.traerValoracion(videoInfoStr, userQueValora);
					valorVideo.setValoracion(1);
					controladorValoracion.valorarVideo(valorVideo);
				} else {
					ValoracionVideo valorV = new ValoracionVideo(); // GENERO LA NUEVA VALORACION
					valorV.setVideo(vid);
					valorV.setUsuario(usercito);
					valorV.setValoracion(1);
					controladorValoracion.valorarVideo(valorV);
				}

				//System.out.println("El video de nombre: "+videoInfoStr+" tiene una valoracion total de: "+controladorValoracion.valoracionActual(videoInfoStr,userQueValora));
							
				VideoMain inicio = new VideoMain();
				Frame.frame.setContentPane(inicio);
				Frame.frame.revalidate();
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un video, gracias.");
			}
			
		}
		});
		
		JButton btnDislike = new JButton("DISLIKE");
		btnDislike.setBounds(261, 323, 114, 25);
		btnDislike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (videoInfoStr != null) {
					Usuario usercito = (Usuario)mana.getSessionManager().createQuery("From Usuario where nickname =: nombre").setParameter("nombre", nickInfoStr).getSingleResult();
				mana.closeSession();
				
				System.out.println("Usuario elegido para valorar: "+ userQueValora);
				Video vid = controladorVideo.consultaVideo(videoInfoStr, userQueValora);

				ValoracionController controladorValoracion = new ValoracionController();
					
				if (controladorValoracion.existeValoracion(videoInfoStr,userQueValora)) {
					ValoracionVideo valorVideo = controladorValoracion.traerValoracion(videoInfoStr, userQueValora);
					valorVideo.setValoracion(-1);
					controladorValoracion.valorarVideo(valorVideo);
				} else {
					ValoracionVideo valorV = new ValoracionVideo(); // GENERO LA NUEVA VALORACION
					valorV.setVideo(vid);
					valorV.setUsuario(usercito);
					valorV.setValoracion(-1);
					controladorValoracion.valorarVideo(valorV);
				}

				System.out.println("El video de nombre: "+videoInfoStr+" tiene una valoracion total de: "+controladorValoracion.valoracionActual(videoInfoStr,nickInfoStr));
							
				VideoMain inicio = new VideoMain();
				Frame.frame.setContentPane(inicio);
				Frame.frame.revalidate();
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un video, gracias.");
				}
				
			}
			});
		
		setLayout(null);
		add(labelUsuario);
		add(selectUser);
		add(btnLIKE);
		add(btnDislike);
		
		JComboBox userValoracion = new JComboBox(array);
		selectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox1 = (JComboBox)e.getSource();
		        userQueValora = (String)comboBox1.getSelectedItem();
		        }
			});
		 userValoracion.setBounds(26, 267, 198, 25);
		 add(userValoracion);
		
		JLabel lblUsuarioQueValora = new JLabel("Usuario que valora");
		lblUsuarioQueValora.setBounds(26, 242, 165, 14);
		add(lblUsuarioQueValora);
		
		
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {					
					videoInfoStr = videos.get(table_1.getSelectedRow()).getNombre();
					System.out.println("Hola me llamo juna");
					
				}
			}	
		});
				
	}
}
