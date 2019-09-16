package uytube.views.videos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;
import uytube.CategoriaController.CategoriaController;
import uytube.ComentarioController.ComentarioController;
import uytube.UsuarioController.UsuarioController;
import uytube.VideoController.VideoController;
import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.Comentario;
import uytube.models.Usuario;
import uytube.models.Video;
import uytube.views.Frame;
import uytube.views.usuarios.Listar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JCheckBox;

public class AltaComentario3 extends JPanel {

	/**
	 * Create the panel.
	 * @param video 
	 */
	private JTextField userInfo;
	private JTextField descrip;
	private VideoMain main;
	private Video videito;
	private String userInfoStr;
	private String catAsignar;
	private JTextField nickname;
	private JTextField duracion1;
	private JTextField txtComentarioUno;
	private JDateChooser fecPub;
	private String nickInfoStr;
	private JComboBox userInfo1;
	
	public AltaComentario3(Video video) {
			setLayout(null);
			
			JLabel label = new JLabel("ALTA COMENTARIO");
			label.setBounds(10, 51, 191, 14);
			add(label);
			txtComentarioUno = new JTextField();
			txtComentarioUno.setText("respuesta uno");
			txtComentarioUno.setBounds(287, 101, 250, 23);
			add(txtComentarioUno);
			txtComentarioUno.setColumns(10);
			
			
			
			
			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(10, 431, 368, 23);
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UsuarioController usercontrol = new UsuarioController();
					Usuario usuario = usercontrol.consultarUsuario(video.getCanal().getNombre());
					AltaComentario2 listarVU = new AltaComentario2(usuario);
					Frame.frame.setContentPane(listarVU);
					Frame.frame.revalidate();
					}
			});
			
			JLabel lblFecha = new JLabel("Seleccione una fecha");
			lblFecha.setBounds(547, 84, 136, 14);
			add(lblFecha);
			add(btnVolver);
			
			//Comentarios con Jtree-------------------------------------------------------------------
			ComentarioController ComControl = new ComentarioController();
			List<Comentario> Comentarios =  ComControl.listarComentarios(video.getNombre());			
			//String ComentarioText = coment.getComentario();
			//Comentarios con jtree
			DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Comentarios");
			DefaultTreeModel modelo = new DefaultTreeModel(raiz);
			
			for(Comentario coment:Comentarios) {
				//DefaultMutableTreeNode idComentario = <String>coment.getId();
				//String FechaStrng = coment.getFecha().toString().substring(15);
				DefaultMutableTreeNode coment1 = new DefaultMutableTreeNode(coment.getId()+"> " + coment.getFecha().toString().substring(0, 10)+" > "+ coment.getUsuario().getNickname()+" > "+coment.getComentario());
				modelo.insertNodeInto(coment1,raiz,0);
				
				
				List<Comentario> Respuestas = ComControl.ListarRespuestas(coment.getId());
				for(Comentario resp:Respuestas) {
					
					DefaultMutableTreeNode respuesta1 = new DefaultMutableTreeNode(resp.getId()+"> " + resp.getFecha().toString().substring(0, 10)+" > "+ resp.getUsuario().getNickname()+" > "+resp.getComentario());
					modelo.insertNodeInto(respuesta1,coment1,0);
				}
			}
			//contenedor con scroll
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 148, 780, 265);
			add(scrollPane);
			
			JTree tree = new JTree(modelo);
			for (int i = 0; i < tree.getRowCount(); i++) {
			    tree.expandRow(i);
			}
			scrollPane.setViewportView(tree);
						// comentario con jtree
			
			
			
			JButton btnNewButton = new JButton("Comentar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UsuarioController vcontrol =new UsuarioController();
					
				Usuario usuario = vcontrol.consultarUsuario(nickInfoStr);
				if (nickInfoStr == null || txtComentarioUno.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario y/o ingresar un texto para comentar");
				} else {
				//guarda hoja del arbol 
				TreePath[] paths = tree.getSelectionPaths();
                String[] IdControl = null;
                if (paths!=null) {//si selecciono una rama del arbol es por que va a comentar un comentario 
	                for (TreePath path : paths) {
	                	IdControl = path.getLastPathComponent().toString().split(">");
	                    System.out.println("You've selected: "+ IdControl[0]);
	                    if(IdControl[0]=="Comentarios") {// hoja Comentarios es para nuevo comentaroi al video 
	                    	System.out.println(path.toString()+"12222");
	                    	ComentarioController controladorC = new ComentarioController();
	        			    Comentario c = new Comentario();
	        			    c.setComentario(txtComentarioUno.getText());
	        			    c.setFecha(fecPub.getDate());	
	        			    c.setVid(video);   
	        			    c.setUsuario(usuario);
	        			    controladorC.AgregarComentario(c);                      
	                    }
	                    else {//si seleeciono un id es para responder comentario
			                //  if (path..isDescendant(coment1)){  	
	                    	System.out.println(path.toString()+"1111111111111111");
	                    	       long IdNum = Long.parseLong(IdControl[0]);//transformo a long la hoja seleccionada
	                    	       ComentarioController controladorC = new ComentarioController();
	                    	       Comentario co = controladorC.getcomentario(IdNum);
			        			   
	                    	       if(co.getVid()!=null) {
	                    	       //Comentario c = new Comentario();
			        			    Comentario  r = new Comentario();
			        			    r.setComentario(txtComentarioUno.getText());
			        			    r.setFecha(fecPub.getDate());
			        			    r.setVid(null);   
			        			    r.setUsuario(usuario);
			        			   
			        			    controladorC.AgregarRespuesta(IdNum, r);
			        			   }else {
			        				   JOptionPane.showMessageDialog(null, "Es una hoja no se puede"); 
			        				   AltaComentario altacom = new AltaComentario();
			        					Frame.frame.setContentPane(altacom);
			        					Frame.frame.revalidate();
			        			   }
			        			    
			        			    //public void AgregarRespuesta(Long idComentario, Comentario respuesta) 
			                  //}
	                    }
			                 
	                }	
                }
               else { 
                	System.out.println("33333333333333");
                ComentarioController controladorC = new ComentarioController();
			    Comentario c = new Comentario();
			    c.setComentario(txtComentarioUno.getText());
			    c.setFecha(fecPub.getDate());
			    c.setVid(video);
			    c.setUsuario(usuario);
			    controladorC.AgregarComentario(c);
                }
			    ConsultaVideoyComentarios consultarDV = new ConsultaVideoyComentarios(video);
				Frame.frame.setContentPane(consultarDV);
				Frame.frame.revalidate();
				}
			    
				   }
			});
			btnNewButton.setBounds(400, 431, 390, 23);
			add(btnNewButton);
			
			JLabel lblNickname = new JLabel("Seleccione un comentario para responder ");
			lblNickname.setBounds(10, 130, 430, 23);
			add(lblNickname);
			
			fecPub = new JDateChooser();
			fecPub.setBounds(547, 102, 243, 23);
			fecPub.setDate(video.getFecha());
			add(fecPub);
			
			

			// BOTON DE ASIGNACION DE USUARIO
			
			UsuarioController controladorUsuario = new UsuarioController();
			ArrayList<Usuario> usuarios = controladorUsuario.listaUsuarios();
			int tamanio =  usuarios.size()+1;
			String[] array = new String[tamanio];
			array[0]="Debe elegir usuario";
			for(int i = 1; i < array.length; i++) {
			    array[i] = usuarios.get(i-1).getNickname();
			}		
			
			JComboBox userInfo1 = new JComboBox(array);
			userInfo1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox comboBox1 = (JComboBox)e.getSource();
			        nickInfoStr = (String)comboBox1.getSelectedItem();
			        System.out.println("ELEG� USER Y ES: "+ nickInfoStr);  
					}
			});
			userInfo1.setBounds(10, 100, 267, 23);
			add(userInfo1);
			
			JLabel lblIngreseElTexto = new JLabel("Ingrese el texto del comentario");
			lblIngreseElTexto.setBounds(289, 84, 248, 14);
			add(lblIngreseElTexto);
			
			JLabel lblNewLabel = new JLabel("Seleccione un usuario");
			lblNewLabel.setBounds(10, 84, 267, 14);
			add(lblNewLabel);
			
			
			
		}
}


	


