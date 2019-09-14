package uytube.models;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Comentario")
public class Comentario {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "comentario")
	private String comentario;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Comentario> respuestas;
	
	@ManyToOne(cascade=CascadeType.MERGE)//eeee proximamente join nulleable, por que tal vez no es necesario
	private Video vid;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private  Usuario usuario;
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public Video getVid() {
		return vid;
	}
	
	public void setVid(Video vid) {
		this.vid = vid;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Comentario> getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(List<Comentario> respuestas) {
		this.respuestas = respuestas;
	}
    
	public Comentario() {
		
	}
	
	public Comentario(Video video,Date fecha,String comentario) {
		this.vid=video;
		this.fecha = fecha;
		this.comentario = comentario;
		
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
}
