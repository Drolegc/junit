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
			btnAgregar.setBounds(400, 431, 390, 23);
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
			setLayout(null);
			
			txtIngresarCategoria = new JTextField();
			txtIngresarCategoria.setBounds(10, 100, 368, 23);
			txtIngresarCategoria.setText("Ingresar Categoria");
			add(txtIngresarCategoria);
			txtIngresarCategoria.setColumns(10);
			add(btnAgregar);
			
			JButton btnNewButton = new JButton("Volver");
			btnNewButton.setBounds(10, 431, 368, 23);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Frame.frame.setContentPane(miMain);
					Frame.frame.validate();
				}
			});
			add(btnNewButton);
	}

}
