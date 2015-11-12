package es.udc.fi.PracticaVVS.contenidos;

import java.util.ArrayList;
import java.util.List;

import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;

public class Emisora extends ContenidoSimple {

	private List<Contenido> listaReproduccion;
		
	public Emisora(String titulo) {
		super(titulo, 0);
		this.listaReproduccion = new ArrayList<Contenido>();
	}
	
	public List<Contenido> obtenerListaReproduccion() {
		return listaReproduccion;
	}

	public List<Contenido> buscar(String subcadena) throws CadenaErroneaException {
		if(subcadena==null) throw new CadenaErroneaException();
		ArrayList<Contenido> list = new ArrayList<Contenido>();
		for(Contenido c: listaReproduccion){
			if(c.obtenerTitulo().contains(subcadena)){
				list.add(c);
			}
		}
		return list;
	}

	public void agregar(Contenido contenidoSimple, Contenido predecesor) {
		int pos = listaReproduccion.indexOf(predecesor);
		if(pos>=0){
			listaReproduccion.add(pos+1,contenidoSimple);
		}else{
			listaReproduccion.add(0, contenidoSimple);
		}
		this.setDuracion(this.obtenerDuracion()+contenidoSimple.obtenerDuracion());
	}

	public void eliminar(Contenido contenidoSimple) throws UnexistingContenidoException {
		if(listaReproduccion.contains(contenidoSimple)){
			listaReproduccion.remove(contenidoSimple);
		}else throw new UnexistingContenidoException();	
	}

}
