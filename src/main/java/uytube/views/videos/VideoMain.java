package uytube.views.videos;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import uytube.views.Frame;
import uytube.views.Inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class VideoMain extends JPanel {

	/**
	 * Create the panel.
	 */
	JFrame frame;
	public VideoMain() {
		setLayout(null);
		
		JButton btnNewButton1 = new JButton("AltaVideo");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaVideo altaV = new AltaVideo();
				Frame.frame.setContentPane(altaV);
				Frame.frame.revalidate();
			}
		});
		btnNewButton1.setBounds(55, 36, 107, 23);
		add(btnNewButton1);
		
		JButton btnConsultaVideo = new JButton("Consulta Video");
		btnConsultaVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaVideo consultaV = new ConsultaVideo();
				Frame.frame.setContentPane(consultaV);
				Frame.frame.revalidate();
			}
		});
		btnConsultaVideo.setBounds(55, 73, 107, 23);
		add(btnConsultaVideo);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.revalidate();
			}
		});
		btnVolver.setBounds(159, 254, 89, 23);
		add(btnVolver);
		
	}
}
