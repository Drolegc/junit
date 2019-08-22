package uytube.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Canal")
public class Canal {
	
	@Id
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Lista> listasReproduccion;
	
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
	public List<Lista> getListasReproduccion() {
		return listasReproduccion;
	}
	public void setListasReproduccion(List<Lista> listasReproduccion) {
		this.listasReproduccion = listasReproduccion;
	}
	
	public Canal() {
		
	}
	
	public Canal(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.listasReproduccion = new ArrayList<Lista>();
	}
	
	public void addLista(Lista l) {
		this.listasReproduccion.add(l);
		System.out.println("Nueva lista agregada");
	}
}
