package uytube.views.categorias;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.CategoriaController.CategoriaController;
import uytube.VideoController.VideoController;
import uytube.models.Categoria;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.usuarios.editar.editarMain;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import uytube.views.Frame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.hibernate.mapping.Table;
public class consulta extends JPanel {

	//Ventana principal
	private JFrame frame;
	//para poder volver al menu principal de categorias
	private main miMain;
	private JTable table;
	
	private String nombreColumna [] = {"Nombre"};
	private Categoria categoria;
	
	ArrayList<Categoria> categorias;
	//paso el frame principal por parametro	
	public consulta() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 780, 313);
		add(scrollPane);
		
		//Creo el controlador para poder llamar al listarCategorias()
		CategoriaController controlador = new CategoriaController();
		
		//Creo un obj arrayList y le cargo el listar categorias
		categorias = controlador.listarCategorias();
		
		//Creo la tabla y le paso los nombres de la categoria y un valor default
		DefaultTableModel tableModel = new DefaultTableModel(nombreColumna,0);
		table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//guardo en categoria lo seleccionado por el usuario para editar
				categoria = categorias.get(e.getFirstIndex());
			}
		});
		
		for(Categoria c:categorias) {
			tableModel.addRow(new Object[] {
					c.getNombre()}
			);
		}
		
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setBounds(10, 431, 368, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Frame.frame.setContentPane(miMain);
				Frame.frame.validate();
			}
			
		});
		add(btnNewButton);
		
		JButton btnEditar = new JButton("Mostrar");
		btnEditar.setBounds(400, 431, 390, 23);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VideoController videoController = new VideoController();							
				consultaMostrar muestro = new consultaMostrar(categoria);
				Frame.frame.setContentPane(muestro);
				Frame.frame.revalidate();
			}
		});
		add(btnEditar);
		//lo cargo en miMain para poder ir y volver (navegabilidad)
		miMain = new main();
	}
}
