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
import uytube.models.Categoria;
import uytube.models.Usuario;
import uytube.views.Frame;

public class AltaComentario extends JPanel {
	private String [] nombreColumnas = {"Nickname","Apellido","Nombre"};
	private Usuario usuarioSeleccionado = new Usuario();
	
	/**
	 * Create the panel.
	 */
	public AltaComentario() {
		
		usuarioSeleccionado.setNickname("");
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 780, 313);
		add(scrollPane);
		
				
		
		UsuarioController controller = new UsuarioController();
		ArrayList<Usuario> usuarios = controller.listaUsuarios();
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
		JTable table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//guardo en categoria lo seleccionado por el usuario para editar
				usuarioSeleccionado = usuarios.get(e.getFirstIndex());
			}
		});
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
				/*VideoMain videos = new VideoMain();
				Frame.frame.setContentPane(videos);
				Frame.frame.validate();*/
			}
		});
		btnVolver.setBounds(10, 431, 368, 23);
		add(btnVolver);
		//textField.setText(seleccion );
		
		JButton btnVerVideos = new JButton("Consultar videos de usuario");
		btnVerVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			UsuarioController usercontroller = new UsuarioController();			
			//corrobora si selecciono un usuario;
				if (!usuarioSeleccionado.getNickname().isEmpty()) {
				AltaComentario2 listarVU = new AltaComentario2(usuarioSeleccionado);
				Frame.frame.setContentPane(listarVU);
				Frame.frame.revalidate();
					}
				else
					{JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario");}
			}
		});
		btnVerVideos.setBounds(400, 431, 390, 23);
		add(btnVerVideos);
		
		JLabel lblIngreseUsuario = new JLabel("Seleccione usuario poseedor del video:");
		lblIngreseUsuario.setBounds(10, 80, 368, 14);
		add(lblIngreseUsuario);
		
		JLabel lblConsulta = new JLabel("COMENTAR VIDEO");
		lblConsulta.setBounds(10, 51, 196, 14);
		add(lblConsulta);
		

	}
}
