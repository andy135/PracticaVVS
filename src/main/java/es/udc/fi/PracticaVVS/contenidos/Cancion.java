package es.udc.fi.PracticaVVS.contenidos;

import java.util.ArrayList;
import java.util.List;

import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;

public class Cancion extends ContenidoSimple {

	public Cancion(String titulo, long duracion) {
		super(titulo, duracion);
	}

	public List<Contenido> buscar(String subcadena) {
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
