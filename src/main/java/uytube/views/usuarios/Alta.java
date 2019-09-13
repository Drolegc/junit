package uytube.views.usuarios;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import resources.files.JFilePicker;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Frame;
import uytube.views.Inicio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FilenameUtils;

import java.awt.Window;
import uytube.views.usuarios.UserMain;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.xml.bind.v2.schemagen.xmlschema.Import;
import java.awt.Color;
public class Alta extends JPanel {

	private JTextField nickname;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField img;
	private JTextField correo;
	private JDateChooser f_nac;
	private UserMain Main;
	private JFilePicker filePicker;
	private Boolean nicknameTaken;
	public Alta() {
		Main = new UserMain();
		setLayout(null);

		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(10, 87, 148, 13);
		add(lblNickname);
		
		nombre = new JTextField();
		nombre.setBounds(10, 172, 368, 23);
		add(nombre);
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setBounds(10, 241, 368, 23);
		apellido.setColumns(10);
		add(apellido);
				
		correo = new JTextField();
		correo.setBounds(400, 100, 390, 23);
		correo.setColumns(10);
		add(correo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 148, 368, 13);
		add(lblNombre);
		
		JLabel label = new JLabel("Apellido");
		label.setBounds(10, 217, 360, 13);
		add(label);
		
		JLabel label_2 = new JLabel("Correo");
		label_2.setBounds(400, 87, 177, 13);
		add(label_2);
		
		JButton btnAgregar = new JButton("Agregar usuario");
		btnAgregar.setBounds(400, 431, 390, 23);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario modelUsuario = null;
				File file = new File(filePicker.getSelectedFilePath());
				File folder = new File("resources" + File.separator + nickname.getText());
				file.getName();
				try {
					filePicker.saveFile(file, folder);					
				} catch (Exception e2) {
					System.out.println(e2);
				}
				try {
					modelUsuario = new Usuario(
							nickname.getText(), 
							nombre.getText(), 
							apellido.getText(), 
							correo.getText(),  
							f_nac.getDate(),
							folder.getAbsolutePath() + File.separator + file.getName()
					);					
				} catch (Exception e2) {
					System.out.println(e2);
				}

				UsuarioController Controlerusuario = new UsuarioController();
				Controlerusuario.crearUsuario(modelUsuario);
				
				JOptionPane.showMessageDialog(null, "Usuario creado");
				Frame.frame.setContentPane(Main);
				Frame.frame.revalidate();
			}
		});
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setBounds(10, 431, 368, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame.setContentPane(Main);
				Frame.frame.revalidate();
			}
		});
		filePicker = new JFilePicker("Img", "Buscar");
		filePicker.setBounds(348, 241, 442, 23);
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addFileTypeFilter(".jpg", "JPEG Images");
		filePicker.addFileTypeFilter(".png", "PNG Images");
		JFileChooser fileChooser = filePicker.getFileChooser();
		fileChooser.setCurrentDirectory(new File("."));		
		
		// access JFileChooser class directly	
		// add the component to the frame		
		add(filePicker);		
		
		JLabel label_3 = new JLabel("F. Nacimiento");
		label_3.setBounds(400, 148, 390, 13);
		add(label_3);
		
		f_nac = new JDateChooser();
		f_nac.setBounds(400, 172, 390, 23);
		add(f_nac);
		add(btnCancelar);
		
		add(btnAgregar);
		
		JLabel nickanamerrror = new JLabel("El nickname ya esta en uso");
		nickanamerrror.setForeground(Color.RED);
		nickanamerrror.setBounds(240, 87, 138, 13);
		nickanamerrror.setVisible(false);
		add(nickanamerrror);
		JLabel correoerror = new JLabel("El correo ya esta en uso");
		correoerror.setForeground(Color.RED);
		correoerror.setBounds(652, 87, 138, 13);
		correoerror.setVisible(false);
		add(correoerror);
		nickname = new JTextField();
		nickname.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				 String enterNickname = nickname.getText();
				 UsuarioController controller = new UsuarioController();
				 System.out.println(enterNickname);
				 Usuario usuario = controller.consultarUsuario(enterNickname);
				 if (usuario != null) {
					btnAgregar.setVisible(false);
					nickanamerrror.setVisible(true);
				 }else {
					btnAgregar.setVisible(true);
					nickanamerrror.setVisible(false);
				 }
			 }
		});
		correo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				 String enterCorreo = correo.getText();
				 UsuarioController controller = new UsuarioController();
				 Usuario usuario = controller.consultarEmail(enterCorreo);
				 if (usuario != null) {
					btnAgregar.setVisible(false);
					correoerror.setVisible(true);
				 }else {
					btnAgregar.setVisible(true);
					correoerror.setVisible(false);
				 }
			 }
		});		
		nickname.setBounds(10, 100, 368, 23);
		nickname.setColumns(10);
		add(nickname);
		
		JLabel lblAltaDeUsuario = new JLabel("ALTA DE USUARIO");
		lblAltaDeUsuario.setBounds(10, 51, 191, 14);
		add(lblAltaDeUsuario);
		
		JLabel lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(399, 217, 391, 14);
		add(lblImagen);
	}
}
