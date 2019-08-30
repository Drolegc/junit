package uytube.views.videos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import uytube.VideoController.VideoController;
import uytube.views.Frame;
import uytube.views.Inicio;

import javax.swing.JFormattedTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ConsultaVideo extends JPanel {
	private JTextField textField;
	private VideoMain main;

	/**
	 * Create the panel.
	 */
	public ConsultaVideo() {
		main = new VideoMain();
		setLayout(null);
		
		JLabel lblTituloDeVideo = new JLabel("Titulo de video a consultar");
		lblTituloDeVideo.setBounds(55, 34, 183, 14);
		add(lblTituloDeVideo);
		
		textField = new JTextField();
		textField.setBounds(65, 59, 146, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Esperando para la busqueda ...");
		lblNewLabel.setBounds(65, 120, 343, 111);
		add(lblNewLabel);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
			}	
		});
		
		btnBuscar.setBounds(36, 254, 89, 23);
		add(btnBuscar);
		

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VideoMain inicio = new VideoMain();
				Frame.frame.setContentPane(inicio);
				Frame.frame.revalidate();
			}
		});
		
		btnVolver.setBounds(164, 254, 89, 23);
		add(btnVolver);
		
	

	}
}
