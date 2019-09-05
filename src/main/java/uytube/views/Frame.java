package uytube.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import uytube.UsuarioController.UsuarioController;
import uytube.models.Usuario;
import uytube.views.Inicio;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import uytube.views.usuarios.Alta;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
public class Frame {
	public static JFrame frame=new JFrame("UyTube");
	public static void main(String[] args) {
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAgregar = new JMenuItem("Agregar");
		mnUsuarios.add(mntmAgregar);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mnUsuarios.add(mntmConsultar);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mnUsuarios.add(mntmListar);
		
		JMenuItem mntmSeguir = new JMenuItem("Seguir");
		mnUsuarios.add(mntmSeguir);
		
		JMenuItem mntmDejarDeSeguir = new JMenuItem("Dejar de seguir");
		mnUsuarios.add(mntmDejarDeSeguir);
		
		JMenu mnVideos = new JMenu("Videos");
		menuBar.add(mnVideos);
		
		JMenuItem mntmAgregar2 = new JMenuItem("Agregar");
		mnUsuarios.add(mntmAgregar2);
		
		JMenuItem mntmConsultar2 = new JMenuItem("Consultar");
		mnUsuarios.add(mntmConsultar2);
		
		JMenuItem mntmListar2 = new JMenuItem("Listar");
		mnUsuarios.add(mntmListar2);
		
		JMenuItem mntmSeguir2 = new JMenuItem("Seguir");
		mnUsuarios.add(mntmSeguir2);
		
		JMenuItem mntmDejarDeSeguir2 = new JMenuItem("Dejar de seguir");
		mnUsuarios.add(mntmDejarDeSeguir2);
		
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		
	}
}
