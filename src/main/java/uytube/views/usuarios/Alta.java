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

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Window;
import uytube.views.usuarios.UserMain;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.xml.bind.v2.schemagen.xmlschema.Import;
public class Alta extends JPanel {

	private JTextField nickname;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField img;
	private JTextField correo;
	private JDateChooser f_nac;
	private UserMain Main;
	private JFilePicker filePicker;
	public Alta() {
		Main = new UserMain();
		setLayout(null);
		nickname = new JTextField();
		nickname.setBounds(144, 31, 148, 19);
		add(nickname);
		nickname.setColumns(10);
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(66, 34, 45, 13);
		add(lblNickname);
		
		nombre = new JTextField();
		nombre.setBounds(144, 60, 148, 19);
		add(nombre);
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setBounds(144, 89, 148, 19);
		apellido.setColumns(10);
		add(apellido);
				
		correo = new JTextField();
		correo.setBounds(144, 118, 150, 19);
		correo.setColumns(10);
		add(correo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(66, 63, 37, 13);
		add(lblNombre);
		
		JLabel label = new JLabel("Apellido");
		label.setBounds(66, 92, 36, 13);
		add(label);
		
		JLabel label_2 = new JLabel("Correo");
		label_2.setBounds(66, 121, 31, 13);
		add(label_2);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(188, 255, 104, 21);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario modelUsuario = new Usuario();
				modelUsuario.setNickname(nickname.getText());
				modelUsuario.setApellido(apellido.getText());
				modelUsuario.setNombre(nombre.getText());
				modelUsuario.setCorreo(correo.getText());
				modelUsuario.setFnacimiento(f_nac.getDate());
				modelUsuario.setImg(filePicker.getSelectedFilePath());
				UsuarioController Controlerusuario = new UsuarioController();
				Controlerusuario.crearUsuario(modelUsuario);
				filePicker.saveFile(new File("resources").getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Usuario creado");
				Frame.frame.setContentPane(Main);
				Frame.frame.revalidate();
			}
		});
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setBounds(66, 255, 95, 21);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame.setContentPane(Main);
				Frame.frame.revalidate();
			}
		});
		filePicker = new JFilePicker("Img", "Buscar");
		filePicker.setBounds(63, 207, 231, 52);
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addFileTypeFilter(".jpg", "JPEG Images");
		filePicker.addFileTypeFilter(".png", "PNG Images");
		JFileChooser fileChooser = filePicker.getFileChooser();
		fileChooser.setCurrentDirectory(new File("."));		
		
		// access JFileChooser class directly	
		// add the component to the frame		
		add(filePicker);		
		
		JLabel label_3 = new JLabel("F. Nacimiento");
		label_3.setBounds(66, 163, 63, 13);
		add(label_3);
		
		f_nac = new JDateChooser();
		f_nac.setBounds(144, 157, 148, 19);
		add(f_nac);
		add(btnCancelar);
		
		add(btnAgregar);

	}

}
