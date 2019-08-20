package uytube.models;

import javax.persistence.*;


@Entity
@Table(name = "Categoria")
public class Categoria {
	
	@Id
	@Column(name = "nombre")
	private String nombre;
	
	public Categoria (String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}