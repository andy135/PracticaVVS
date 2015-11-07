package es.udc.fi.PracticaVVS.contenidos;

import java.util.List;

import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;

public interface InterfazContenido {
	public String obtenerTitulo();
	public long obtenerDuracion();
	public List<Contenido> obtenerListaReproduccion();
	public List<Contenido> buscar(String subcadena) throws CadenaErroneaException;
	public void agregar(Contenido contenido, Contenido predecesor);
	public void eliminar(Contenido contenido) throws UnexistingContenidoException;
	
}
