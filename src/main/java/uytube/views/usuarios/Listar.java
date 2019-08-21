package uytube.views.usuarios;

import java.util.List;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.UsuarioController.UsuarioController;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import uytube.models.Usuario;
import javax.swing.JButton;
public class Listar extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	private String [] nombreColumnas = {"Nombre","Apellido","Nickname","Correo","F.Nac"};
	private String [][] datos ;
	
	public Listar() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 2, fill, fill");
		UsuarioController controller = new UsuarioController();
		List<Usuario> usuarios = controller.listaUsuarios();
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
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
		

	}

}
