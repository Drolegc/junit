package uytube.views.usuarios;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Frame;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.Table;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

public class Buscar extends JPanel {
	private JTextField textField;
	private String [] nombreColumnas = {"Nombre","Apellido","Nickname","Correo","F.Nac"};
	private Usuario user;
	private JTable table;
	private JButton btnVolver;
	private JLabel lblBuscarUsuario;

	/**
	 * Create the panel.
	 */
	public Buscar() {
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Ingrese nickname");
		lblNewLabel.setBounds(10, 81, 100, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 100, 780, 20);
		add(textField);
		textField.setColumns(10);
		JButton btnEditarUsuario = new JButton("Editar usuario");
		btnEditarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar editar = new Editar(user);
				Frame.frame.setContentPane(editar);
				Frame.frame.revalidate();
				
			}
		});
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(400, 431, 390, 23);
		add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textField.getText());
				UsuarioController controller = new UsuarioController();
				user = controller.consultarUsuario(textField.getText());
				DefaultTableModel  model = new DefaultTableModel(nombreColumnas, 0);
				model.addRow(
						new Object[] {
								user.getNombre(),
								user.getApellido(),
								user.getNickname(),
								user.getCorreo(),
								user.getFnacimiento()
						}
				);
				table.setModel(model);
				add(btnEditarUsuario, "5, 10, fill, center");		

			}
		});
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 431, 368, 23);
		add(btnVolver);
		
		table = new JTable();
		table.setBounds(11, 120, 671, 0);
		add(table);
		
		lblBuscarUsuario = new JLabel("BUSCAR USUARIO");
		lblBuscarUsuario.setBounds(10, 51, 191, 14);
		add(lblBuscarUsuario);
		
	}
}
