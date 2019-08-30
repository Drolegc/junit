package uytube.views.usuarios.consultar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javassist.tools.framedump;
import uytube.ListaController.ListaController;
import uytube.UsuarioController.UsuarioController;
import uytube.VideoController.VideoController;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import uytube.models.Lista;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import uytube.views.usuarios.UserMain;
import uytube.ListaController.ListaController;
import uytube.VideoController.VideoController;
public class Consultar extends JPanel {
	private JTable tableVideos;
	private JTable tableCanales;

	/**
	 * Create the panel.
	 */
	private String []  columnasUsuarios = {"Nombre","Apellido","Nickname","Correo","F.Nac"};
	private String []  columnasCanales = {"Nombre", "Privado","Categoria"};
	private String []  columnasVideos = {"Nombre","Descripcion","Duracion","Fecha","Url"};
	private String [][] datos ;
	private JFrame frame;
	private Usuario user;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JScrollPane scrollPane_2;
	private JTable tableUsuarios;
	public Consultar(Usuario user) {
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();				
			}
		});
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("430px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("1px"),},
			new RowSpec[] {
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("66px:grow"),
				RowSpec.decode("24px"),
				RowSpec.decode("66px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("66px"),
				RowSpec.decode("27px"),
				RowSpec.decode("21px"),}));
		
		tableUsuarios = new JTable();
		add(tableUsuarios, "2, 2, fill, fill");
		add(btnVolver, "2, 8, center, top");
		System.out.println(user.getNombre());
		DefaultTableModel  modelUsuarios = new DefaultTableModel(columnasUsuarios, 0);
		DefaultTableModel  modelVideo = new DefaultTableModel(columnasVideos, 0);
		DefaultTableModel  modelCanales = new DefaultTableModel(columnasCanales, 0);

		tableVideos = new JTable();
		tableCanales = new JTable();
		tableCanales.setBounds(0, 0, 428, 1);
		ListaController listaController = new ListaController();
		List<Lista> listas = listaController.listarListas(user.getNickname());
		VideoController videoController = new VideoController();
		
		ArrayList<Video> videos = videoController.obtenerVideosUsuario(user.getNickname());
		modelUsuarios.addRow(
					new Object[] {
							user.getNombre(),
							user.getApellido(),
							user.getNickname(),
							user.getCorreo(),
							user.getFnacimiento()
					}
			);
		
		for (Video v:videos) {
			modelVideo.addRow(
					new Object[] {
							v.getNombre(),
							v.getDuracion(),
							v.getDescripcion(),
							v.getFecha(),
							v.getUrl()
					}
			);	
		}

		for(Lista l:listas) {
			modelCanales.addRow(
					new Object[] {
							l.getNombre(),
							l.getPrivado(),
					}
			);			
		}

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 4, fill, fill");

		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "2, 6, fill, fill");
		tableVideos.setModel(modelVideo);
		tableCanales.setModel(modelCanales);
		tableUsuarios.setModel(modelUsuarios);
		scrollPane_1.setViewportView(tableCanales);
		scrollPane.setViewportView(tableVideos);
		
		scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "4, 2, fill, fill");
		
	}
}
