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

public class QuitarVideo1 extends JPanel {
	private JTable table;
	private String nickInfoStr;
	private int idVideo;
	private int idList;
	private List<Lista> listaR; 
	private Manager mng;

	public QuitarVideo1() {
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
				
				nickInfoStr = (String)comboBox1.getSelectedItem();
		        
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
		
		JLabel lblUsuarioListas = new JLabel("Usuario listas ");
		lblUsuarioListas.setBounds(36, 11, 139, 24);
		add(lblUsuarioListas);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {					
					idList = listaR.get(table.getSelectedRow()).getId();
					
					QuitarVideo2 vid2 = new QuitarVideo2(idList,nickInfoStr);
					Frame.frame.setContentPane(vid2);
					Frame.frame.validate();
				}
			}	
		});
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
