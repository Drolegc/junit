package uytube.views.usuarios.editar;

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
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;

public class editarLista extends JPanel {

	/**
	 * Create the panel.
	 */
	
	Lista lista_a_modificar;
	List<Lista> listasUser;

	
	public editarLista(Lista lista, Usuario user) {
		setLayout(null);
				
		IUsuario controladorUsuario = new UsuarioController();
		List<Usuario> usuarios = controladorUsuario.listaUsuarios();	
		String[] array = new String[usuarios.size()];
		for(int i = 0; i < array.length; i++) {
		    array[i] = usuarios.get(i).getNickname();
		}
		
		ILista controllerLista = new ListaController();
		
		JButton btnModificar = new JButton("Modificar");

		btnModificar.setBounds(326, 251, 114, 25);
		add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoVideosListas info = new infoVideosListas(user);
				Frame.frame.setContentPane(info);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(202, 251, 114, 25);
		add(btnVolver);
		

		
		ICategoria controllerCat = new CategoriaController();
		List<Categoria> categorias = controllerCat.listarCategorias();
		String[] nombreCategorias = new String[categorias.size()];
		
		for(int i=0;i<categorias.size();i++) {
			nombreCategorias[i] = categorias.get(i).getNombre();
		}
		
		JComboBox comboBox_2 = new JComboBox(nombreCategorias);
		comboBox_2.setBounds(10, 55, 144, 24);

		comboBox_2.setSelectedIndex(lista.getCategoria().getId()-1);
		
		
		add(comboBox_2);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 30, 66, 15);
		add(lblCategoria);
		
		JRadioButton rdbtnPrivada = new JRadioButton("Privada");
		JRadioButton rdbtnOpublica = new JRadioButton("Publica");

		rdbtnPrivada.setBounds(263, 56, 76, 23);
		add(rdbtnPrivada);

		
		if(lista.getPrivado()) {
			rdbtnPrivada.setSelected(true);
		}else {
			rdbtnOpublica.setSelected(true);

		}
		rdbtnPrivada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnOpublica.setSelected(false);
			}
		});		
		rdbtnPrivada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnOpublica.setSelected(false);
			}
		});
		rdbtnOpublica.setBounds(339, 57, 105, 21);
		add(rdbtnOpublica);

		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean privada = (rdbtnOpublica.isSelected()) ? false:true;
				controllerLista.modificarLista(lista.getId(),comboBox_2.getSelectedIndex()+1,privada);
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
