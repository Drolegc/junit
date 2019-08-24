package uytube.models;

import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "Lista")
public class Lista {
	
	@Id
	@Column( name = "nombre" )
	private String nombre;
	@Column( name = "privado ")
	private Boolean privado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nombre",nullable=true)
	private Categoria categoria;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Video> videos;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nombre")
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
		this.nombre = nombre;
		this.privado = privado;
		this.categoria = categoria;
		this.canal = canal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
