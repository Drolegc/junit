package uytube.views.videos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uytube.views.Frame;
import uytube.views.Inicio;

public class VideoMain extends JPanel {

	/**
	 * Create the panel.
	 */
	JFrame frame;
	public VideoMain() {
		
		JButton btnNewButton1 = new JButton("AltaVideo");
		btnNewButton1.setBounds(55, 38, 107, 23);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaVideo altaV = new AltaVideo();
				Frame.frame.setContentPane(altaV);
				Frame.frame.revalidate();
			}
		});
		
		JButton btnConsultaVideo = new JButton("Consulta Video");
		btnConsultaVideo.setBounds(55, 72, 107, 23);
		btnConsultaVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaVideo consultaV = new ConsultaVideo();
				Frame.frame.setContentPane(consultaV);
				Frame.frame.revalidate();
			}
		});
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(185, 253, 63, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.revalidate();
			}
		});
		
		JButton btnValorarVideo = new JButton("Valorar Video");
		btnValorarVideo.setBounds(55, 106, 107, 23);
		btnValorarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValorarVideo valorov = new ValorarVideo();
				Frame.frame.setContentPane(valorov);
				Frame.frame.revalidate();
			}
		});
		setLayout(null);
		add(btnNewButton1);
		add(btnConsultaVideo);
		add(btnVolver);
		add(btnValorarVideo);
		
		JButton btnModificarVideo = new JButton("Modificar Video ");
		btnModificarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDatosVideo ModifVideo = new ModificarDatosVideo();
				Frame.frame.setContentPane(ModifVideo);
				Frame.frame.revalidate();
			}
		});
		btnModificarVideo.setBounds(55, 140, 107, 23);
		add(btnModificarVideo);
		
	}
}
