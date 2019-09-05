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
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("63px"),
				ColumnSpec.decode("63px"),
				ColumnSpec.decode("20px"),
				ColumnSpec.decode("32px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("110px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("115px"),},
			new RowSpec[] {
				RowSpec.decode("41px"),
				RowSpec.decode("19px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				RowSpec.decode("21px"),
				RowSpec.decode("19px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				RowSpec.decode("19px"),
				RowSpec.decode("52px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("21px"),}));
		JLabel lblNickname = new JLabel("Nickname");
		add(lblNickname, "2, 2, left, center");
		
		JLabel label_1 = new JLabel();
		label_1.setBounds(100,100,450,300);
		ImageIcon imgIcon = new ImageIcon(user.getImg());
		Image img = imgIcon.getImage();
		Image newImg = img.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon profilePicture = new ImageIcon(newImg);
		label_1.setIcon(profilePicture);
		add(label_1, "8, 2, 1, 7, fill, fill");
		
		JLabel label_2 = new JLabel("Correo");
		add(label_2, "2, 8, left, center");
		this.nickname = new JTextField();
		add(nickname, "4, 2, 3, 1, fill, top");
		this.nickname.setColumns(10);
		this.nickname.setEditable(false);
		this.nickname.setText(user.getNickname());
		
		this.correo = new JTextField();
		this.correo.setColumns(10);
		this.correo.setText(user.getCorreo());
		add(correo, "4, 8, 3, 1, fill, top");
		JLabel lblNombre = new JLabel("Nombre");
		add(lblNombre, "2, 4, left, center");
		
		JLabel label = new JLabel("Apellido");
		add(label, "2, 6, left, center");
		
		this.nombre = new JTextField();
		add(nombre, "4, 4, 3, 1, fill, top");
		this.nombre.setColumns(10);
		this.nombre.setText(user.getNombre());
		this.apellido = new JTextField();
		this.apellido.setColumns(10);
		add(apellido, "4, 6, 3, 1, fill, top");
		this.apellido.setText(user.getApellido());
		
		JLabel label_3 = new JLabel("F. Nacimiento");
		add(label_3, "2, 10, left, bottom");
		
		f_nac = new JDateChooser();
		f_nac.setDate(user.getFnacimiento());
		add(f_nac, "4, 10, 3, 1, fill, top");
		
		JButton btnAgregar = new JButton("Editar");
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
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listar listar = new Listar();				
				Frame.frame.setContentPane(listar);
				Frame.frame.revalidate();
			}
		});
		filePicker = new JFilePicker("Img", "Buscar");
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addFileTypeFilter(".jpg", "JPEG Images");
		filePicker.addFileTypeFilter(".png", "PNG Images");
		JFileChooser fileChooser = filePicker.getFileChooser();
		fileChooser.setCurrentDirectory(new File("."));		
		
		// access JFileChooser class directly	
		// add the component to the frame		
		add(filePicker, "2, 12, 5, 1, fill, fill");		
		add(btnCancelar, "2, 14, 3, 1, fill, top");
		add(btnAgregar, "6, 14, fill, top");
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}
