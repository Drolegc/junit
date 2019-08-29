package uytube.views.listas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uytube.UsuarioController.IUsuario;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Frame;

import javax.swing.JComboBox;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class modificar extends JPanel {

	/**
	 * Create the panel.
	 */
	public modificar() {
		setLayout(null);
		
		IUsuario controladorUsuario = new UsuarioController();
		List<Usuario> usuarios = controladorUsuario.listaUsuarios();	
		String[] array = new String[usuarios.size()];
		for(int i = 0; i < array.length; i++) {
		    array[i] = usuarios.get(i).getNombre();
		}
		
		JComboBox comboBox = new JComboBox(array);
		comboBox.setBounds(34, 55, 150, 24);
		add(comboBox);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificar.setBounds(293, 55, 114, 25);
		add(btnModificar);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(34, 172, 150, 24);
		add(comboBox_1);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(34, 30, 66, 15);
		add(lblUsuario);
		
		JLabel lblListas = new JLabel("Listas");
		lblListas.setBounds(34, 145, 66, 15);
		add(lblListas);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				inicio init = new inicio();
				Frame.frame.setContentPane(init);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(293, 249, 114, 25);
		add(btnVolver);

	}
}
