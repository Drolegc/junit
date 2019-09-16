package uytube.views.categorias;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.CategoriaController.CategoriaController;
import uytube.models.Categoria;
import uytube.views.Frame;
import uytube.views.Inicio;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class editar extends JPanel {
	private JTextField txtNuevoNombreCategoria;

	/**
	 * Create the panel.
	 */
	public editar(Categoria catAeditar) {
		setLayout(null);
		
		txtNuevoNombreCategoria = new JTextField();
		txtNuevoNombreCategoria.setBounds(10, 100, 350, 20);
		txtNuevoNombreCategoria.setText("Nuevo Nombre Categoria");
		add(txtNuevoNombreCategoria);
		txtNuevoNombreCategoria.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 431, 368, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inicio miMain = new Inicio();
				Frame.frame.setContentPane(miMain);
				Frame.frame.validate();
			}
		});
		add(btnVolver);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(400, 431, 390, 23);
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
		add(btnEditar);
		
		JLabel lblNewLabel = new JLabel("ALTA DE CATEGORIA");
		lblNewLabel.setBounds(10, 51, 137, 14);
		add(lblNewLabel);

	}

}
