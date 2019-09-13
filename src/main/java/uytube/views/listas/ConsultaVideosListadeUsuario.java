package uytube.views.listas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uytube.ListaController.ListaController;
import uytube.UsuarioController.UsuarioController;
import uytube.VideoController.VideoController;
import uytube.models.Lista;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.videos.ConsultaVideo;
import uytube.views.videos.ConsultaVideoyComentarios;

public class ConsultaVideosListadeUsuario extends JPanel {
	
	
	
	private String [] nombreColumnas = {"Nombre","Duracion","Es publico"};
	 private Video videoSeleccionado= new Video();
		/**
		 * Create the panel.
		 */

	    ConsultaVideosListadeUsuario(List<Video> ListaVideos) {
		    videoSeleccionado.setNombre("");//setea el video seleccionado a vacio
			//ListaController controller = new ListaController();
			//List<Video> ListaVideos = controller.listaVideosUsuario(usuario.getNickname());
			//if(!Videos.isEmpty()) {
						
						JLabel lblSeleccione = new JLabel("Seleccione el video:");
						lblSeleccione.setBounds(10, 25, 430, 14);
						add(lblSeleccione);
						
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(10, 41, 430, 224);
						add(scrollPane);
						DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
						JTable table = new JTable();
						table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								//guardo en categoria lo seleccionado por el usuario para editar
								videoSeleccionado = ListaVideos.get(e.getFirstIndex());
								
							}
						});
						
						for(Video v:ListaVideos) {
							tablemodel.addRow(
									new Object[] {
											v.getNombre(),
											v.getCategoria().getNombre(),
											v.getEs_publico(),
											
											
									}
							);
						}
						
						table.setModel(tablemodel);
						scrollPane.setViewportView(table);
						
						JButton btnConsultar = new JButton("Consultar video");
						btnConsultar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							VideoController videocontroller = new VideoController();			
							
							//corrobora si selecciono un video;
							if (!videoSeleccionado.getNombre().isEmpty()) {
						
							Usuario u = new Usuario();
							u.setNickname(videoSeleccionado.getCanal().getNombre());
							ConsultaListasUsuario consultarDV = new ConsultaListasUsuario(u);
							Frame.frame.setContentPane(consultarDV);
							Frame.frame.revalidate();
							}
							else
								{JOptionPane.showMessageDialog(null, "Debe seleccionar un video");}
								
								
								
							}
						});
						btnConsultar.setBounds(230, 277, 210, 23);
						add(btnConsultar);
		/*		}//	del if y  de aca...
				else {
					JLabel lbl2 = new JLabel("Usuario no tiene videos.");
				lbl2.setBounds(160, 157, 200, 14);
				add(lbl2);
				//{JOptionPane.showMessageDialog(null, "Usuario no tiene videos"); 
				}//hasta aca ...
			*/
			
			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(10, 277, 210, 23);
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UsuarioController usercontrol = new UsuarioController();
					ConsultaListasUsuario consultarDV = new ConsultaListasUsuario(usercontrol.consultarUsuario(videoSeleccionado.getCanal().getNombre()));
					Frame.frame.setContentPane(consultarDV);
					Frame.frame.revalidate();
					}
			});
			setLayout(null);
			add(btnVolver);
			
			JLabel label = new JLabel("CONSULTA DE VIDEO");
			label.setBounds(160, 11, 181, 14);
			add(label);

		}
	}
