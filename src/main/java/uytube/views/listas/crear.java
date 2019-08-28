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
import uytube.views.videos.VideoMain;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class crear extends JPanel {
	private JTextField nombreListaDefualt;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	
	public crear() {
		
		ILista controller = new ListaController();
		
		setLayout(null);
		
		JLabel lblDefault = new JLabel("Default");
		lblDefault.setBounds(29, 24, 66, 15);
		add(lblDefault);
		
		nombreListaDefualt = new JTextField();
		nombreListaDefualt.setBounds(28, 56, 188, 28);
		add(nombreListaDefualt);
		nombreListaDefualt.setColumns(10);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(nombreListaDefualt.getText());
				controller.crearLista(nombreListaDefualt.getText(), null, null, true, true);
				JOptionPane.showMessageDialog(null, "Lista default cargada");
				inicio init = new inicio();
				Frame.frame.setContentPane(init);
				Frame.frame.revalidate();

			}
		});
		btnCargar.setBounds(270, 57, 114, 25);
		add(btnCargar);
		
		JLabel lblPersonalizada = new JLabel("Particular");
		lblPersonalizada.setBounds(29, 114, 82, 15);
		add(lblPersonalizada);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicio init = new inicio();
				Frame.frame.setContentPane(init);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(324, 263, 114, 25);
		add(btnVolver);
		
		

		IUsuario controladorUsuario = new UsuarioController();
		List<Usuario> usuarios = controladorUsuario.listaUsuarios();	
		String[] array = new String[usuarios.size()];
		for(int i = 0; i < array.length; i++) {
		    array[i] = usuarios.get(i).getNombre();
		}
		
		JComboBox comboBox = new JComboBox(array);
		
		comboBox.setBounds(98, 142, 125, 22);
		add(comboBox);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(29, 149, 66, 15);
		add(lblUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(29, 184, 66, 15);
		add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(108, 182, 124, 19);
		add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnPrivado = new JRadioButton("Privado");
		rdbtnPrivado.setBounds(29, 207, 144, 23);
		add(rdbtnPrivado);
		
		JLabel lblCateoria = new JLabel("Categoria");
		lblCateoria.setBounds(29, 245, 66, 15);
		add(lblCateoria);
		
		ICategoria controllerCat = new CategoriaController();
		
		
		JComboBox comboBox_1 = new JComboBox(controllerCat.listarCategoriasName());
		comboBox_1.setBounds(108, 245, 125, 24);
		add(comboBox_1);
		
		JButton btnCargar_1 = new JButton("Cargar");
		btnCargar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obtener todos los datos
				
				String user = (String)comboBox.getSelectedItem();
				String categoria = (String)comboBox_1.getSelectedItem();
				boolean isPrivate = rdbtnPrivado.isSelected();
				String nombreList = textField.getText();
				
				controller.crearLista(nombreList, categoria, user, isPrivate, false);
				JOptionPane.showMessageDialog(null, "Lista default cargada");
				inicio init = new inicio();
				Frame.frame.setContentPane(init);
				Frame.frame.revalidate();
				
			}
		});
		btnCargar_1.setBounds(270, 144, 114, 25);
		add(btnCargar_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 96, 426, 6);
		add(separator);

	}
}
