package uytube.views.usuarios.editar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javassist.tools.framedump;
import uytube.CanalController.CanalController;
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

import uytube.models.Canal;
import uytube.models.Lista;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.Inicio;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import uytube.views.usuarios.UserMain;
import uytube.ListaController.ListaController;
import uytube.VideoController.VideoController;
import javax.swing.JTextArea;
public class infoVideosListas extends JPanel {
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
	ArrayList<Video> videos;
	List<Lista> listas;
	public infoVideosListas(Usuario user) {
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(263, 279, 85, 21);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.validate();			
			}
		});
		setLayout(null);
		add(btnVolver);
		DefaultTableModel  modelVideo = new DefaultTableModel(columnasVideos, 0);
		DefaultTableModel  modelListas = new DefaultTableModel(columnasCanales, 0);

		tableVideos = new JTable();
		tableListas = new JTable();
		tableListas.setBounds(0, 0, 428, 1);
		ListaController listaController = new ListaController();
		listas = listaController.listarListas(user.getNickname());
		VideoController videoController = new VideoController();
		
		videos = videoController.obtenerVideosUsuario(user.getNickname());
		
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
							(l.getPrivado())?"Si":"No",
							l.getCategoria().getNombre()
					}
			);			
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 433, 93);
		add(scrollPane);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 159, 433, 93);
		add(scrollPane_1);
		tableVideos.setModel(modelVideo);
		tableListas.setModel(modelListas);
		scrollPane_1.setViewportView(tableListas);
		scrollPane.setViewportView(tableVideos);
		
		JButton btnVerLista = new JButton("Editar lista");
		btnVerLista.setBounds(358, 279, 85, 21);
		add(btnVerLista);
		btnVerLista.setVisible(false);
		btnVerLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarLista editarlista = new editarLista(lista, user);
				Frame.frame.setContentPane(editarlista);
				Frame.frame.revalidate();

			}
		});
		JButton btnVerVideo = new JButton("Editar video");
		btnVerVideo.setBounds(358, 279, 85, 21);
		btnVerVideo.setVisible(false);
		add(btnVerVideo);
		CanalController canalCont = new CanalController();
		Canal canal = canalCont.obtenerCanalUsuario(user.getNickname());
		
		JLabel lblListas = new JLabel("Listas");
		lblListas.setBounds(10, 136, 46, 13);
		add(lblListas);
		
		JLabel lblVideos = new JLabel("Videos");
		lblVideos.setBounds(10, 10, 46, 13);
		add(lblVideos);
		btnVerVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarVideo editarvideo = new editarVideo(video, user);
				Frame.frame.setContentPane(editarvideo);
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
