package uytube.views.listas;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uytube.CanalController.CanalController;
import uytube.ListaController.ListaController;
import uytube.UsuarioController.UsuarioController;
import uytube.VideoController.VideoController;
import uytube.models.Lista;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.models.manager.Manager;
import uytube.views.Frame;
import uytube.views.Inicio;
import uytube.views.videos.VideoMain;

public class AgregarVideo2 extends JPanel {
	private JTable table;
	private String nickInfoStr;
	private int idVideo;
	private int idList;
	private List<Lista> listaR; 
	private Manager mng;

	public AgregarVideo2(int idVideo) {
		setLayout(null);
		String [] ModeloLista = {"Listas de Reproduccion"};
		
		System.out.println("El id del video es "+idVideo);
		
		UsuarioController controladorUsuario = new UsuarioController();
		ArrayList<Usuario> usuarios = controladorUsuario.listaUsuarios();
		int tamanio =  usuarios.size();
		String[] array = new String[tamanio];
		for(int i = 0; i < array.length; i++) {
		    array[i] = usuarios.get(i).getNickname();
		}
		
		CanalController controladorCanal = new CanalController();
		JComboBox comboBox = new JComboBox(array);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox comboBox1 = (JComboBox)e.getSource();
				String infoStringNick = (String)comboBox1.getSelectedItem();
		        
		        nickInfoStr = controladorCanal.obtenerCanalUsuario(infoStringNick).getNombre();
		        System.out.println("ELEGI USER Y ES: "+ nickInfoStr);
		        
				DefaultTableModel tablemodelLista = new DefaultTableModel(ModeloLista,0);
				
				ListaController controladorLista = new ListaController();
				
				listaR = controladorLista.listarListas(nickInfoStr);
					for(Lista list: listaR) {
						tablemodelLista.addRow(new Object [] {
											list.getNombre()
											});
						}
					
					table.setModel(tablemodelLista);
					table.revalidate();
					table.repaint();
			}
		});
		comboBox.setBounds(36, 46, 139, 22);
		add(comboBox);
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 100, 370, 123);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListaController controladorLista = new ListaController();
				System.out.println("Id Video: "+idVideo+" IdList: "+idList);
				
				VideoController controladorVideo = new VideoController();
				
				Video vidd = controladorVideo.consultaVideoPorID(idVideo);
				System.out.println("----"+vidd.getNombre());
				
				controladorLista.agregarVideo(idVideo, idList);
								
				Inicio inicio1 = new Inicio();
				Frame.frame.setContentPane(inicio1);
				Frame.frame.validate();
			}
		});
		btnConfirmar.setBounds(84, 255, 89, 23);
		add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Inicio inicio1 = new Inicio();
				Frame.frame.setContentPane(inicio1);
				Frame.frame.validate();
			}
		});
		btnVolver.setBounds(234, 255, 89, 23);
		add(btnVolver);
		
		JLabel lblUsuarioListas = new JLabel("Usuario listas ");
		lblUsuarioListas.setBounds(36, 11, 139, 24);
		add(lblUsuarioListas);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {					
					idList = listaR.get(table.getSelectedRow()).getId();
				}
			}	
		});
	}
}
