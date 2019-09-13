package uytube.views.listas;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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

import javax.swing.JButton;

public class QuitarVideo2 extends JPanel {
	private JTable table;
	private int idVideo;
	private String nickUser;
	List<Video> videos;
	
	public QuitarVideo2(int idList, String nickUser) {
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
			        
			//Listo los videos
			ListaController controladorLista = new ListaController();
			Lista listreproduccion = controladorLista.obtenerListaPorId(idList);
				
			DefaultTableModel  tablemodelVideo = new DefaultTableModel(ModeloVideo, 0);
			
			for(Video v: listreproduccion.getVideos()) {
				tablemodelVideo.addRow(
						new Object[] {
								v.getNombre()}
						);
			}
				
		table.setModel(tablemodelVideo);
		scrollPane.setViewportView(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					idVideo = listreproduccion.getVideos().get(table.getSelectedRow()).getId();

				}
			}
			
		});
			
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListaController controladorLista = new ListaController();			
				VideoController controladorVideo = new VideoController();
				
				Video vidd = controladorVideo.consultaVideoPorID(idVideo);
				System.out.println("----"+vidd.getNombre());
				
				controladorLista.quitarVideo(idVideo, idList);
				
				Inicio inicio1 = new Inicio();
				Frame.frame.setContentPane(inicio1);
				Frame.frame.validate();
			}
		});
		btnConfirmar.setBounds(24, 202, 89, 23);
		add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Inicio inicio1 = new Inicio();
				Frame.frame.setContentPane(inicio1);
				Frame.frame.validate();
			}
		});
		btnCancelar.setBounds(24, 250, 89, 23);
		add(btnCancelar);
		
		
		
		
	}
}
