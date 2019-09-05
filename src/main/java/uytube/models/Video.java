package uytube.models;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Columns;

import uytube.models.Usuario;

@Entity
@Table(name = "Video")
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id", insertable=false, updatable=false)
	private int id;

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
	
	@Column(name="es_publico")
	private boolean es_publico;
	
	

	public boolean getEs_publico() {
		return es_publico;
	}

	public void setEs_publico(boolean es_publico) {
		this.es_publico = es_publico;
	}

	@OneToOne(cascade = CascadeType.ALL)
	Canal canal;
	
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="id",nullable = true)
	Categoria categoria;



	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Video() {
		
	}
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
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

