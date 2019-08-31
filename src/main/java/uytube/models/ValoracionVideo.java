package uytube.models;
import javax.persistence.*;

@Entity
@Table(name = "ValoracionVideo")
public class ValoracionVideo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id", insertable=false, updatable=false)
	private int id;
	
	@Column(name= "valoracion")
	private int valoracion;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Video video;
	
	public ValoracionVideo() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}	
	
}
