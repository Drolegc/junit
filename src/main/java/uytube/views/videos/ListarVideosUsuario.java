package uytube.views.videos;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import uytube.UsuarioController.UsuarioController;
import uytube.VideoController.VideoController;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarVideosUsuario extends JPanel {
	private String [] nombreColumnas = {"Nombre","Categoria","Fecha","URL"};
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	ListarVideosUsuario(Usuario usuario) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 430, 100);
		add(scrollPane);
		
		
		VideoController controller = new VideoController();
		ArrayList<Video> Videos = controller.listaVideosUsuario(usuario.getNickname());
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
		JTable table = new JTable();
		for(Video v:Videos) {
			tablemodel.addRow(
					new Object[] {
							v.getNombre(),
							v.getCategoria().getNombre(),
							v.getFecha(),
							v.getUrl(),
							
					}
			);
		}
		
		table.setModel(tablemodel);
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDatosVideo ModifVideo = new ModificarDatosVideo();
				Frame.frame.setContentPane(ModifVideo);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(351, 266, 89, 23);
		add(btnVolver);
		
		JLabel lblEditarVideo = new JLabel("Editar video");
		lblEditarVideo.setBounds(10, 157, 79, 14);
		add(lblEditarVideo);
		
		textField = new JTextField();
		textField.setBounds(10, 171, 202, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			VideoController videocontroller = new VideoController();			
			Video video = videocontroller.consultaVideo(textField.getText(),"user2"); // harcodeado para probar el caso de uso ValoracionVideo
			EditarDatosVideo editarDV = new EditarDatosVideo(video);
			Frame.frame.setContentPane(editarDV);
			Frame.frame.revalidate();
				
				
				
			}
		});
		btnEditar.setBounds(222, 170, 89, 23);
		add(btnEditar);

	}
}
