package uytube.views.videos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Frame;
import uytube.views.Inicio;

public class ModificarDatosVideo extends JPanel {
	private String [] nombreColumnas = {"Nickname","Apellido","Nombre"};
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ModificarDatosVideo() {
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 430, 100);
		add(scrollPane);
		
		UsuarioController controller = new UsuarioController();
		ArrayList<Usuario> usuarios = controller.listaUsuarios();
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
		JTable table = new JTable();
		for(Usuario u:usuarios) {
			tablemodel.addRow(
					new Object[] {
							u.getNickname(),
							u.getApellido(),
							u.getNombre(),
							
					}
			);
		}
		
		table.setModel(tablemodel);
		scrollPane.setViewportView(table);
	
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VideoMain videos = new VideoMain();
				Frame.frame.setContentPane(videos);
				Frame.frame.validate();
			}
		});
		btnVolver.setBounds(170, 266, 89, 23);
		add(btnVolver);
		
		textField = new JTextField();
		textField.setBounds(10, 168, 150, 20);
		add(textField);
		textField.setColumns(10);
		//textField.setText(seleccion );
		
		JButton btnVerVideos = new JButton("Ver videos");
		btnVerVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			UsuarioController usercontroller = new UsuarioController();			
			Usuario usuario = usercontroller.consultarUsuario(textField.getText());
			ListarVideosUsuario listarVU = new ListarVideosUsuario(usuario);
			Frame.frame.setContentPane(listarVU);
			Frame.frame.revalidate();
			}
		});
		btnVerVideos.setBounds(170, 167, 89, 23);
		add(btnVerVideos);
		
		JLabel lblIngreseUsuario = new JLabel("Ingrese usuario");
		lblIngreseUsuario.setBounds(23, 154, 89, 14);
		add(lblIngreseUsuario);
		

	}
}
