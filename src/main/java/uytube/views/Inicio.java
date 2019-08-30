package uytube.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import uytube.views.categorias.main;
import uytube.views.listas.inicio;
import uytube.views.usuarios.UserMain;
import uytube.views.videos.VideoMain;

public class Inicio extends JPanel {

	/**
	 * Create the panel.
	 */
	JFrame frame;
	public Inicio() {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMain main = new UserMain();
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();
				
			}
		});
		add(btnUsuarios, "4, 6");
		
		JButton btnVideos = new JButton("Videos");
		btnVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VideoMain videos = new VideoMain();
				Frame.frame.setContentPane(videos);
				Frame.frame.validate();
			}
		});
		add(btnVideos, "4, 8");
		
		JButton btnCategorias = new JButton("Categorias");
		btnCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main categorias = new main();
				Frame.frame.setContentPane(categorias);
				Frame.frame.validate();				
			}
		});
		add(btnCategorias, "4, 10");
		
		JButton btnListas = new JButton("Listas");
		btnListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				inicio init = new inicio();
				Frame.frame.setContentPane(init);
				Frame.frame.validate();
				
			}
		});
		add(btnListas, "4, 12");
	}

}
