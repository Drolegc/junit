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
	private int videoInfoInt;
	private boolean elegiVideo;
	private String [] nombreColumnas = {"Titulo","Descripcion","Duracion","URL","Categoria","Canal"};
	private JTable table_1;
	private ArrayList<Video> videos;
	private String [] ArrayVideos;
	private Manager mana;
	private String userQueValora;
	private String userQueValora1;
	private int idVideos;
	
	/**
	 * Create the panel.
	 */
	public ValorarVideo() {

	// Controladores 
		VideoController controladorVideo = new VideoController();
		UsuarioController controladorUsuario = new UsuarioController();
		ValoracionController controladorValoracion = new ValoracionController();
		mana = Manager.getInstance();
		elegiVideo=false;
		
		JLabel labelUsuario = new JLabel("Usuario para mostrar videos");
		labelUsuario.setBounds(10, 74, 200, 15);
		
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
		scrollPane.setBounds(10, 135, 780, 224);
		add(scrollPane);
				
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		// OBTENGO EL USUARIO PARA LISTAR LOS VIDEOS A ELEGIR PARA VALORAR.
		JComboBox selectUser = new JComboBox(array);
		selectUser.setBounds(10, 100, 780, 24);
		selectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox1 = (JComboBox)e.getSource();
				nickInfoStr = (String)comboBox1.getSelectedItem();
		        
		        System.out.println("ELEGI CANAL Y ES: " + nickInfoStr);
	
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
		}); // TABLE CARGADA CON VIDEOS PARA ELEGIR.
		
		
		JButton btnLIKE = new JButton("LIKE");
		btnLIKE.setBounds(10, 431, 369, 23);
		btnLIKE.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (elegiVideo) {
				Usuario usercito = (Usuario)mana.getSessionManager().createQuery("From Usuario where nickname =: nombre").setParameter("nombre", userQueValora).getSingleResult();
				mana.closeSession();
				
				System.out.println("Usuario elegido para valorar: "+ userQueValora);

				if (controladorValoracion.existeValoracion(videoInfoInt,userQueValora)) {
					ValoracionVideo valorVideo = controladorValoracion.traerValoracion(videoInfoInt, userQueValora);
					valorVideo.setValoracion(1);
					controladorValoracion.valorarVideo(valorVideo);
					System.out.println("Si ya tengo valorada deberia entrar ac�");
				} else {
					ValoracionVideo valorV = new ValoracionVideo(); // GENERO LA NUEVA VALORACION
					Video vid = controladorVideo.consultaVideoPorID(videoInfoInt);
					valorV.setVideo(vid);
					valorV.setUsuario(usercito);
					valorV.setValoracion(1);
					controladorValoracion.valorarVideo(valorV);
				}

				JOptionPane.showMessageDialog(null,"Se le ha dado - Me Gusta - al video");
				System.out.println("El video de ID: "+videoInfoInt+" tiene una valoracion total de: "+controladorValoracion.valoracionActual(videoInfoInt,userQueValora));
							
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.validate();
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un video, gracias.");
			}
			
		}
		});
		
		JButton btnDislike = new JButton("DISLIKE");
		btnDislike.setBounds(400, 431, 390, 23);
		btnDislike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (elegiVideo) {
					Usuario usercito = (Usuario)mana.getSessionManager().createQuery("From Usuario where nickname =: nombre").setParameter("nombre", userQueValora).getSingleResult();
					mana.closeSession();


					if (controladorValoracion.existeValoracion(videoInfoInt,userQueValora)) {
						ValoracionVideo valorVideo = controladorValoracion.traerValoracion(videoInfoInt, userQueValora);
						valorVideo.setValoracion(-1);
						controladorValoracion.valorarVideo(valorVideo);
					} else {
						ValoracionVideo valorV = new ValoracionVideo(); // GENERO LA NUEVA VALORACION
						Video vid = controladorVideo.consultaVideoPorID(videoInfoInt);
						valorV.setVideo(vid);
						valorV.setUsuario(usercito);
						valorV.setValoracion(-1);
						controladorValoracion.valorarVideo(valorV);
					}
					
					JOptionPane.showMessageDialog(null,"Se le ha dado - No Me Gusta - al video");
					System.out.println("El video de ID: "+videoInfoInt+" tiene una valoracion total de: "+controladorValoracion.valoracionActual(videoInfoInt,userQueValora));
								
					Inicio inicio = new Inicio();
					Frame.frame.setContentPane(inicio);
					Frame.frame.validate();
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
		userValoracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox1 = (JComboBox)e.getSource();
				
		        userQueValora = (String)comboBox1.getSelectedItem(); 
		        }
			});
		 userValoracion.setBounds(10, 395, 780, 25);
		 add(userValoracion);
		
		JLabel lblUsuarioQueValora = new JLabel("Usuario que valora");
		lblUsuarioQueValora.setBounds(10, 370, 165, 14);
		add(lblUsuarioQueValora);
		
		JLabel lblValorarVideo = new JLabel("VALORAR VIDEO");
		lblValorarVideo.setBounds(10, 51, 165, 14);
		add(lblValorarVideo);
		
		
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {					
					videoInfoInt = videos.get(table_1.getSelectedRow()).getId();
					elegiVideo=true;
					
				}
			}	
		});
				
	}
}