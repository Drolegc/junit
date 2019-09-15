package uytube.views.usuarios.editar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javassist.tools.framedump;
import uytube.UsuarioController.UsuarioController;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import uytube.models.Usuario;
import uytube.views.Frame;
import uytube.views.Inicio;
import uytube.views.usuarios.UserMain;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
public class ListadoEditar extends JPanel {
	private JTable table;

	
	private String [] nombreColumnas = {"Nombre","Apellido","Nickname","Correo","F.Nac"};
	private String [][] datos ;
	private JTable table_1;
	private JFrame frame;
	private Usuario user;
	public ListadoEditar() {
		setLayout(null);
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.validate();			
			}
		});
		btnVolver.setBounds(10, 431, 368, 23);
		add(btnVolver);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 780, 313);
		add(scrollPane);
		
		UsuarioController controller = new UsuarioController();
		ArrayList<Usuario> usuarios = controller.listaUsuarios();
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
		
		JButton btnEditarUsuario = new JButton("Editar usuario");
		btnEditarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.validate();

			}
		});
		btnEditarUsuario.setBounds(400, 431, 390, 23);
		add(btnEditarUsuario);
		
		JLabel lblEditarUsuario = new JLabel("EDITAR USUARIO");
		lblEditarUsuario.setBounds(10, 51, 124, 14);
		add(lblEditarUsuario);
		
		JLabel lblSeleccioneElUsuario = new JLabel("Seleccione el usuario a editar:");
		lblSeleccioneElUsuario.setBounds(10, 85, 220, 14);
		add(lblSeleccioneElUsuario);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				user = usuarios.get(e.getFirstIndex());
			}
		});
		
		

	}
}
