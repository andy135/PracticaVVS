package es.udc.fi.PracticaVVS.contenidos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.DuracionErroneaCancionException;
import es.udc.fi.PracticaVVS.utiles.TituloErroneoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;

public class Cancion extends ContenidoSimple {

	public Cancion(@Nonnull String titulo, long duracion)throws DuracionErroneaCancionException, TituloErroneoException {
		super(titulo, duracion);
	}

	public List<Contenido> buscar(@Nonnull String subcadena) throws CadenaErroneaException {
		if(subcadena == null) throw new CadenaErroneaException();
		ArrayList<Contenido> lista = new ArrayList<Contenido>();
        if (super.obtenerTitulo().contains(subcadena)){
			lista.add(this);
		}
		return lista;
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
