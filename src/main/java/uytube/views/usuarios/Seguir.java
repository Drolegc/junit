package uytube.views.usuarios;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uytube.UsuarioController.IUsuario;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Canal;
import uytube.models.Usuario;
import uytube.views.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Seguir extends JPanel {
	private JTable table_1;
	private JTable table;

	/**
	 * Create the panel.
	 */
	
	private String nameUser_1,nameUser_2;
	
	public Seguir() {
				
		//private String [] nombreColumnas = {"Nombre","Apellido","Nickname","Correo","F.Nac"};
		
		String [] nombreColumnas = {"Usuario"};
		
		setLayout(null);
		
		//Obtener todos los usuarios
		
		IUsuario controller = new UsuarioController();
		
		List<Usuario> usuarios = controller.listaUsuarios();
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();	
			}
		});
		btnVolver.setBounds(173, 242, 114, 25);
		add(btnVolver);
		
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
		
		for(Usuario u: usuarios) {
			tablemodel.addRow(
					new Object[] {
							u.getNickname()
					}
					);
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(256, 35, 169, 160);
		add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 35, 169, 160);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(tablemodel);
		scrollPane.setViewportView(table);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Siguiendo usuarios");
				controller.seguirUsuario(nameUser_1, nameUser_2);
				JOptionPane.showMessageDialog(null, nameUser_1+" sigue a "+nameUser_2);
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();
			}
		});
		btnConfirmar.setBounds(311, 242, 114, 25);
		add(btnConfirmar);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					//Usuario seleccionado
					nameUser_1 = usuarios.get(table.getSelectedRow()).getNickname();
					
					DefaultTableModel  tablemodel_2 = new DefaultTableModel(nombreColumnas, 0);
					
					//Canales que sigue el user seleccionado
					List<Canal> canales = controller.listCanalesSeguidos(nameUser_1);
					
					for(Usuario u: usuarios) {
						boolean esta = false;
						
						for(Canal c: canales) {
							if(u.getNickname().equals(c.getNombre())) {
								System.out.println(u.getNickname());
								esta = true;
							}
						}
						
						if(!esta && !u.getNickname().equals(nameUser_1)) {
							tablemodel_2.addRow(
									new Object [] {
											u.getNickname(),
									}
									);
						}
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
					
					nameUser_2 = (String) table_1.getValueAt(table_1.getSelectedRow(),0);

				}
			}
		});
		

	}
}
