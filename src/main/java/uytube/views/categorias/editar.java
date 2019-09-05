package uytube.views.categorias;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.CategoriaController.CategoriaController;
import uytube.models.Categoria;
import uytube.views.Frame;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class editar extends JPanel {
	private JTextField txtNuevoNombreCategoria;

	/**
	 * Create the panel.
	 */
	public editar(Categoria catAeditar) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		txtNuevoNombreCategoria = new JTextField();
		txtNuevoNombreCategoria.setText("Nuevo Nombre Categoria");
		add(txtNuevoNombreCategoria, "6, 6, fill, default");
		txtNuevoNombreCategoria.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main miMain = new main();
				Frame.frame.setContentPane(miMain);
				Frame.frame.validate();
			}
		});
		add(btnVolver, "4, 8");
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Categoria nuevaCategoria = new Categoria();
				nuevaCategoria.setNombre(txtNuevoNombreCategoria.getText());
				nuevaCategoria.setId(catAeditar.getId());
				CategoriaController controlador = new CategoriaController();
				controlador.modificarCategoria(nuevaCategoria);
				listar lista = new listar();
				Frame.frame.setContentPane(lista);
				Frame.frame.revalidate();
			}
		});
		add(btnEditar, "6, 8");

	}

}
