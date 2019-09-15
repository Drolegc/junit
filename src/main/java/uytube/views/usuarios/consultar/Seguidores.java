package uytube.views.usuarios.consultar;

import java.util.List;

import javax.swing.JPanel;

import uytube.UsuarioController.UsuarioController;
import uytube.models.Canal;
import uytube.models.Usuario;
import uytube.views.Frame;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Seguidores extends JPanel {
	private JTable table;
	private JTable table_1;
	private String []  columnasSeguidores = {"Seguidores"};
	private String []  columnasSeguidos = {"Seguidos"};

	/**
	 * Create the panel.
	 */
	public Seguidores(Usuario user) {
		UsuarioController controller = new UsuarioController();
		DefaultTableModel  modelSeguidores = new DefaultTableModel(columnasSeguidores, 0);
		DefaultTableModel  modelSeguidos = new DefaultTableModel(columnasSeguidos, 0);
		List<Canal> seguidos = controller.listCanalesSeguidos(user.getNickname());
		List<Usuario> seguidores = controller.listUsuariosSeguidores(user.getNickname());
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("27px"),
				ColumnSpec.decode("162px"),
				ColumnSpec.decode("73px"),
				ColumnSpec.decode("162px"),},
			new RowSpec[] {
				RowSpec.decode("23px"),
				RowSpec.decode("239px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("21px"),}));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 2, fill, fill");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "4, 2, fill, fill");
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);



		for (Usuario u:seguidores) {
			modelSeguidores.addRow(
				new Object[] {
					u.getNickname(),
				}
			);			
		}
		for (Canal c: seguidos) {
			modelSeguidos.addRow(
				new Object[] {
					c.getNombre(),
				}
			);			
		}
		this.table.setModel(modelSeguidos);
		this.table_1.setModel(modelSeguidores);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultar consultar = new Consultar(user);
				Frame.frame.setContentPane(consultar);
				Frame.frame.revalidate();				
			}
		});
		add(btnVolver, "4, 4, right, top");

	}
}
