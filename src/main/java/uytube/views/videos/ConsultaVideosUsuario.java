package uytube.views.videos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uytube.CanalController.CanalController;
import uytube.VideoController.VideoController;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;

public class ConsultaVideosUsuario extends JPanel {
	private String [] nombreColumnas = {"Nombre","Categoria","Fecha"};
    private Video videoSeleccionado= new Video();
	/**
	 * Create the panel.
	 */

    ConsultaVideosUsuario(Usuario usuario) {
	    videoSeleccionado.setNombre("");//setea el video seleccionado a vacio
		VideoController controller = new VideoController();
		CanalController contrcanal = new CanalController();
		
		ArrayList<Video> Videos = controller.listaVideosUsuario(contrcanal.obtenerCanalUsuario(usuario.getNickname()).getNombre());
		//acaaa
		if(!Videos.isEmpty()) {
					
					JLabel lblSeleccione = new JLabel("Seleccione el video:");
					lblSeleccione.setBounds(10, 80, 430, 14);
					add(lblSeleccione);
					
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(10, 100, 780, 313);
					add(scrollPane);
					DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
					JTable table = new JTable();
					table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							//guardo en categoria lo seleccionado por el usuario para editar
							videoSeleccionado = Videos.get(e.getFirstIndex());
							
						}
					});
					
					for(Video v:Videos) {
						tablemodel.addRow(
								new Object[] {
										v.getNombre(),
										v.getCategoria().getNombre(),
										v.getFecha(),
										
										
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
						ConsultaVideoyComentarios consultarDV = new ConsultaVideoyComentarios(videoSeleccionado);
						Frame.frame.setContentPane(consultarDV);
						Frame.frame.revalidate();
						}
						else
							{JOptionPane.showMessageDialog(null, "Debe seleccionar un video");}

						}
					});
					btnConsultar.setBounds(400, 431, 390, 23);
					add(btnConsultar);
			}//	del if y  de aca...
			else {
				JLabel lbl2 = new JLabel("Usuario no tiene videos.");
			lbl2.setBounds(160, 157, 200, 14);
			add(lbl2);
			//{JOptionPane.showMessageDialog(null, "Usuario no tiene videos"); 
			}//hasta aca ...
	
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 431, 368, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaVideo ConsVideo = new ConsultaVideo();
				Frame.frame.setContentPane(ConsVideo);
				Frame.frame.revalidate();
			}
		});
		setLayout(null);
		add(btnVolver);
		
		JLabel label = new JLabel("CONSULTA DE VIDEO");
		label.setBounds(10, 51, 181, 14);
		add(label);

	}
}
