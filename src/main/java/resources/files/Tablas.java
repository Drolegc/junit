package resources.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Frame;
import uytube.views.usuarios.UserMain;
import uytube.views.usuarios.editar.editarMain;

public class Tablas extends JPanel{
	private JTable table;
	private String [][] datos ;
	private JTable table_1;
	private JFrame frame;
	private Usuario user;
	public Tablas(String [] columnas,Class clase,String nombreClase) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class genericClass = null;
		try {
			genericClass = Class.forName("uytube.models." + nombreClase);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object instance = genericClass.getDeclaredConstructor().newInstance();
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();				
			}
		});
		btnVolver.setBounds(241, 269, 85, 21);
		add(btnVolver);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 430, 230);
		add(scrollPane);
		
		UsuarioController controller = new UsuarioController();
		ArrayList<Usuario> usuarios = controller.listaUsuarios();
		DefaultTableModel  tablemodel = new DefaultTableModel(columnas, 0);
		table = new JTable();
		for(Usuario u:usuarios) {
			tablemodel.addRow(
					new Object[] {
							u.getNombre(),
							u.getApellido(),
							u.getNickname(),
							u.getCorreo(),
							u.getFnacimiento()
					}
			);
		}
		table.setModel(tablemodel);
		scrollPane.setViewportView(table);
		
		JButton btnEditarUsuario = new JButton("Editar usuario");
		btnEditarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarMain editar = new editarMain(user);
				Frame.frame.setContentPane(editar);
				Frame.frame.revalidate();

			}
		});
		btnEditarUsuario.setBounds(336, 269, 104, 21);
		add(btnEditarUsuario);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				user = usuarios.get(e.getFirstIndex());
			}
		});
		
		

	}
}