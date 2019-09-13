package uytube.ComentarioController;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.Comentario;
import uytube.models.HibernateUtil;
import uytube.models.Lista;
import uytube.models.Video;
import uytube.models.manager.Manager;
import uytube.models.Comentario;
public class ComentarioController implements IComentario{

	

	private Session session;
	private Transaction transaction;
    private Manager mng;
	public ComentarioController() {
		mng = Manager.getInstance();
	}
	
	
public List<Comentario> ListarRespuestas(Long idComentario) {
	   //obtengo el comentario
	   Comentario c = (Comentario) mng.getSessionManager().createQuery("From Comentario where id = :IdComentario").setParameter("IdComentario", idComentario).getSingleResult();	
	   //List<Comentario> respuestas = mng.getSessionManager().createQuery("From Comentario where id = :IdComentario").setParameter("IdComentario", idComentario).getResultList();
	   //obtengo las respuestas de ese comentario
	   List<Comentario> respuestas = c.getRespuestas();
		
		//devuelvo lista de respuestas
		return respuestas;
     }

public List<Comentario> listarComentarios(String nombreVideo) {
	// TODO Auto-generated method stub
	
	List<Comentario> Comentarios = (List<Comentario>)mng.getSessionManager().createQuery("From Comentario where vid.nombre = :NombreVideo").setParameter("NombreVideo", nombreVideo).getResultList();
	mng.closeSession();
	System.out.println("Listando Comentarios");
	for(Comentario C: Comentarios) {
		System.out.println("Id comentario: "+C.getId()+" - Comentario: "+C.getComentario()+"Id Video: "+ C.getVid().getId());
	}
	return Comentarios;
}



	
	public void AgregarRespuesta(Long idComentario, Comentario respuesta) {
		
		Comentario c = (Comentario) mng.getSessionManager().createQuery("From Comentario where id = :IdComentario").setParameter("IdComentario", idComentario).getSingleResult();
		mng.closeSession();
		List<Comentario> respuestas = c.getRespuestas();
		respuestas.add(respuesta);
		c.setRespuestas(respuestas);
		mng.startTransaction("Comentario", c);
	
	}
	
	
	public boolean ExisteComentarioID(long idComentario) {
		 boolean existe =(boolean)mng.getSessionManager().createQuery("From Comentario where id = :IdComentario").setParameter("IdComentario", idComentario).getResultList().isEmpty();
		 System.out.println("id comentario ya existe "+!existe);
		
		 return !existe;
		
	}
	
	
	
	
	public void AgregarComentario(Comentario c) {
		// TODO Auto-generated method stub
		
		this.session = null;
		this.transaction = null;
		
		//Comentario Coment = new Comentario(c.getVid(), c.getFecha(),c.getComentario());
		
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction();
	        if(!transaction.isActive())
	        	transaction.begin();
	        session.saveOrUpdate("comentario", c);
	        transaction.commit();
	      } catch (Exception e) {
	        if (transaction != null) {
	          transaction.rollback();
	        }
	        e.printStackTrace();
	      } finally {
	        if (session != null) {
	          session.close();
	        }
	      }		
	}
	
	
	
	
	
	
	
	/*
	@Override
	public void altaComentario() {
		// TODO Auto-generated method stub
		//altaComentario(Video video, String comentario,Comentario padre)
		/*
		 * Tenemos que crear un nuevo comentario
		 * 
		 * Chequear si video es nulo
		 * 
		 *	Si es nulo, agregar el nuevo comenario a la coleccion del padre
		 *
		 *padre.getRespuestas().add(nuevoComentario)
		 *
		 *mng.startTransaccion(padre)
		 *
		 *
		 * 
		
	}

	@Override
	public void altaComentario(Video video, String comentario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaComentario(Video video, String comentario, Comentario padre) {
		// TODO Auto-generated method stub
		
	}
*/

	
	
	
}
