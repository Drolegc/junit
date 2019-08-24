package uytube.models;

import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "Lista")
public class Lista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",insertable = false,updatable = false)
	private int id;
	
	@Column( name = "nombre_lista" )
	private String nombre_lista;
	
	@Column( name = "privado ")
	private Boolean privado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nombre",nullable=true)
	private Categoria categoria;

	@OneToMany(cascade = CascadeType.ALL)
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

	public Lista(String nombre,boolean privado,Categoria categoria,Canal canal) {
		this.nombre_lista = nombre;
		this.privado = privado;
		this.categoria = categoria;
		this.canal = canal;
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
