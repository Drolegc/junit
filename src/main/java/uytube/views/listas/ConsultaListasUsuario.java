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
import uytube.VideoController.VideoController;
import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.Lista;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.videos.ConsultaVideo;
import uytube.views.videos.ConsultaVideoyComentarios;

public class ConsultaListasUsuario extends JPanel {
	private String [] nombreColumnas = {"Nombre","Es privada","Tipo"};
    private Lista ListaSeleccionada= new Lista();
	/**
	 * Create the panel.
	 */
	public ConsultaListasUsuario(Usuario usuario) {
		 ListaSeleccionada.setNombre("");//setea la lista simbolicamente en vacia 
			ListaController controller = new ListaController();
			List<Lista> Listas = controller.listarListas(usuario.getNickname());
			//if(!Videos.isEmpty()) {
						
						JLabel lblSeleccione = new JLabel("Seleccione la lista:");
						lblSeleccione.setBounds(10, 84, 430, 14);
						add(lblSeleccione);
						
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(20, 99, 780, 313);
						add(scrollPane);
						DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
						JTable table = new JTable();
						table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								//guardo en categoria lo seleccionado por el usuario para editar
								ListaSeleccionada= Listas.get(e.getFirstIndex());
								
							}
						});
						
						for(Lista l:Listas) {
							tablemodel.addRow(
									new Object[] {
											l.getNombre(),
											l.getPrivado(),
											l.getCategoria().getNombre(),
											l.getVideos(),
											
											
											
									}
							);
						}
						
						table.setModel(tablemodel);
						scrollPane.setViewportView(table);
						
						JButton btnConsultar = new JButton("Consultar videos");
						btnConsultar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							VideoController videocontroller = new VideoController();			
							
							//corrobora si selecciono un video;
							if (!ListaSeleccionada.getNombre().isEmpty()) {
						ConsultaVideosListadeUsuario consultarLU = new ConsultaVideosListadeUsuario(ListaSeleccionada.getVideos());
							Frame.frame.setContentPane(consultarLU);
							Frame.frame.revalidate();
							}
							else
								{JOptionPane.showMessageDialog(null, "Debe seleccionar un video");}
								
								
								
							}
						});
						btnConsultar.setBounds(400, 431, 390, 23);
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
			btnVolver.setBounds(10, 431, 368, 23);
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					uytube.views.listas.consulta listarLU = new uytube.views.listas.consulta();
					Frame.frame.setContentPane(listarLU);
					Frame.frame.revalidate();
				}
			});
			setLayout(null);
			add(btnVolver);
			
			JLabel lblConsultaDeLista = new JLabel("CONSULTA DE LISTA");
			lblConsultaDeLista.setBounds(10, 51, 146, 14);
			add(lblConsultaDeLista);

		}
	}
