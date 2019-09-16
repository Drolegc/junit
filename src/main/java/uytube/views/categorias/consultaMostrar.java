package uytube.views.categorias;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.ListaController.ListaController;
import uytube.VideoController.VideoController;
import uytube.models.Categoria;
import uytube.models.Lista;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.Inicio;
import uytube.views.usuarios.UserMain;
import uytube.views.usuarios.editar.editarMain;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.hibernate.mapping.Table;
import javax.swing.JLabel;

public class consultaMostrar extends JPanel {
	private JTable table; 
	private String [] columnaVideo = {"nombre","canal"};
	private String [][] datos;
	private String [] columnaLista = {"nombre_lista"};
	private JTable table_1;
	
	public consultaMostrar(Categoria categoria) {
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(400, 431, 368, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inicio main = new Inicio();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();	
			}
		});
		setLayout(null);
		add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 368, 313);
		add(scrollPane);
		
		VideoController videoControlador = new VideoController();
		ArrayList<Video> videos = videoControlador.videoPorCategoria(categoria);
		DefaultTableModel tableModel = new DefaultTableModel(columnaVideo,0);
		table = new JTable();
		for(Video v: videos) {
			tableModel.addRow(
					new Object[] {
							v.getNombre(),
							v.getCanal().getNombre(),
					}
							
				);
		}
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(400, 100, 368, 313);
		add(scrollPane_1);
		
		ListaController listaControlador = new ListaController();		
		ArrayList<Lista> listados = listaControlador.ListaPorCategoria(categoria);
		DefaultTableModel tableModel1 = new DefaultTableModel(columnaLista,0);
		table_1 = new JTable();
		for(Lista l: listados) {
			tableModel1.addRow(
					new Object[] {
							l.getNombre()
						}
					);
		}
		table_1.setModel(tableModel1);
		scrollPane_1.setViewportView(table_1);		
		
		JLabel lblVideosDeLa = new JLabel("Videos de la categoria");
		lblVideosDeLa.setBounds(10, 75, 207, 14);
		add(lblVideosDeLa);
		
		JLabel lblListasDeLa = new JLabel("Listas de la  categoria");
		lblListasDeLa.setBounds(405, 75, 170, 14);
		add(lblListasDeLa);
		
		JLabel lblConsultaDeCategoria = new JLabel("CONSULTA DE CATEGORIA");
		lblConsultaDeCategoria.setBounds(10, 41, 251, 14);
		add(lblConsultaDeCategoria);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
						
			}
		});
			

	}
}
