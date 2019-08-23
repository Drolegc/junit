package uytube.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import uytube.models.Canal;
@Entity
@Table(name = "Usuario")
public class Usuario {
		
	@Id
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "fnacimiento")
	private Date fnacimiento;
	
	@Column(name = "img")
	private String img;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nombre")
	private List<Canal> canalesSeguidos;

	
	public Usuario() {
		
	}

	public Usuario(String nickname, String nombre, String apellido, String correo, Date fnacimiento, String img) {
		
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fnacimiento = fnacimiento;
		this.img = img;
		//canalesSeguidos = new ArrayList<Canal>();
		
	}

	public List<Canal> getCanalesSeguidos() {
		return canalesSeguidos;
	}

	public void addCanal(Canal c) {
		this.canalesSeguidos.add(c);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFnacimiento() {
		return fnacimiento;
	}

	public void setFnacimiento(Date fnacimiento) {
		this.fnacimiento = fnacimiento;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
