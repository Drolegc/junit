package uytube.views.listas;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import uytube.CategoriaController.CategoriaController;
import uytube.CategoriaController.ICategoria;
import uytube.ListaController.ILista;
import uytube.ListaController.ListaController;
import uytube.UsuarioController.IUsuario;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Frame;
import uytube.views.Inicio;
import uytube.views.videos.VideoMain;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class crear extends JPanel {
	private JTextField nombreListaDefualt;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	
	public crear() {
		
		ILista controller = new ListaController();
		
		setLayout(null);
		
		JLabel lblDefault = new JLabel("Alta de lista por default");
		lblDefault.setBounds(400, 82, 390, 15);
		add(lblDefault);
		
		nombreListaDefualt = new JTextField();
		nombreListaDefualt.setBounds(400, 100, 390, 23);
		add(nombreListaDefualt);
		nombreListaDefualt.setColumns(10);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(nombreListaDefualt.getText());
				if(controller.crearLista(nombreListaDefualt.getText(), null, null, true, true)) {
					JOptionPane.showMessageDialog(null, "Lista default cargada");
				}
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.validate();
			}
		});
		btnCargar.setBounds(400, 329, 390, 23);
		add(btnCargar);
		
		JLabel lblPersonalizada = new JLabel("Alta de lista de un usuario");
		lblPersonalizada.setBounds(10, 82, 368, 15);
		add(lblPersonalizada);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicio init = new inicio();
				Frame.frame.setContentPane(init);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(10, 431, 368, 23);
		add(btnVolver);
		
		

		IUsuario controladorUsuario = new UsuarioController();
		List<Usuario> usuarios = controladorUsuario.listaUsuarios();	
		String[] array = new String[usuarios.size()];
		for(int i = 0; i < array.length; i++) {
		    array[i] = usuarios.get(i).getNickname();
		}
		
		JComboBox comboBox = new JComboBox(array);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cambiando");
				System.out.println(comboBox.getSelectedIndex());
			}
		});
		

		
		comboBox.setBounds(10, 100, 368, 23);
		add(comboBox);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 135, 66, 15);
		add(lblUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 161, 66, 15);
		add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(10, 187, 368, 23);
		add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnPrivado = new JRadioButton("Privado");
		rdbtnPrivado.setBounds(10, 228, 144, 23);
		add(rdbtnPrivado);
		
		JLabel lblCateoria = new JLabel("Categoria");
		lblCateoria.setBounds(10, 258, 66, 15);
		add(lblCateoria);
		
		ICategoria controllerCat = new CategoriaController();
		
		
		JComboBox comboBox_1 = new JComboBox(controllerCat.listarCategoriasName());
		
		comboBox_1.setBounds(10, 284, 368, 23);
		add(comboBox_1);
		
		JButton btnCargar_1 = new JButton("Cargar");
		btnCargar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obtener todos los datos
				
				String user = (String)comboBox.getSelectedItem();
				String categoria = (String)comboBox_1.getSelectedItem();
				boolean isPrivate = rdbtnPrivado.isSelected();
				String nombreList = textField.getText();
				
				if(controller.crearLista(nombreList, categoria, user, isPrivate, false)) {
					JOptionPane.showMessageDialog(null, "Lista personalizada cargada");
				}
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.validate();
				
			}
		});
		btnCargar_1.setBounds(10, 329, 368, 23);
		add(btnCargar_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(388, 99, 2, 318);
		add(separator);
		
		JLabel lblCrearListaDe = new JLabel("CREAR LISTA DE VIDEOS");
		lblCrearListaDe.setBounds(10, 51, 206, 14);
		add(lblCrearListaDe);

	}
}
