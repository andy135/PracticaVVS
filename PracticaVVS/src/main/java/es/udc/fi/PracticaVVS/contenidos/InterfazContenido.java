package es.udc.fi.PracticaVVS.contenidos;

import java.util.List;

public interface InterfazContenido {
	public String obtenerTitulo();
	public long obtenerDuracion();
	public List<InterfazContenido> obtenerListaReproduccion();
	public List<InterfazContenido> buscar(String subcadena);
	public void agregar(InterfazContenido contenido, InterfazContenido predecesor);
	public void eliminar(InterfazContenido contenido);
	
}
