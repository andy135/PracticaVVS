package es.udc.fi.PracticaVVS.contenidos;

import java.util.ArrayList;
import java.util.List;

import es.udc.fi.PracticaVVS.utiles.DuracionErroneaCancionException;

public class Cancion extends ContenidoSimple {

	public Cancion(String titulo, long duracion)
			throws DuracionErroneaCancionException {
		super(titulo, Math.abs(duracion));
		if (duracion == 0) {
			throw new DuracionErroneaCancionException();
		}
	}

	public List<Contenido> buscar(String subcadena) {
		return null;
	}

	public void agregar(Contenido contenidoSimple, Contenido predecesor) {
	}

	public void eliminar(Contenido contenidoSimple) {
	}

	public List<Contenido> obtenerListaReproduccion() {
		ArrayList<Contenido> lista = new ArrayList<Contenido>();
		lista.add(this);
		return lista;
	}

}
