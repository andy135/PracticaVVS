package es.udc.fi.PracticaVVS.contenidos;

import java.util.ArrayList;
import java.util.List;

public class Cancion extends Contenido {

	public List<Contenido> buscar(String subcadena) {
		return null;
	}

	public void agregar(Contenido contenido, Contenido predecesor) {
	}

	public void eliminar(Contenido contenido) {		
	}

	public List<Contenido> obtenerListaReproduccion() {
		ArrayList<Contenido> lista = new ArrayList<Contenido>();
		lista.add(this);
		return lista;
	}
	
}
