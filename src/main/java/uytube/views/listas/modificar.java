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
import uytube.views.Inicio;

import javax.swing.JComboBox;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;

public class modificar extends JPanel {

	/**
	 * Create the panel.
	 */
	
	Lista lista_a_modificar;
	List<Lista> listasUser;

	
	public modificar() {
		setLayout(null);
				
		IUsuario controladorUsuario = new UsuarioController();
		List<Usuario> usuarios = controladorUsuario.listaUsuarios();	
		String[] array = new String[usuarios.size()];
		for(int i = 0; i < array.length; i++) {
		    array[i] = usuarios.get(i).getNickname();
		}
		
		JComboBox comboBox = new JComboBox(array);

		comboBox.setBounds(10, 100, 368, 23);
		add(comboBox);
		
		ILista controllerLista = new ListaController();
		
		JButton btnModificar = new JButton("Modificar");

		btnModificar.setBounds(400, 431, 390, 23);
		add(btnModificar);
		
		JComboBox comboBox_1 = new JComboBox();
		
		List<Lista> listas = controllerLista.listarListas(usuarios.get(comboBox.getSelectedIndex()).getNickname());
		comboBox_1.setModel(new DefaultComboBoxModel(this.ListasToArr(listas)));
		comboBox_1.setBounds(10, 180, 368, 24);
		add(comboBox_1);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 85, 150, 15);
		add(lblUsuario);
		
		JLabel lblListas = new JLabel("Listas");
		lblListas.setBounds(10, 165, 66, 15);
		add(lblListas);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Inicio init = new Inicio();
				Frame.frame.setContentPane(init);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(10, 431, 368, 23);
		add(btnVolver);
		
		JRadioButton rdbtnPrivacidad = new JRadioButton("Privacidad");
		rdbtnPrivacidad.setBounds(400, 181, 144, 23);
		rdbtnPrivacidad.setSelected(listas.get(0).getPrivado());
		add(rdbtnPrivacidad);
		
		ICategoria controllerCat = new CategoriaController();
		List<Categoria> categorias = controllerCat.listarCategorias();
		String[] nombreCategorias = new String[categorias.size()];
		
		for(int i=0;i<categorias.size();i++) {
			nombreCategorias[i] = categorias.get(i).getNombre();
		}
		
		JComboBox comboBox_2 = new JComboBox(nombreCategorias);
		comboBox_2.setBounds(400, 100, 390, 24);

		listasUser = controllerLista.listarListas(usuarios.get(comboBox.getSelectedIndex()).getNickname());
		lista_a_modificar = listasUser.get(comboBox_1.getSelectedIndex());

		comboBox_2.setSelectedIndex(listasUser.get(0).getCategoria().getId()-1);
		
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("Categoria cambiada");
					listasUser = controllerLista.listarListas(usuarios.get(comboBox.getSelectedIndex()).getNickname());
					comboBox_2.setSelectedIndex(listasUser.get(comboBox_1.getSelectedIndex()).getCategoria().getId()-1);
					rdbtnPrivacidad.setSelected(listasUser.get(comboBox_1.getSelectedIndex()).getPrivado());
					
					//Guardar el id de la lista seleccionada
					
					lista_a_modificar = listasUser.get(comboBox_1.getSelectedIndex());
				}
			}
		});
		
		
		add(comboBox_2);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(400, 85, 66, 15);
		add(lblCategoria);
		
		JLabel lblModificarLista = new JLabel("MODIFICAR LISTA");
		lblModificarLista.setBounds(10, 51, 196, 14);
		add(lblModificarLista);
		
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
					
					comboBox_2.setSelectedIndex(listasUser.get(0).getCategoria().getId()-1);
					rdbtnPrivacidad.setSelected(listasUser.get(0).getPrivado());
					
				}
				
			}
		});

		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Guardar cambios
				 * */
				 //Tendo id de la lista
				//Falta la categoria seleccionada y la privacidad
				System.out.println(":: A modificar ::");
				System.out.println(comboBox_2.getSelectedIndex());
				System.out.println(rdbtnPrivacidad.isSelected());
				controllerLista.modificarLista(lista_a_modificar.getId(),comboBox_2.getSelectedIndex()+1,rdbtnPrivacidad.isSelected());
				JOptionPane.showMessageDialog(null, "Lista modificada");

				
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
