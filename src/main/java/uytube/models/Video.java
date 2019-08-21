package uytube.models;
import java.util.Date;

import javax.persistence.*;
import uytube.models.Usuario;

@Entity
@Table(name = "Video")
public class Video {
	
	@Id
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "duracion")
	private String duracion;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "url")
	private String url;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="username")
	Usuario usuario;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="nombre", insertable = false, updatable = false)
	Categoria categoria;

	
	public Video (String nombre, String descripcion, String duracion, Date fecha, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.fecha = fecha;
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

