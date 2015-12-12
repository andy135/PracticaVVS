package es.udc.fi.PracticaVVS.contenidos;

import javax.annotation.Nonnull;

import es.udc.fi.PracticaVVS.utiles.DuracionErroneaCancionException;
import es.udc.fi.PracticaVVS.utiles.TituloErroneoException;

public abstract class ContenidoSimple implements Contenido {

	private String titulo;
	private long duracion;

	public ContenidoSimple(@Nonnull String titulo, long duracion) throws DuracionErroneaCancionException, TituloErroneoException {
		if (duracion < 0) {
			throw new DuracionErroneaCancionException();
		}
		if (titulo == null || titulo == "") {
			throw new TituloErroneoException();
		}
		this.titulo = titulo;
		this.duracion = duracion;
	}

	public String obtenerTitulo() {
		return titulo;
	}

	public long obtenerDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

}
