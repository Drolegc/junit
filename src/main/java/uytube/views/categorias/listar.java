package uytube.views.categorias;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.CategoriaController.CategoriaController;
import uytube.models.Categoria;
import uytube.views.Frame;
import uytube.views.usuarios.Editar;

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
public class listar extends JPanel {

	//Ventana principal
	private JFrame frame;
	//para poder volver al menu principal de categorias
	private main miMain;
	private JTable table;
	private String nombreColumna [] = {"Nombre"};
	private Categoria categoria;
	ArrayList<Categoria> categorias;
	//paso el frame principal por parametro	
	public listar() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("77px"),
				ColumnSpec.decode("198px"),
				ColumnSpec.decode("117px"),},
			new RowSpec[] {
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("247px"),
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("25px"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 2, 3, 1, fill, fill");
		
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Frame.frame.setContentPane(miMain);
				Frame.frame.validate();
			}
			
		});
		add(btnNewButton, "2, 4, left, top");
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//le cargo a edito la categoria a editar
				editar edito = new editar(categoria);
				//envio la categoria a editar
				Frame.frame.setContentPane(edito);
				Frame.frame.revalidate();
			}
		});
		add(btnEditar, "4, 4, fill, top");
		//lo cargo en miMain para poder ir y volver (navegabilidad)
		miMain = new main();
	}
}
