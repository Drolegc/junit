package uytube.views.usuarios.consultar;

import java.util.List;

import javax.swing.JPanel;

import uytube.UsuarioController.UsuarioController;
import uytube.models.Canal;
import uytube.models.Usuario;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Seguidores extends JPanel {
	private JTable table;
	private JTable table_1;
	private String []  columnasSeguidores = {"Nickname"};
	private String []  columnasSeguidos = {"Nickname"};

	/**
	 * Create the panel.
	 */
	public Seguidores(Usuario user) {
		setLayout(null);
		UsuarioController controller = new UsuarioController();
		DefaultTableModel  modelSeguidores = new DefaultTableModel(columnasSeguidores, 0);
		DefaultTableModel  modelSeguidos = new DefaultTableModel(columnasSeguidos, 0);
		List<Canal> seguidos = controller.listCanalesSeguidos(user.getNickname());
		List<Usuario> seguidores = controller.listUsuariosSeguidores(user.getNickname());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 23, 162, 239);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(262, 23, 162, 239);
		add(scrollPane_1);
		
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

	}
}
