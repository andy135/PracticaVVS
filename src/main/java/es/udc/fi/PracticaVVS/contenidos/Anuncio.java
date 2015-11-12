package es.udc.fi.PracticaVVS.contenidos;

import java.util.ArrayList;
import java.util.List;

public class Anuncio extends ContenidoSimple {	

	public Anuncio(){
		super("PUBLICIDAD", 5);
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
