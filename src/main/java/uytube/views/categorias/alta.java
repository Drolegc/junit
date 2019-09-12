package uytube.views.categorias;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uytube.CategoriaController.CategoriaController;
import uytube.views.usuarios.UserMain;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import uytube.views.Frame;
public class alta extends JPanel {

	private String nombre;
	private JFrame frame;
	private main miMain;
	private JTextField txtIngresarCategoria;
	
	
	public alta() {
			miMain = new main();
			
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boolean resultado;
					CategoriaController categoria = new CategoriaController();
					resultado = categoria.altaCategoria(txtIngresarCategoria.getText());
					if(resultado == false) {
						JOptionPane.showMessageDialog(null, "La Categoria: "+txtIngresarCategoria.getText()+" fue agregada con exito.");
					}
					else {
						JOptionPane.showMessageDialog(null, "La Categoria: "+txtIngresarCategoria.getText()+" ya existe");
					}
					
					
				}
			});
			setLayout(new FormLayout(new ColumnSpec[] {
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("172px"),
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("172px"),},
				new RowSpec[] {
					RowSpec.decode("98px"),
					RowSpec.decode("32px"),
					FormSpecs.RELATED_GAP_ROWSPEC,
					RowSpec.decode("25px"),}));
			
			txtIngresarCategoria = new JTextField();
			txtIngresarCategoria.setText("Ingresar Categoria");
			add(txtIngresarCategoria, "2, 2, fill, fill");
			txtIngresarCategoria.setColumns(10);
			add(btnAgregar, "2, 4, fill, top");
			
			JButton btnNewButton = new JButton("Volver");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Frame.frame.setContentPane(miMain);
					Frame.frame.validate();
				}
			});
			add(btnNewButton, "4, 4, fill, top");
	}

}
