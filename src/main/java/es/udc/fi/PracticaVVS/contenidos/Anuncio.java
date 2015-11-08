package es.udc.fi.PracticaVVS.contenidos;

import java.util.List;

public class Anuncio extends Contenido {	

	public Anuncio(){
		setTitulo("PUBLICIDAD");
		setDuracion(5);
	}
	
	public List<Contenido> buscar(String subcadena) {
		return null;
	}

	public void agregar(Contenido contenido, Contenido predecesor) {	
	}

	public void eliminar(Contenido contenido) {		
	}

	public List<Contenido> obtenerListaReproduccion() {
		return null;
	}
	
}
