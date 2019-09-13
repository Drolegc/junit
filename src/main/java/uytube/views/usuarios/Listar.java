package uytube.views.usuarios;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Listar extends JPanel {
	private JTable table;

	
	private String [] nombreColumnas = {"Nombre","Apellido","Nickname","Correo","F.Nac"};
	private String [][] datos ;
	private JTable table_1;
	private JFrame frame;
	private Usuario user;
	public Listar() {
		setLayout(null);
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();				
			}
		});
		btnVolver.setBounds(355, 269, 85, 21);
		add(btnVolver);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 430, 230);
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
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				user = usuarios.get(e.getFirstIndex());
			}
		});
		
		

	}
}
