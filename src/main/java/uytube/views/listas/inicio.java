package uytube.views.listas;

import javax.swing.JPanel;

import uytube.views.Frame;
import uytube.views.Inicio;
import uytube.views.videos.VideoMain;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inicio extends JPanel {

	/**
	 * Create the panel.
	 */
	public inicio() {
		setLayout(null);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crear listas = new crear();
				Frame.frame.setContentPane(listas);
				Frame.frame.validate();
			}
		});
		
		btnCrear.setBounds(30, 41, 114, 25);
		add(btnCrear);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(296, 240, 114, 25);
		add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar mod = new modificar();
				Frame.frame.setContentPane(mod);
				Frame.frame.revalidate();
			}
		});
		btnModificar.setBounds(30, 90, 114, 25);
		add(btnModificar);

	}
}
