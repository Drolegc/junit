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
import uytube.views.Frame;
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
import uytube.views.usuarios.UserMain;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
public class Alta extends JPanel {

	private JTextField nickname;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField img;
	private JTextField correo;
	private JDateChooser f_nac;
	private UserMain Main;
	public Alta() {
		Main = new UserMain();
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("30px"),
				ColumnSpec.decode("148px"),
				ColumnSpec.decode("83px"),
				ColumnSpec.decode("71px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("69px"),},
			new RowSpec[] {
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("13px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("13px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("13px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				RowSpec.decode("52px"),
				RowSpec.decode("21px"),}));
		nickname = new JTextField();
		add(nickname, "2, 8, fill, top");
		nickname.setColumns(10);
		
		JLabel lblNickname = new JLabel("Nickname");
		add(lblNickname, "2, 6, left, top");
		
		nombre = new JTextField();
		add(nombre, "2, 4, fill, top");
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		add(apellido, "4, 4, 3, 1, fill, top");
		
		img = new JTextField();
		img.setColumns(10);
		add(img, "2, 12, fill, top");
		
		correo = new JTextField();
		correo.setColumns(10);
		add(correo, "4, 8, 3, 1, fill, top");
		
		JLabel lblNombre = new JLabel("Nombre");
		add(lblNombre, "2, 2, left, top");
		
		JLabel label = new JLabel("Apellido");
		add(label, "4, 2, left, top");
		
		JLabel label_1 = new JLabel("Img (Opcional)");
		add(label_1, "2, 10, left, top");
		
		JLabel label_2 = new JLabel("Correo");
		add(label_2, "4, 6, left, top");
		
		JLabel label_3 = new JLabel("F. Nacimiento");
		add(label_3, "4, 10, left, top");
		
		f_nac = new JDateChooser();
		add(f_nac, "4, 12, 3, 1, fill, top");
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario modelUsuario = new Usuario();
				modelUsuario.setNickname(nickname.getText());
				modelUsuario.setApellido(apellido.getText());
				modelUsuario.setNombre(nombre.getText());
				modelUsuario.setCorreo(correo.getText());
				modelUsuario.setFnacimiento(f_nac.getDate());
				modelUsuario.setImg(img.getText());
				UsuarioController Controlerusuario = new UsuarioController();
				Controlerusuario.crearUsuario(modelUsuario);
				JOptionPane.showMessageDialog(null, "Usuario creado");
				Frame.frame.setContentPane(Main);
				Frame.frame.revalidate();
			}
		});
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame.setContentPane(Main);
				Frame.frame.revalidate();
			}
		});
		add(btnCancelar, "2, 14, default, top");
		add(btnAgregar, "4, 14, 3, 1, default, top");

	}

}
