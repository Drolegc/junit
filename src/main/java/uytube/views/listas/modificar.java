package uytube.views.listas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uytube.ListaController.ILista;
import uytube.ListaController.ListaController;
import uytube.UsuarioController.IUsuario;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Lista;
import uytube.models.Usuario;
import uytube.views.Frame;

import javax.swing.JComboBox;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
		btnModificar.setBounds(293, 55, 114, 25);
		add(btnModificar);
		
		JComboBox comboBox_1 = new JComboBox();
		
		ILista controllerLista = new ListaController();
		
		List<Lista> listas = controllerLista.listarListas(usuarios.get(comboBox.getSelectedIndex()).getNickname());
		
		comboBox_1.setModel(new DefaultComboBoxModel(this.ListasToArr(listas)));
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
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					//Se cambio el usuario
					
					//obtener nickname
					
					String nombreUser = usuarios.get(comboBox.getSelectedIndex()).getNombre();
					System.out.println(nombreUser);
					
					//buscar las lsitas del usuario
					
					comboBox_1.setModel(new DefaultComboBoxModel(ListasToArr(listas)));
					comboBox_1.revalidate();
					comboBox_1.repaint();
				}
				
			}
		});

	}
	
	private String[] ListasToArr(List<Lista> listas ) {
		String[] arr = new String[listas.size()];
		for(int i = 0;i<listas.size();i++) {
			arr[i] = listas.get(i).getNombre();
		}
		return arr;
	}
}
