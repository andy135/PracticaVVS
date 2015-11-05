package es.udc.fi.PracticaVVS.contenidos;

public abstract class Contenido implements InterfazContenido{
	
	private String titulo;
	private long duracion;

	public String obtenerTitulo() {
		return titulo;
	}

	public long obtenerDuracion() {
		return duracion;
	}

}
