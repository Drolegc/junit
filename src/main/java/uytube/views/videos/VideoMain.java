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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VideoMain extends JPanel {

	/**
	 * Create the panel.
	 */
	JFrame frame;
	public VideoMain() {
		
		JButton btnNewButton1 = new JButton("AltaVideo");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaVideo altaV = new AltaVideo();
				Frame.frame.setContentPane(altaV);
				Frame.frame.revalidate();
			}
		});
		
		JButton btnConsultaVideo = new JButton("Consulta Video");
		btnConsultaVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaVideo consultaV = new ConsultaVideo();
				Frame.frame.setContentPane(consultaV);
				Frame.frame.revalidate();
			}
		});
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				Frame.frame.setContentPane(inicio);
				Frame.frame.revalidate();
			}
		});
		
		JButton btnValorarVideo = new JButton("Valorar Video");
		btnValorarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValorarVideo valorov = new ValorarVideo();
				Frame.frame.setContentPane(valorov);
				Frame.frame.revalidate();
			}
		});
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("55px"),
				ColumnSpec.decode("193px"),},
			new RowSpec[] {
				RowSpec.decode("38px"),
				RowSpec.decode("23px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				RowSpec.decode("23px"),
				RowSpec.decode("25px"),
				RowSpec.decode("110px"),
				RowSpec.decode("23px"),}));
		add(btnNewButton1, "2, 2, left, fill");
		add(btnConsultaVideo, "2, 4, left, fill");
		add(btnVolver, "2, 8, right, fill");
		add(btnValorarVideo, "2, 6, left, top");
		
	}
}
