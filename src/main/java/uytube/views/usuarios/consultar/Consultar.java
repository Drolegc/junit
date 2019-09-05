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
	private JTable tableListas;

	/**
	 * Create the panel.
	 */
	private String []  columnasUsuarios = {"Nombre","Apellido","Nickname","Correo","F.Nac"};
	private String []  columnasCanales = {"Nombre", "Privado","Categoria"};
	private String []  columnasVideos = {"Nombre","Descripcion","Duracion","Fecha","Url"};
	private String [][] datos ;
	private JFrame frame;
	private Usuario user;
	private Video video;
	private Lista lista;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JScrollPane scrollPane_2;
	private JTable tableUsuarios;
	ArrayList<Video> videos;
	List<Lista> listas;
	public Consultar(Usuario user) {
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(263, 279, 85, 21);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();				
			}
		});
		setLayout(null);
		
		tableUsuarios = new JTable();
		tableUsuarios.setBounds(10, 10, 433, 76);
		add(tableUsuarios);
		add(btnVolver);
		System.out.println(user.getNombre());
		DefaultTableModel  modelUsuarios = new DefaultTableModel(columnasUsuarios, 0);
		DefaultTableModel  modelVideo = new DefaultTableModel(columnasVideos, 0);
		DefaultTableModel  modelListas = new DefaultTableModel(columnasCanales, 0);

		tableVideos = new JTable();
		tableListas = new JTable();
		tableListas.setBounds(0, 0, 428, 1);
		ListaController listaController = new ListaController();
		listas = listaController.listarListas(user.getNickname());
		VideoController videoController = new VideoController();
		
		videos = videoController.obtenerVideosUsuario(user.getNickname());
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
			modelListas.addRow(
					new Object[] {
							l.getNombre(),
							l.getPrivado(),
					}
			);			
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 433, 66);
		add(scrollPane);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 186, 433, 66);
		add(scrollPane_1);
		tableVideos.setModel(modelVideo);
		tableListas.setModel(modelListas);
		tableUsuarios.setModel(modelUsuarios);
		scrollPane_1.setViewportView(tableListas);
		scrollPane.setViewportView(tableVideos);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(449, 10, 1, 76);
		add(scrollPane_2);
		
		JButton btnVerLista = new JButton("Ver lista");
		btnVerLista.setBounds(358, 279, 85, 21);
		add(btnVerLista);
		btnVerLista.setVisible(false);
		btnVerLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verLista verlista = new verLista(lista);
				Frame.frame.setContentPane(verlista);
				Frame.frame.revalidate();

			}
		});
		JButton btnVerVideo = new JButton("Ver video");
		btnVerVideo.setBounds(358, 279, 85, 21);
		btnVerVideo.setVisible(false);
		add(btnVerVideo);
		btnVerVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verVideo vervideo = new verVideo(video);
				Frame.frame.setContentPane(vervideo);
				Frame.frame.revalidate();

			}
		});
		tableVideos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				video = videos.get(e.getFirstIndex());
				System.out.println(video);
				btnVerLista.setVisible(false);
				btnVerVideo.setVisible(true);

			}
		});
		tableListas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				lista = listas.get(e.getFirstIndex());
				System.out.println(lista);
				btnVerLista.setVisible(true);
				btnVerVideo.setVisible(false);
			}
		});		
	}
}
