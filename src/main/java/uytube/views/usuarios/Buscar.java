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

	/**
	 * Create the panel.
	 */
	public Buscar() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("1px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("312px:grow"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("108px"),},
			new RowSpec[] {
				RowSpec.decode("1px"),
				RowSpec.decode("25px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("13px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("21px"),
				RowSpec.decode("200px:grow"),
				RowSpec.decode("21px"),}));
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
		JLabel lblNewLabel = new JLabel("Buscar por nickname");
		add(lblNewLabel, "3, 4, left, top");
		
		textField = new JTextField();
		add(textField, "3, 7, fill, center");
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
		add(btnBuscar, "5, 7, fill, center");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textField.getText());
				UsuarioController controller = new UsuarioController();
				ArrayList<Usuario> usuarios = controller.consultarUsuario(textField.getText());
				DefaultTableModel  model = new DefaultTableModel(nombreColumnas, 0);
				System.out.println(usuarios);
				for(Usuario u:usuarios) {
					System.out.println(u.getNombre());
					model.addRow(
							new Object[] {
									u.getNombre(),
									u.getApellido(),
									u.getNickname(),
									u.getCorreo(),
									u.getFnacimiento()
							}
					);
					user = new Usuario(
							u.getNickname(),
							u.getNombre(),
							u.getApellido(),
							u.getCorreo(),
							u.getFnacimiento(),
							u.getImg()
					);
				}
				table.setModel(model);
				if(usuarios.size()>0) {
					add(btnEditarUsuario, "5, 10, fill, center");		
					
				}else {
					remove(btnEditarUsuario);
				}
			}
		});
		
		table = new JTable();
		add(table, "3, 10, fill, top");
		
	}
}
