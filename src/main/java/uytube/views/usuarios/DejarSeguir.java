package uytube.views.usuarios;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uytube.UsuarioController.IUsuario;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Canal;
import uytube.models.Usuario;
import uytube.views.Frame;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DejarSeguir extends JPanel {
	private JTable table;
	private JTable table_1;
	private String nameUser_1,nameUser_2;

	/**
	 * Create the panel.
	 */
	public DejarSeguir() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 30, 173, 186);
		add(scrollPane);
		
		String [] nombreColumnas = {"Usuario"};
		IUsuario controller = new UsuarioController();
		
		List<Usuario> usuarios = controller.listaUsuarios();
		
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
		
		for(Usuario u: usuarios) {
			tablemodel.addRow(
					new Object[] {
							u.getNickname()
					}
					);
		}
		
		table = new JTable();
		table.setModel(tablemodel);
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(33, 244, 114, 25);
		add(btnVolver);
		
		JButton btnDejarDeSeguir = new JButton("Dejar de seguir");
		btnDejarDeSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Name 1:"+nameUser_1);
				System.out.println("Name 2:"+nameUser_2);
				controller.dejarDeSeguir(nameUser_1, nameUser_2);
				JOptionPane.showMessageDialog(null, nameUser_1+" dejo de seguir a "+nameUser_2);
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();

			}
		});
		btnDejarDeSeguir.setBounds(265, 244, 173, 25);
		add(btnDejarDeSeguir);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(253, 30, 173, 186);
		add(scrollPane_1);
		

		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					//Obtenemos el user seleccionado
					nameUser_1 = usuarios.get(table.getSelectedRow()).getNickname();
					List<Canal> canales = controller.listCanalesSeguidos(nameUser_1);
					System.out.println("Seguidores de "+nameUser_1+": ");
					
					DefaultTableModel  tablemodel_2 = new DefaultTableModel(nombreColumnas, 0);
					
					for(Canal c: canales) {
						System.out.println(c.getNombre());
						tablemodel_2.addRow(
								new Object[] {
										c.getNombre(),
								}
								);
					}
					
					table_1.setModel(tablemodel_2);
					table_1.revalidate();
					table_1.repaint();
					
				}
			}
		});
		
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					nameUser_2 = usuarios.get(table_1.getSelectedRow()).getNickname();
				}
			}
		});

	}

}
