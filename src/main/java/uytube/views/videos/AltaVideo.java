package uytube.views.videos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import uytube.VideoController.VideoController;
import uytube.models.Video;
import uytube.models.manager.Manager;
import uytube.views.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class AltaVideo extends JPanel {
	private JTextField userInfo;
	private JTextField titulo;
	private JTextField duracion;
	private JTextField url;
	private JTextField descrip;
	private VideoMain main;

	/**
	 * Create the panel.
	 */
	private Manager mana;

	public AltaVideo() {
		main = new VideoMain();
		setLayout(null);
		
		JLabel TITULOPANEL = new JLabel("ALTA VIDEO");
		TITULOPANEL.setBounds(176, 11, 87, 14);
		add(TITULOPANEL);
		
		JLabel lblUsuarioCreador = new JLabel("Usuario creador");
		lblUsuarioCreador.setBounds(30, 48, 89, 14);
		add(lblUsuarioCreador);
		
		userInfo = new JTextField();
		userInfo.setBounds(23, 73, 96, 20);
		add(userInfo);
		userInfo.setColumns(10);
		
		JLabel lblTituloVideo = new JLabel("Titulo Video");
		lblTituloVideo.setBounds(30, 118, 89, 14);
		add(lblTituloVideo);
		
		titulo = new JTextField();
		titulo.setBounds(23, 144, 96, 20);
		add(titulo);
		titulo.setColumns(10);
		
		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setBounds(30, 192, 48, 14);
		add(lblDuracion);
		
		duracion = new JTextField();
		duracion.setBounds(23, 217, 96, 20);
		add(duracion);
		duracion.setColumns(10);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(297, 48, 48, 14);
		add(lblUrl);
		
		url = new JTextField();
		url.setBounds(275, 73, 96, 20);
		add(url);
		url.setColumns(10);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setBounds(275, 118, 96, 14);
		add(descripcion);
		
		descrip = new JTextField();
		descrip.setBounds(275, 144, 96, 20);
		add(descrip);
		descrip.setColumns(10);
		
		JLabel lblFechaPublicacion = new JLabel("Fecha Publicacion");
		lblFechaPublicacion.setBounds(275, 192, 96, 14);
		add(lblFechaPublicacion);
		
		JDateChooser fecPub = new JDateChooser();
		fecPub.setBounds(276, 217, 95, 20);
		add(fecPub);
		
		JButton btnAgregarCategoria = new JButton("Agregar Categoria");
		btnAgregarCategoria.setBounds(275, 254, 145, 23);
		add(btnAgregarCategoria);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
						
				Video videito = new Video();
				
				videito.setNombre(titulo.getText());
				videito.setDuracion(duracion.getText());
				videito.setDescripcion(descripcion.getText());
				videito.setUrl(url.getText());
				videito.setFecha(fecPub.getDate());
				
				VideoController controladorVideo = new VideoController();
				controladorVideo.altaVideo(videito, userInfo.getText());

				JOptionPane.showMessageDialog(null, "Video dado de alta correctamente");
				
				Frame.frame.setContentPane(main);
				Frame.frame.revalidate();
			}
		});
		btnOk.setBounds(46, 254, 89, 23);
		add(btnOk);
		

	}
}
