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
	
	
	public AltaComentario3(Video video) {
			setLayout(null);
			
			JLabel label = new JLabel("ALTA COMENTARIO");
			label.setBounds(10, 51, 191, 14);
			add(label);
			txtComentarioUno = new JTextField();
			txtComentarioUno.setText("respuesta uno");
			txtComentarioUno.setBounds(281, 63, 136, 20);
			add(txtComentarioUno);
			txtComentarioUno.setColumns(10);
			
			
			
			
			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(10, 431, 368, 23);
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConsultaVideosUsuario listarVU = new ConsultaVideosUsuario(video.getCanal().getUsuario());
					Frame.frame.setContentPane(listarVU);
					Frame.frame.revalidate();
				}
			});
			
			JLabel lblFecha = new JLabel("fecha");
			lblFecha.setBounds(456, 66, 48, 14);
			add(lblFecha);
			add(btnVolver);
			
			//Comentarios con Jtree-------------------------------------------------------------------
			ComentarioController ComControl = new ComentarioController();
			List<Comentario> Comentarios =  ComControl.listarComentarios(video.getNombre());			
			//String ComentarioText = coment.getComentario();
			//Comentarios con jtree
			DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Comentarios");
			DefaultTreeModel modelo = new DefaultTreeModel(raiz);
			DefaultMutableTreeNode nuevo=new DefaultMutableTreeNode("Comentario nuevo");
			modelo.insertNodeInto(nuevo, raiz, 0);
			
			for(Comentario coment:Comentarios) {
				//DefaultMutableTreeNode idComentario = <String>coment.getId();
				//String FechaStrng = coment.getFecha().toString().substring(15);
				DefaultMutableTreeNode coment1 = new DefaultMutableTreeNode(coment.getId()+"-" + coment.getFecha().toString().substring(0, 13)+" - "+coment.getComentario());
				modelo.insertNodeInto(coment1,raiz,0);
				
				DefaultMutableTreeNode responder=new DefaultMutableTreeNode("Responder");
				modelo.insertNodeInto(responder, coment1, 0);
				
				List<Comentario> Respuestas = ComControl.ListarRespuestas(coment.getId());
				for(Comentario resp:Respuestas) {
					
					DefaultMutableTreeNode respuesta1 = new DefaultMutableTreeNode(resp.getId()+"-" + resp.getFecha().toString().substring(0, 13)+" - "+resp.getComentario());
					modelo.insertNodeInto(respuesta1,coment1,0);
				}
			}
			//contenedor con scroll
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 100, 780, 313);
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
				//guarda hoja del arbol 
				TreePath[] paths = tree.getSelectionPaths();
                String[] IdControl = null;
                if (paths!=null) {//si selecciono una rama del arbol es por que va a comentar un comentario 
	                for (TreePath path : paths) {
	                	IdControl = path.getLastPathComponent().toString().split("-");
	                    System.out.println("You've selected: "+ IdControl[0]);
	                    if(IdControl[0]=="Comentarios") {// hoja Comentarios es para nuevo comentaroi al video 
	                        ComentarioController controladorC = new ComentarioController();
	        			    Comentario c = new Comentario();
	        			    c.setComentario(txtComentarioUno.getText());
	        			    c.setFecha(new Date());
	        			    c.setVid(video);      			    
	        			    controladorC.AgregarComentario(c);                      
	                    }
	                    else {//si seleeciono un id es para comentar ese comentario
	                    	ComentarioController controladorC = new ComentarioController();
	        			    //Comentario c = new Comentario();
	        			    Comentario  r = new Comentario();
	        			    r.setComentario(txtComentarioUno.getText());
	        			    r.setFecha(new Date());
	        			    r.setVid(null);      			    
	        			    long IdNum = Long.parseLong(IdControl[0]);//transformo a long la hoja seleccionada
	        			    controladorC.AgregarRespuesta(IdNum, r);
	        			    //public void AgregarRespuesta(Long idComentario, Comentario respuesta) 
	                    	
	                    }
	                }	
                }
                else { 
               
                ComentarioController controladorC = new ComentarioController();
			    Comentario c = new Comentario();
			    c.setComentario(txtComentarioUno.getText());
			    c.setFecha(new Date());
			    c.setVid(video);
			    //long IdNum = Long.parseLong(IdControl[0]);
			    //boolean existe = controladorC.ExisteComentarioID(IdNum);
			    //if (IdControl !=null)  {c.setId(IdNum); } //
			    //Long idContr =(long)IdControl[0].;
			    
			    
			    controladorC.AgregarComentario(c);
                }
			    ConsultaVideoyComentarios consultarDV = new ConsultaVideoyComentarios(video);
				Frame.frame.setContentPane(consultarDV);
				Frame.frame.revalidate();
			    
			    
				}
			});
			btnNewButton.setBounds(400, 431, 390, 23);
			add(btnNewButton);
			
			JLabel lblNickname = new JLabel("Comentario");
			lblNickname.setBounds(10, 80, 430, 23);
			add(lblNickname);
			
			
			
			
			
			
		}
}


	


