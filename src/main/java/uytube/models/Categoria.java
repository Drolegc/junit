package uytube.models;

import java.util.GregorianCalendar;
import java.util.List;


import javax.persistence.*;


@Entity
@Table(name = "Categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",insertable = false,updatable = false)
	private int id;
	
	
	@Column(name = "nombre")
	private String nombre; 
		
	public Categoria (String nombre) {
		this.nombre = nombre;
	}
	public Categoria() {}
	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	
}