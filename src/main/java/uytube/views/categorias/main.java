package uytube.views.categorias;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.views.Frame;
import uytube.views.Inicio;
import uytube.views.usuarios.Alta;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class main extends JPanel {
 private Inicio inicio;
	/**
	 * Create the panel.
	 */
	public main() {
		inicio = new Inicio();
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton btnNewButton_1 = new JButton("Consultar Categorias");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				consulta con = new consulta();
				Frame.frame.setContentPane(con);
				Frame.frame.revalidate();
			}
		});
		
		JButton btnNewButton = new JButton("Alta Categoria");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alta alta = new alta();
				Frame.frame.setContentPane(alta);
				Frame.frame.revalidate();

			}
		});
		add(btnNewButton, "2, 8");
		add(btnNewButton_1, "2, 10");
		
		JButton btnListarCategorias = new JButton("Listar Categorias");
		btnListarCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listar lista = new listar();
				Frame.frame.setContentPane(lista);
				Frame.frame.revalidate();
			}
		});
		add(btnListarCategorias, "2, 12");
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Frame.frame.setContentPane(inicio);
				Frame.frame.validate();
			}
		});
		add(btnVolver, "2, 16");

	}

}
