package uytube.models;

import javax.persistence.*;
@Entity
@Table(name = "Lista")
public class Lista {
	
	@Id
	@Column( name = "nombre" )
	private String nombre;
	@Column( name = "privado ")
	private Boolean privado;
	@Column( name = "categoria")
	private String categoria;
	
	public Lista(String nombre,Boolean privado,String categoria) {
		this.nombre = nombre;
		this.privado = privado;
		this.categoria = categoria;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
