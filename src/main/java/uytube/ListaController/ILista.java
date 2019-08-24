package uytube.ListaController;

public interface ILista {

	public void crearLista(String listaName,String categoria,String nombreUser,boolean privado,boolean Default);
	public void modificarLista();
	public void consultarListas();
	public void agregarVideo();
	public void quitarVideo();

}
