package uytube.views.videos;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

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
	private JTable table_2;
	private JTable table_1;
	private ArrayList<Video> videos;
	private String [] ArrayVideos;
	private Manager mana;
	private JTextField textField;
	
	/**
	 * Create the panel.
	 */
	public ValorarVideo() {

	// Controladores 
		VideoController controladorVideo = new VideoController();
		UsuarioController controladorUsuario = new UsuarioController();
		mana = Manager.getInstance();
		
		JLabel labelUsuario = new JLabel("Seleccionar Usuario");
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
		
		JComboBox selectUser = new JComboBox(array);
		
		selectUser.setBounds(24, 32, 200, 24);
		selectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox1 = (JComboBox)e.getSource();
		        nickInfoStr = (String)comboBox1.getSelectedItem();
		        System.out.println("ELEGI USER Y ES: " + nickInfoStr);
	
				DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
				table_2 = new JTable();
				
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
				scrollPane.setViewportView(table_1);
						
				//SI EL USUARIO TIENE VIDEOS, LO CARGO AL COMBOBOX
		
				ArrayVideos = new String[videos.size()];
				for(int i = 0; i < ArrayVideos.length; i++) {
					ArrayVideos[i] = videos.get(i).getNombre();
				}			
	
			} //FIN DEL ACCION DEL BOTON
		}); //FIN DEL ACCTION LISTENED
		

		JLabel lblElVideoElegido = new JLabel("El video elegido es:");
		lblElVideoElegido.setBounds(26, 228, 200, 15);
		
		
		JButton btnLIKE = new JButton("LIKE");
		btnLIKE.setBounds(97, 323, 103, 25);
		btnLIKE.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			ValoracionVideo valorV = new ValoracionVideo();
			
			Usuario usercito = (Usuario)mana.getSessionManager().createQuery("From Usuario where nickname =: nombre").setParameter("nombre", nickInfoStr).getSingleResult();
			mana.closeSession();
			
			System.out.println("Usuario elegido para valorar: "+ nickInfoStr);
			Video vid = controladorVideo.consultaVideo(videoInfoStr, nickInfoStr);
			
			valorV.setVideo(vid);
			valorV.setUsuario(usercito);
			valorV.setValoracion(1);
			
			ValoracionController controladorValoracion = new ValoracionController();
			controladorValoracion.valorarVideo(valorV);
			
			System.out.println("El video de nombre: "+videoInfoStr+" tiene una valoracion total de: "+controladorValoracion.valoracionActual(videoInfoStr));
						
			
			VideoMain inicio = new VideoMain();
			Frame.frame.setContentPane(inicio);
			Frame.frame.revalidate();
		}
		});
		
		JButton btnDislike = new JButton("DISLIKE");
		btnDislike.setBounds(261, 323, 114, 25);
		btnDislike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ValoracionVideo valorV = new ValoracionVideo();
				
				Usuario usercito = (Usuario)mana.getSessionManager().createQuery("From Usuario where nickname =: nombre").setParameter("nombre", nickInfoStr).getSingleResult();
				mana.closeSession();
				System.out.println("Usuario elegido para valorar: "+ nickInfoStr);
				Video vid = controladorVideo.consultaVideo(videoInfoStr, nickInfoStr);
				
				valorV.setVideo(vid);
				valorV.setUsuario(usercito);
				valorV.setValoracion(-1);
				
				ValoracionController controladorValoracion = new ValoracionController();
				controladorValoracion.valorarVideo(valorV); //ERROR AL INGRESAR A LA BDD
				
				VideoMain inicio = new VideoMain();
				Frame.frame.setContentPane(inicio);
				Frame.frame.revalidate();
			}
			});
		
		setLayout(null);
		add(labelUsuario);
		add(selectUser);
		add(lblElVideoElegido);
		add(btnLIKE);
		add(btnDislike);
		
		textField = new JTextField();
		textField.setBounds(26, 250, 231, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				videoInfoStr = textField.getText();
				System.out.println("El nombre del video es: "+videoInfoStr);
			}
		});
		btnConfirmar.setBounds(267, 249, 89, 23);
		add(btnConfirmar);
		
	
		
	}
}
