package uytube.views.usuarios;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import resources.files.JFilePicker;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Frame;
import uytube.views.Inicio;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.maven.model.Profile;

import java.awt.Window;
import uytube.views.usuarios.UserMain;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.Icon;
public class Editar extends JPanel {

	private JTextField nickname;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField img;
	private JTextField correo;
	private JDateChooser f_nac;
	private JFilePicker filePicker;
	private BufferedImage image;
	public Editar(Usuario user) {
		setLayout(null);
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(10, 87, 148, 14);
		add(lblNickname);
		
		JLabel lblRrrr = new JLabel();
		lblRrrr.setBounds(403, 283, 115, 122);
		ImageIcon imgIcon = new ImageIcon(user.getImg());
		Image img = imgIcon.getImage();
		Image newImg = img.getScaledInstance(lblRrrr.getWidth(), lblRrrr.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon profilePicture = new ImageIcon(newImg);
		add(lblRrrr);
		
		JLabel label_2 = new JLabel("Correo");
		label_2.setBounds(403, 87, 166, 14);
		add(label_2);
		this.nickname = new JTextField();
		nickname.setBounds(10, 104, 368, 23);
		add(nickname);
		this.nickname.setColumns(10);
		this.nickname.setEditable(false);
		this.nickname.setText(user.getNickname());
		
		this.correo = new JTextField();
		correo.setBounds(400, 100, 390, 23);
		this.correo.setColumns(10);
		this.correo.setEditable(false);
		this.correo.setText(user.getCorreo());
		add(correo);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 148, 368, 14);
		add(lblNombre);
		
		JLabel label = new JLabel("Apellido");
		label.setBounds(10, 217, 360, 14);
		add(label);
		
		this.nombre = new JTextField();
		nombre.setBounds(10, 173, 368, 23);
		add(nombre);
		this.nombre.setColumns(10);
		this.nombre.setText(user.getNombre());
		this.apellido = new JTextField();
		apellido.setBounds(10, 235, 368, 23);
		this.apellido.setColumns(10);
		add(apellido);
		this.apellido.setText(user.getApellido());
		
		JLabel label_3 = new JLabel("F. Nacimiento");
		label_3.setBounds(400, 149, 63, 14);
		add(label_3);
		
		f_nac = new JDateChooser();
		f_nac.setBounds(400, 172, 390, 23);
		f_nac.setDate(user.getFnacimiento());
		add(f_nac);
		
		JButton btnAgregar = new JButton("Editar");
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
				Controlerusuario.modificarUsuario(modelUsuario);
				Listar listar = new Listar();
				JOptionPane.showMessageDialog(Frame.frame, "Usuario Editado");
				Frame.frame.setContentPane(listar);
				Frame.frame.revalidate();
			}
		});
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setBounds(10, 431, 368, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listar listar = new Listar();				
				Frame.frame.setContentPane(listar);
				Frame.frame.revalidate();
			}
		});
		filePicker = new JFilePicker("Img", "Buscar");
		filePicker.setBounds(345, 235, 445, 23);
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addFileTypeFilter(".jpg", "JPEG Images");
		filePicker.addFileTypeFilter(".png", "PNG Images");
		JFileChooser fileChooser = filePicker.getFileChooser();
		fileChooser.setCurrentDirectory(new File("."));		
		
		// access JFileChooser class directly	
		// add the component to the frame		
		add(filePicker);		
		add(btnCancelar);
		add(btnAgregar);
		
		JLabel lblImagenDeUsuario = new JLabel("Imagen de usuario");
		lblImagenDeUsuario.setBounds(403, 217, 387, 14);
		add(lblImagenDeUsuario);
		
		JLabel lblModificarUsuario = new JLabel("MODIFICAR USUARIO");
		lblModificarUsuario.setBounds(10, 51, 191, 14);
		add(lblModificarUsuario);
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}
