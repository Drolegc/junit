package uytube.views.usuarios.consultar;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Hibernate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import uytube.models.Lista;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.usuarios.UserMain;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class verLista extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private String [] nombreColumnas = {"Nombre","Categoria","Fecha","URL"};

	/**
	 * Create the panel.
	 */
	public verLista(Lista lista, Usuario user) {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 100, 265, 19);
		textField.setText(lista.getNombre());
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(285, 100, 244, 19);
		textField_1.setColumns(10);
		textField_1.setText(lista.getCategoria().getNombre());
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(539, 100, 251, 19);
		textField_2.setColumns(10);
		textField_2.setText((lista.getPrivado()) ? "Si":"No");
		add(textField_2);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 86, 107, 13);
		add(lblNombre);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(285, 86, 244, 13);
		add(lblCategoria);
		
		JLabel lblEsPrivada = new JLabel("Es privada?");
		lblEsPrivada.setBounds(539, 86, 251, 13);
		add(lblEsPrivada);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 780, 271);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
		Hibernate.initialize(lista.getVideos());
		for(Video v:lista.getVideos()) {
			tablemodel.addRow(
					new Object[] {
							v.getNombre(),
							v.getCategoria().getNombre(),
							v.getFecha(),
							v.getUrl(),
							
					}
			);
		}		
		table.setModel(tablemodel);
		scrollPane.setViewportView(table);
		
		JButton btnEditar = new JButton("Editar ");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modificarLista modlista = new modificarLista(lista);
				Frame.frame.setContentPane(modlista);
				Frame.frame.revalidate();

			}
		});
		btnEditar.setBounds(355, 269, 85, 21);
		add(btnEditar);
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();				
			}
		});
		btnVolver.setBounds(400, 431, 390, 23);
		add(btnVolver);
		
		JLabel label = new JLabel("CONSULTA DE VIDEO");
		label.setBounds(10, 51, 196, 14);
		add(label);
	}
}
