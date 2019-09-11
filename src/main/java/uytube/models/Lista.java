package uytube.models;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
@Table(name = "Lista")
public class Lista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",insertable = false,updatable = false)
	private int id;
	
	
	//True = Default
	@Column( name = "tipo")
	private Boolean tipo;
	
	@Column( name = "nombre_lista" )
	private String nombre_lista;
	
	@Column( name = "privado ")
	private Boolean privado;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Categoria categoria;

	@OneToMany(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Video> videos;	
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Canal canal;
	
	public List<Video> getVideos() {
		return videos;
	}
	public Lista() {
		// TODO Auto-generated constructor stub
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public Lista(String nombre,boolean privado,Categoria categoria,Canal canal,boolean tipo) {
		this.nombre_lista = nombre;
		this.privado = privado;
		this.categoria = categoria;
		this.canal = canal;
		this.tipo = tipo;
	}

	public String getNombre_lista() {
		return nombre_lista;
	}
	public void setNombre_lista(String nombre_lista) {
		this.nombre_lista = nombre_lista;
	}
	public String getNombre() {
		return nombre_lista;
	}

	public void setNombre(String nombre) {
		this.nombre_lista = nombre;
	}

	public Boolean getPrivado() {
		return privado;
	}

	public void setPrivado(Boolean privado) {
		this.privado = privado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Canal getCanal() {
		return canal;
	}
	public void setCanal(Canal canal) {
		this.canal = canal;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
