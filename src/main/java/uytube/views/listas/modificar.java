package uytube.views.listas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uytube.CategoriaController.CategoriaController;
import uytube.CategoriaController.ICategoria;
import uytube.ListaController.ILista;
import uytube.ListaController.ListaController;
import uytube.UsuarioController.IUsuario;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Categoria;
import uytube.models.Lista;
import uytube.models.Usuario;
import uytube.views.Frame;

import javax.swing.JComboBox;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;

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
		    array[i] = usuarios.get(i).getNickname();
		}
		
		JComboBox comboBox = new JComboBox(array);

		comboBox.setBounds(34, 55, 150, 24);
		add(comboBox);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Guardar cambios
				 * */
			}
		});
		btnModificar.setBounds(293, 212, 114, 25);
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
		
		JRadioButton rdbtnPrivacidad = new JRadioButton("Privacidad");
		rdbtnPrivacidad.setBounds(263, 125, 144, 23);
		rdbtnPrivacidad.setSelected(true);
		add(rdbtnPrivacidad);
		
		ICategoria controllerCat = new CategoriaController();
		List<Categoria> categorias = controllerCat.listarCategorias();
		String[] nombreCategorias = new String[categorias.size()];
		
		for(int i=0;i<categorias.size();i++) {
			nombreCategorias[i] = categorias.get(i).getNombre();
		}
		
		JComboBox comboBox_2 = new JComboBox(nombreCategorias);
		comboBox_2.setBounds(263, 55, 144, 24);
		
		
		
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("Categoria cambiada");
					List<Lista> listasUser = controllerLista.listarListas(usuarios.get(comboBox.getSelectedIndex()).getNickname());
					comboBox_2.setSelectedIndex(listasUser.get(comboBox_1.getSelectedIndex()).getCategoria().getId()-1);
					rdbtnPrivacidad.setSelected(listasUser.get(comboBox_1.getSelectedIndex()).getPrivado());
				}
			}
		});
		
		
		add(comboBox_2);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(263, 30, 66, 15);
		add(lblCategoria);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					//Se cambio el usuario
					
					//obtener nickname
					
					String nombreUser = usuarios.get(comboBox.getSelectedIndex()).getNickname();
					System.out.println(nombreUser);
					
					List<Lista> listasUser = controllerLista.listarListas(usuarios.get(comboBox.getSelectedIndex()).getNickname());
					//buscar las lsitas del usuario
					comboBox_1.setModel(new DefaultComboBoxModel(ListasToArr(listasUser)));
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
