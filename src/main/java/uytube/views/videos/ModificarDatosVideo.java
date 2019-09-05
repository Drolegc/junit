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

import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Frame;
import uytube.views.Inicio;

public class ModificarDatosVideo extends JPanel {
	private String [] nombreColumnas = {"Nickname","Apellido","Nombre"};
	private Usuario usuarioSeleccionado = new Usuario();

	/**
	 * Create the panel.
	 */
	public ModificarDatosVideo() {
		usuarioSeleccionado.setNickname("");
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 430, 201);
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
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//guardo en categoria lo seleccionado por el usuario para editar
				usuarioSeleccionado = usuarios.get(e.getFirstIndex());
				
			}
		});
		//textField.setText(seleccion );
		
		JButton btnVerVideos = new JButton("Ver videos");
		btnVerVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			UsuarioController usercontroller = new UsuarioController();			
			//corrobora si selecciono un usuario;
			if (!usuarioSeleccionado.getNickname().isEmpty()) {
				ListarVideosUsuario listarVU = new ListarVideosUsuario(usuarioSeleccionado);
				Frame.frame.setContentPane(listarVU);
				Frame.frame.revalidate();
			}
			else
				{JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario");}
			}
		});
		
		JLabel lblIngreseUsuario = new JLabel("Seleccione un usuario");
		lblIngreseUsuario.setBounds(10, 49, 224, 14);
		add(lblIngreseUsuario);
		btnVerVideos.setBounds(223, 277, 217, 23);
		add(btnVerVideos);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VideoMain videos = new VideoMain();
				Frame.frame.setContentPane(videos);
				Frame.frame.validate();
			}
		});
		btnVolver.setBounds(10, 277, 203, 23);
		add(btnVolver);
		
		JLabel lblEditarVideo = new JLabel("EDITAR VIDEO");
		lblEditarVideo.setBounds(186, 11, 192, 14);
		add(lblEditarVideo);
		

	}
}
