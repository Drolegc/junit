package uytube.views.videos;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import uytube.views.Frame;
import uytube.views.Inicio;

import com.jgoodies.forms.layout.FormSpecs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ValorarVideo extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ValorarVideo() {
		
		setLayout(null);
		
		JComboBox selectUser = new JComboBox();
		selectUser.setBounds(24, 33, 182, 24);
		add(selectUser);
		
		JLabel labelUsuario = new JLabel("Seleccionar Usuario");
		labelUsuario.setBounds(24, 11, 182, 15);
		add(labelUsuario);
		
		table = new JTable();
		table.setBounds(417, 68, -386, 134);
		add(table);
		
		JLabel lblElVideoElegido = new JLabel("El video elegido es:");
		lblElVideoElegido.setBounds(42, 234, 182, 15);
		add(lblElVideoElegido);
		
		JLabel infoVideo = new JLabel("....");
		infoVideo.setBounds(73, 261, 66, 15);
		add(infoVideo);
		
		JButton btnLIKE = new JButton("LIKE");
		btnLIKE.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
			VideoMain inicio = new VideoMain();
			Frame.frame.setContentPane(inicio);
			Frame.frame.revalidate();
		}
		});
		
		btnLIKE.setBounds(83, 327, 114, 25);
		add(btnLIKE);
		
		JButton btnDislike = new JButton("DISLIKE");
		btnDislike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				VideoMain inicio = new VideoMain();
				Frame.frame.setContentPane(inicio);
				Frame.frame.revalidate();
			}
		});
		btnDislike.setBounds(261, 327, 114, 25);
		add(btnDislike);

	}
}
