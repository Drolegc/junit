package uytube.views.videos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;
import uytube.CategoriaController.CategoriaController;
import uytube.ComentarioController.ComentarioController;
import uytube.UsuarioController.UsuarioController;
import uytube.ValoracionController.ValoracionController;
import uytube.VideoController.VideoController;
import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.Comentario;
import uytube.models.Usuario;
import uytube.models.ValoracionVideo;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.Inicio;
import uytube.views.usuarios.Listar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class ConsultaVideoyComentarios extends JPanel {

	/**
	 * Create the panel.
	 * @param video 
	 */
	private JTextField userInfo;
	private JTextField titulo;
	private JTextField duracion;
	private JTextField url;
	private JTextField descrip;
	private VideoMain main;
	private Video videito;
	private String userInfoStr;
	private String catAsignar;
	private JTextField nickname;
	private JTextArea descripcion;
	private JTextField duracion1;
	private JTextField fechapublicacion;
	private JTextField categoria;
	private JTable table;
	
	
	public ConsultaVideoyComentarios(Video video) {
			setLayout(null);
			
			String [] ColumnasValoracion = {"Usuario", "Valoracion"};

			JLabel label = new JLabel("CONSULTA DE VIDEO");
			label.setBounds(10, 51, 191, 14);
			add(label);
			
			
			JLabel lblNickname = new JLabel("Usuario/nickname");
			lblNickname.setBounds(10, 76, 206, 32);
			add(lblNickname);
			
			JLabel lblUrl = new JLabel("URL");
			lblUrl.setBounds(10, 281, 206, 13);
			add(lblUrl);
			
			this.userInfo = new JTextField();
			userInfo.setBounds(10, 100, 359, 20);
			add(userInfo);
			this.userInfo.setColumns(10);
			this.userInfo.setText(video.getCanal().getNombre());
			this.url = new JTextField();
			url.setBounds(10, 299, 359, 20);
			this.url.setColumns(10);
			this.url.setText(video.getUrl());
			add(url);
			
			JLabel lblNombre = new JLabel("Titulo");
			lblNombre.setBounds(10, 131, 206, 14);
			add(lblNombre);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 192, 359, 2);
			add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setOrientation(SwingConstants.VERTICAL);
			separator_1.setBounds(367, 192, 2, 78);
			add(separator_1);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setOrientation(SwingConstants.VERTICAL);
			separator_2.setBounds(10, 192, 2, 78);
			add(separator_2);
			
			JSeparator separator_3 = new JSeparator();
			separator_3.setBounds(10, 268, 359, 2);
			add(separator_3);
			
			JLabel lblDescripcion = new JLabel("Descripcion");
			lblDescripcion.setBounds(9, 176, 207, 14);
			add(lblDescripcion);
			
			this.titulo = new JTextField();
			titulo.setBounds(10, 146, 359, 19);
			add(titulo);
			this.titulo.setColumns(10);
			this.titulo.setText(video.getNombre());
			
			this.descripcion = new JTextArea();
			descripcion.setBounds(10, 192, 359, 78);
			descripcion.setEnabled(false);
			descripcion.setEditable(false);
			descripcion.setWrapStyleWord(true);
			descripcion.setLineWrap(true);
			add(descripcion);
			this.descripcion.setText(video.getDescripcion());

			this.duracion = new JTextField();
			duracion.setBounds(10, 343, 174, 19);
			this.duracion.setColumns(10);
			this.duracion.setText(video.getDuracion());
			add(duracion);
			
			JLabel lblDuracion = new JLabel("Duracion");
			lblDuracion.setBounds(10, 330, 89, 13);
			add(lblDuracion);
			
			JLabel lblFechaPublicacion = new JLabel("Fecha publicacion");
			lblFechaPublicacion.setBounds(194, 330, 153, 13);
			add(lblFechaPublicacion);
			lblFechaPublicacion.setFocusable(false);

			
			
			this.fechapublicacion = new JTextField();
			fechapublicacion.setBounds(194, 343, 175, 19);
			this.fechapublicacion.setColumns(10);
			this.fechapublicacion.setText(video.getFecha().toString().substring(0,10));
			add(fechapublicacion);
			
			
			
			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(400, 431, 390, 23);
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Inicio inicio = new Inicio();
					Frame.frame.setContentPane(inicio);
					Frame.frame.validate();
				}
			});
			
			JLabel lblCategoria = new JLabel("Categoria");
			lblCategoria.setBounds(9, 373, 207, 14);
			add(lblCategoria);
			
			
			this.categoria = new JTextField();
			categoria.setBounds(10, 386, 174, 19);
			this.categoria.setColumns(10);
			this.categoria.setText(video.getCategoria().getNombre());
			add(categoria);
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("Es Publico");
			chckbxNewCheckBox.setBounds(194, 385, 98, 23);
			chckbxNewCheckBox.setSelected(video.getEs_publico());
			add(chckbxNewCheckBox);
			JCheckBox chckbxNewCheckBox2 = new JCheckBox("Es Privado");
			chckbxNewCheckBox2.setBounds(294, 385, 97, 23);
			chckbxNewCheckBox2.setSelected(!(video.getEs_publico()));
			add(chckbxNewCheckBox2);
			
			
			
			//add(categoriaAsig, "4, 16, fill, default");
			add(btnVolver);
			
			//Comentarios con Jtree-------------------------------------------------------------------
			ComentarioController ComControl = new ComentarioController();
			List<Comentario> Comentarios =  ComControl.listarComentarios(video.getNombre());			
			//String ComentarioText = coment.getComentario();
			//Comentarios con jtree
			DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Comentarios");
			DefaultTreeModel modelo = new DefaultTreeModel(raiz);
			
			for(Comentario coment:Comentarios) {
				DefaultMutableTreeNode coment1 = new DefaultMutableTreeNode(coment.getId()+">> " + coment.getFecha().toString().substring(0, 10)+" >> "+ coment.getUsuario().getNickname()+" >> " +coment.getComentario());
				modelo.insertNodeInto(coment1,raiz,0);
				
				
				List<Comentario> Respuestas = ComControl.ListarRespuestas(coment.getId());
				for(Comentario resp:Respuestas) {
					
					DefaultMutableTreeNode respuesta1 = new DefaultMutableTreeNode(resp.getId()+">> " + resp.getFecha().toString().substring(0, 10)+" >> "+ resp.getUsuario().getNickname()+" >> "+resp.getComentario());
					modelo.insertNodeInto(respuesta1,coment1,0);
				}
			}
			//contenedor con scroll
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(397, 100, 393, 170);
			add(scrollPane);
			
			JTree tree = new JTree(modelo);
			scrollPane.setViewportView(tree);
			
			JLabel lblComentarios = new JLabel("Comentarios");
			lblComentarios.setBounds(397, 82, 393, 20);
			add(lblComentarios);
			
			DefaultTableModel tablemodelValoracion = new DefaultTableModel(ColumnasValoracion, 0);
			ValoracionController controladorValoracion = new ValoracionController();
			List<ValoracionVideo> valoracionLista = controladorValoracion.listaValoracionesVideo(video.getId());
			
			for(ValoracionVideo vl: valoracionLista) {
				tablemodelValoracion.addRow(new Object[] {
						vl.getUsuario().getNickname(),
						vl.getValoracion()
					});
				}
			JScrollPane scrollPanelComentariosVideo = new JScrollPane();
			scrollPanelComentariosVideo.setRowHeaderView(table);
			scrollPanelComentariosVideo.setBounds(397, 299, 393, 106);
			add(scrollPanelComentariosVideo);
			table = new JTable();
			table.setModel(tablemodelValoracion);
			scrollPanelComentariosVideo.setViewportView(table);
			
			
			
			JLabel lblCalificacin = new JLabel("Calificaci\u00F3nes");
			lblCalificacin.setBounds(396, 280, 394, 14);
			add(lblCalificacin);
			for (int i = 0; i < tree.getRowCount(); i++) {
			    tree.expandRow(i);
			}
						// comentario con jtree
			
			
			
			
			
			
			
		}
}


	


