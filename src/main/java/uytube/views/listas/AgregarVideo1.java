package uytube.views.listas;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uytube.CanalController.CanalController;
import uytube.ListaController.ListaController;
import uytube.UsuarioController.IUsuario;
import uytube.UsuarioController.UsuarioController;
import uytube.VideoController.IVideo;
import uytube.VideoController.VideoController;
import uytube.models.Lista;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.Inicio;

public class AgregarVideo1 extends JPanel {
	private JTable table;
	private int idVideo;
	private String nickInfoStr;
	List<Video> videos;
	
	public AgregarVideo1() {
		setLayout(null);
		
		String [] ModeloVideo = {"Nombre video"};
		String [] ModeloLista = {"Listas de Reproduccion"};
		
		JLabel lblElijaUsuarioPara = new JLabel("Elija usuario para listar videos");
		lblElijaUsuarioPara.setBounds(21, 23, 151, 14);
		add(lblElijaUsuarioPara);
		
		
		// BOTON DE ASIGNACION DE USUARIO
		
		UsuarioController controladorUsuario = new UsuarioController();
		ArrayList<Usuario> usuarios = controladorUsuario.listaUsuarios();
		int tamanio =  usuarios.size()+1;
		String[] array = new String[tamanio];
		array[0]="Debe elegir usuario";
		for(int i = 1; i < array.length; i++) {
		    array[i] = usuarios.get(i-1).getNickname();
		}		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(204, 48, 219, 245);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		CanalController controladorCanal = new CanalController();
		JComboBox comboBox = new JComboBox(array);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox1 = (JComboBox)e.getSource();
				nickInfoStr = (String)comboBox1.getSelectedItem();
		        
		        System.out.println("ELEGI USER Y ES: "+ nickInfoStr);
				
			
				//Listo los videos
				VideoController controller = new VideoController();
				videos = controller.listaVideosUsuario(nickInfoStr);
				
				DefaultTableModel  tablemodelVideo = new DefaultTableModel(ModeloVideo, 0);
				for(Video v: videos) {
					tablemodelVideo.addRow(
							new Object[] {
									v.getNombre()}
							);
					}
				table.setModel(tablemodelVideo);
				scrollPane.setViewportView(table);
			}
		});
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					
					idVideo = videos.get(table.getSelectedRow()).getId();
					
					AgregarVideo2 vid2 = new AgregarVideo2(idVideo);
					Frame.frame.setContentPane(vid2);
					Frame.frame.validate();
				}
			}
			
		});
		
		comboBox.setBounds(21, 48, 151, 22);
		add(comboBox);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Inicio init = new Inicio();
				Frame.frame.setContentPane(init);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(10, 431, 368, 23);
		add(btnVolver);
		
		
	}
}
