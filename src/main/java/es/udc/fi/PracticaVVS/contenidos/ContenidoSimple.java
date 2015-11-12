package es.udc.fi.PracticaVVS.contenidos;

public abstract class ContenidoSimple implements Contenido{
	
	private String titulo;
	private long duracion;
	
	public ContenidoSimple(String titulo, long duracion) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
	}

	public String obtenerTitulo() {
		return titulo;
	}

	public long obtenerDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion){
		this.duracion = duracion;
	}
	
}
