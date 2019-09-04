package uytube.models;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Comentario> respuestas;
	
	@ManyToOne(cascade=CascadeType.ALL)//eeee proximamente join nulleable, por que tal vez no es necesario
	private Video vid;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Comentario> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Comentario> respuestas) {
		this.respuestas = respuestas;
	}
    
	public Comentario() {
		
	}
	
	public Comentario(Date fecha,String comentario) {
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
