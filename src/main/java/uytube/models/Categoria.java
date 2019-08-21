package uytube.models;

import java.util.GregorianCalendar;
import java.util.List;


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
	public Categoria() {}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	
}