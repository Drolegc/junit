package uytube.models;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	//List<Comentario> respuestas;
	
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
