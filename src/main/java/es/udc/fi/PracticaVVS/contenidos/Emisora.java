package es.udc.fi.PracticaVVS.contenidos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.DuracionErroneaCancionException;
import es.udc.fi.PracticaVVS.utiles.TituloErroneoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;

public class Emisora extends ContenidoSimple {

	private List<Contenido> listaReproduccion;

	public Emisora(@Nonnull String titulo) throws DuracionErroneaCancionException, TituloErroneoException {
		super(titulo, 0);
		this.listaReproduccion = new ArrayList<Contenido>();
	}

	public List<Contenido> obtenerListaReproduccion() {
		return listaReproduccion;
	}

	public List<Contenido> buscar(@Nonnull String subcadena)
			throws CadenaErroneaException {
		if (subcadena == null) throw new CadenaErroneaException();
		ArrayList<Contenido> list = new ArrayList<Contenido>();
		for (Contenido c : listaReproduccion) {
			if (c.obtenerTitulo().contains(subcadena)) {
				list.add(c);
			}
		}
		return list;
	}

	public void agregar(@Nonnull Contenido contenidoSimple, Contenido predecesor) throws UnexistingContenidoException {
		if(contenidoSimple==null) throw new UnexistingContenidoException();
		int pos = listaReproduccion.indexOf(predecesor);
		if (pos >= 0) {
			listaReproduccion.add(pos + 1, contenidoSimple);
		} else {
			listaReproduccion.add(0, contenidoSimple);
		}
		this.setDuracion(this.obtenerDuracion()
				+ contenidoSimple.obtenerDuracion());
	}

	public void eliminar(@Nonnull Contenido contenidoSimple) throws UnexistingContenidoException {
		if (listaReproduccion.contains(contenidoSimple)) {
			listaReproduccion.remove(contenidoSimple);
			this.setDuracion(this.obtenerDuracion()
					- contenidoSimple.obtenerDuracion());
		} else
			throw new UnexistingContenidoException();
	}

}
