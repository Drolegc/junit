package uytube.views.videos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import uytube.VideoController.VideoController;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class ListarVideosUsuario extends JPanel {
		
	private String [] nombreColumnas = {"Nombre","Categoria","Fecha","URL"};
    private Video videoSeleccionado= new Video();
  
	/**
	 * Create the panel.
	 */
	ListarVideosUsuario(Usuario usuario) {
		setLayout(null);
	    videoSeleccionado.setNombre("");//setea el video seleccionado a vacio

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 780, 313);
		add(scrollPane);
		
		
		VideoController controller = new VideoController();
		ArrayList<Video> Videos = controller.listaVideosUsuario(usuario.getNickname());
		DefaultTableModel  tablemodel = new DefaultTableModel(nombreColumnas, 0);
		JTable table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//guardo en categoria lo seleccionado por el usuario para editar
				videoSeleccionado = Videos.get(e.getFirstIndex());
				
			}
		});
		for(Video v:Videos) {
			tablemodel.addRow(
					new Object[] {
							v.getNombre(),
							v.getCategoria().getNombre(),
							v.getFecha().toString().subSequence(0,10),
							v.getUrl(),
							
					}
			);
		}
		
		table.setModel(tablemodel);
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDatosVideo ModifVideo = new ModificarDatosVideo();
				Frame.frame.setContentPane(ModifVideo);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(10, 431, 368, 23);
		add(btnVolver);
		
		JLabel lblEditarVideo = new JLabel("Editar video");
		lblEditarVideo.setBounds(10, 157, 79, 14);
		add(lblEditarVideo);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//corrobora si selecciono un video;
				if (!videoSeleccionado.getNombre().isEmpty()) {
						VideoController videocontroller = new VideoController();			
						
						EditarDatosVideo editarDV = new EditarDatosVideo(videoSeleccionado);
						Frame.frame.setContentPane(editarDV);
						Frame.frame.revalidate();
						}
				else
						{JOptionPane.showMessageDialog(null, "Debe seleccionar un video");
						}	
				
				
			}
		});
		btnEditar.setBounds(400, 431, 390, 23);
		add(btnEditar);
		
		
		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("nodo");
		DefaultTreeModel model = new DefaultTreeModel(nodo);
		JTree tree = new JTree(model);
		tree.setBounds(46, 214, 72, 64);
		
		add(tree);
		
		JLabel lblSeleccioneUnUn = new JLabel("Seleccione un un video para editar");
		lblSeleccioneUnUn.setBounds(10, 80, 224, 14);
		add(lblSeleccioneUnUn);
		
		JLabel label = new JLabel("EDITAR VIDEO");
		label.setBounds(10, 51, 119, 14);
		add(label);

	}
}
