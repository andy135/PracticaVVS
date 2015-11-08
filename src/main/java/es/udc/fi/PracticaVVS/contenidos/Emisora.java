package es.udc.fi.PracticaVVS.contenidos;

import java.util.ArrayList;
import java.util.List;

import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;

public class Emisora extends Contenido {

	private List<Contenido> listaReproduccion = new ArrayList();
	
	public List<Contenido> obtenerListaReproduccion() {
		return listaReproduccion;
	}

	public List<Contenido> buscar(String subcadena) throws CadenaErroneaException {
		if(subcadena==null) throw new CadenaErroneaException();
		ArrayList<Contenido> list = new ArrayList();
		for(Contenido c: listaReproduccion){
			if(c.obtenerTitulo().contains(subcadena)){
				list.add(c);
			}
		}
		return list;
	}

	public void agregar(Contenido contenido, Contenido predecesor) {
		int pos = listaReproduccion.indexOf(predecesor);
		if(pos>=0){
			listaReproduccion.add(pos+1,contenido);
		}else{
			listaReproduccion.add(0, contenido);
		}
	}

	public void eliminar(Contenido contenido) throws UnexistingContenidoException {
		if(listaReproduccion.contains(contenido)){
			listaReproduccion.remove(contenido);
		}else throw new UnexistingContenidoException();	
	}

}
