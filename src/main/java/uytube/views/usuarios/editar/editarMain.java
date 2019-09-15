package uytube.views.usuarios.editar;

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
import uytube.CanalController.CanalController;
import uytube.UsuarioController.UsuarioController;
import uytube.models.Canal;
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

import uytube.views.usuarios.Listar;
import uytube.views.usuarios.UserMain;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.Icon;
import javax.swing.JTextArea;
public class editarMain extends JPanel {

	private JTextField nickname;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField img;
	private JTextField correo;
	private JDateChooser f_nac;
	private JFilePicker filePicker;
	private BufferedImage image;
	private JTextField nombreCanal;
	private Canal canal;
	public editarMain(Usuario user) {
		setLayout(null);
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(49, 10, 45, 13);
		add(lblNickname);
		CanalController canalcont = new CanalController();
		canal = canalcont.obtenerCanalUsuario(user.getNickname());
		
		JLabel label_1 = new JLabel();
		label_1.setBounds(300,41,115,119);
		ImageIcon imgIcon = new ImageIcon(user.getImg());
		Image img = imgIcon.getImage();
		Image newImg = img.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon profilePicture = new ImageIcon(newImg);
		label_1.setIcon(profilePicture);
		add(label_1);
		nombreCanal = new JTextField();
		nombreCanal.setText(canal.getNombre());
		nombreCanal.setBounds(49, 193, 148, 19);
		add(nombreCanal);
		nombreCanal.setColumns(10);
		
		JRadioButton statusCanal = new JRadioButton("Es privado?");
		statusCanal.setBounds(217, 192, 105, 21);
		statusCanal.setSelected(canal.getPrivacidad());
		add(statusCanal);
		
		JTextArea descripcionCanal = new JTextArea();
		descripcionCanal.setBounds(49, 253, 316, 40);
		descripcionCanal.setText(canal.getDescripcion());
		add(descripcionCanal);
		
		JLabel lblDescripcionDelCanal = new JLabel("Descripcion del canal");
		lblDescripcionDelCanal.setBounds(49, 230, 115, 13);
		add(lblDescripcionDelCanal);
		
		JLabel label_2 = new JLabel("Correo");
		label_2.setBounds(217, 62, 31, 13);
		add(label_2);
		this.nickname = new JTextField();
		nickname.setBounds(49, 33, 148, 19);
		add(nickname);
		this.nickname.setColumns(10);
		this.nickname.setEditable(false);
		this.nickname.setText(user.getNickname());
		
		this.correo = new JTextField();
		correo.setBounds(217, 85, 148, 19);
		this.correo.setColumns(10);
		this.correo.setEditable(false);
		this.correo.setText(user.getCorreo());
		add(correo);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(217, 10, 37, 13);
		add(lblNombre);
		
		JLabel label = new JLabel("Apellido");
		label.setBounds(49, 62, 36, 13);
		add(label);
		
		this.nombre = new JTextField();
		nombre.setBounds(217, 33, 148, 19);
		add(nombre);
		this.nombre.setColumns(10);
		this.nombre.setText(user.getNombre());
		this.apellido = new JTextField();
		apellido.setBounds(49, 85, 148, 19);
		this.apellido.setColumns(10);
		add(apellido);
		this.apellido.setText(user.getApellido());
		
		JLabel label_3 = new JLabel("F. Nacimiento");
		label_3.setBounds(49, 114, 63, 13);
		add(label_3);
		
		f_nac = new JDateChooser();
		f_nac.setBounds(49, 141, 148, 19);
		f_nac.setDate(user.getFnacimiento());
		add(f_nac);
		
		JButton btnAgregar = new JButton("Editar");
		btnAgregar.setBounds(174, 316, 110, 21);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(validateFields()) {
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
					canal.setNombre(nombreCanal.getText());
					canal.setPrivacidad(statusCanal.isSelected());
					canal.setDescripcion(descripcionCanal.getText());
					Controlerusuario.modificarUsuario(modelUsuario, canal);
					CanalController canalcont = new CanalController();
					canalcont.actualizarCanal(canal);
					Listar listar = new Listar();
					JOptionPane.showMessageDialog(Frame.frame, "Usuario Editado");
					Frame.frame.setContentPane(listar);
					Frame.frame.revalidate();
				}
			}
			public boolean validateFields()
			{
			  if (! validateField( nickname.getText(), "Debe ingresar un Nickname"))
			    return false;
			  else
			  if (! validateField( nombre.getText(), "Debe ingresar un nombre"))
			    return false;
			  else
			  if (! validateField( apellido.getText(), "Debe ingresar un apellido"))
			    return false;
			  if (! validateField( correo.getText(), "Debe ingresar un correo"))
				return false;
			  if (! validateField( descripcionCanal.getText(), "Debe ingresar una descripcion"))
				return false;
			  else
			    return true;
			}

			// test if field is empty
			public boolean validateField( String f, String errormsg )
			{
			  if ( f.equals("") )
			    return failedMessage( errormsg );
			  else
			    return true; // validation successful
			}

			public boolean validateInteger( JTextField f, String errormsg )
			{
			  try
			  {  // try to convert input to integer
			    int i = Integer.parseInt(f.getText());

			    // input must be greater then 0
			    // if it is, success
			    if ( i > 0 )
			      return true; // success, validation succeeded
			   }
			   catch(Exception e)
			   {
			      // if conversion failed, or input was <= 0,
			      // fall-through and do final return below
			   }
			   return failedMessage( errormsg );
			}

			public boolean failedMessage(String errormsg)
			{
			  JOptionPane.showMessageDialog(Frame.frame, errormsg); // give user feedback
			  return false; // return false, as validation has failed
			}			
		});
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setBounds(49, 316, 115, 21);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listar listar = new Listar();				
				Frame.frame.setContentPane(listar);
				Frame.frame.revalidate();
			}
		});
		filePicker = new JFilePicker("Img", "Buscar");
		filePicker.setBounds(209, 141, 231, 19);
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
		
		JButton btnVerVideosY = new JButton("Ver videos y listas");
		btnVerVideosY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoVideosListas videosYlistas = new infoVideosListas(user);
				Frame.frame.setContentPane(videosYlistas);
				Frame.frame.revalidate();
			}
		});
		btnVerVideosY.setBounds(300, 316, 115, 21);
		add(btnVerVideosY);
		
		JLabel lblNombreDelCanal = new JLabel("Nombre del canal (Opcional)");
		lblNombreDelCanal.setBounds(49, 170, 148, 13);
		add(lblNombreDelCanal);
		
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
}
