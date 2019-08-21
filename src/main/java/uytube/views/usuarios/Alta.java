package uytube.views.usuarios;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Inicio;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Window;
import uytube.views.Inicio;
public class Alta extends JPanel {

	private JTextField nickname;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField img;
	private JTextField correo;
	private JDateChooser f_nac;
	private JFrame frame;
	Inicio inicio;
	public Alta(JFrame f) {
		inicio = new Inicio(f);
		this.frame = f;
		setLayout(null);
		nickname = new JTextField();
		nickname.setBounds(30, 85, 148, 19);
		add(nickname);
		nickname.setColumns(10);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(30, 62, 45, 13);
		add(lblNickname);
		
		nombre = new JTextField();
		nombre.setBounds(30, 33, 148, 19);
		add(nombre);
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(261, 33, 148, 19);
		add(apellido);
		
		img = new JTextField();
		img.setColumns(10);
		img.setBounds(30, 137, 148, 19);
		add(img);
		
		correo = new JTextField();
		correo.setColumns(10);
		correo.setBounds(261, 85, 148, 19);
		add(correo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 10, 37, 13);
		add(lblNombre);
		
		JLabel label = new JLabel("Apellido");
		label.setBounds(261, 10, 36, 13);
		add(label);
		
		JLabel label_1 = new JLabel("Img (Opcional)");
		label_1.setBounds(30, 114, 69, 13);
		add(label_1);
		
		JLabel label_2 = new JLabel("Correo");
		label_2.setBounds(261, 62, 31, 13);
		add(label_2);
		
		JLabel label_3 = new JLabel("F. Nacimiento");
		label_3.setBounds(261, 114, 63, 13);
		add(label_3);
		
		f_nac = new JDateChooser();
		f_nac.setBounds(261, 137, 148, 19);
		add(f_nac);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario modelUsuario = new Usuario();
				modelUsuario.setNickname(nickname.getText());
				modelUsuario.setApellido(apellido.getText());
				modelUsuario.setNombre(nombre.getName());
				modelUsuario.setCorreo(correo.getText());
				modelUsuario.setFnacimiento(f_nac.getDate());
				modelUsuario.setImg(img.getText());
				UsuarioController Controlerusuario = new UsuarioController();
				Controlerusuario.crearUsuario(modelUsuario);
				JOptionPane.showMessageDialog(null, "Usuario creado");
				frame.setContentPane(inicio);
				frame.revalidate();
			}
		});
		btnAgregar.setBounds(342, 208, 69, 21);
		add(btnAgregar);
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(inicio);
				frame.revalidate();
			}
		});
		btnCancelar.setBounds(261, 208, 71, 21);
		add(btnCancelar);

	}

}
