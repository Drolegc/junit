package uytube.views.listas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Frame;
import uytube.views.videos.ConsultaVideosUsuario;
import uytube.views.videos.VideoMain;

public class consulta extends JPanel {
	private String [] nombreColumnas = {"Nickname","Apellido","Nombre"};
	private Usuario usuarioSeleccionado = new Usuario();
	/**
	 * Create the panel.
	 */
	public consulta() {
		usuarioSeleccionado.setNickname("");
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 430, 226);
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
				VideoMain videos = new VideoMain();
				Frame.frame.setContentPane(videos);
				Frame.frame.validate();
			}
		});
		btnVolver.setBounds(10, 277, 212, 23);
		add(btnVolver);
		//textField.setText(seleccion );
		
		JButton btnVerVideos = new JButton("Consultar listas");
		btnVerVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			UsuarioController usercontroller = new UsuarioController();			
			//corrobora si selecciono un usuario;
				if (!usuarioSeleccionado.getNickname().isEmpty()) {
				ConsultaListasUsuario listarLU = new ConsultaListasUsuario(usuarioSeleccionado);
				Frame.frame.setContentPane(listarLU);
				Frame.frame.revalidate();
					}
				else
					{JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario");}
			}
		});
		btnVerVideos.setBounds(228, 277, 212, 23);
		add(btnVerVideos);
		
		JLabel lblIngreseUsuario = new JLabel("Seleccione usuario");
		lblIngreseUsuario.setBounds(10, 24, 140, 14);
		add(lblIngreseUsuario);
		
		JLabel lblConsulta = new JLabel("CONSULTA DE LISTAS");
		lblConsulta.setBounds(160, 11, 196, 14);
		add(lblConsulta);
		

	}
}
