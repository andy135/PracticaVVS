package es.udc.fi.PracticaVVS.servidores;

import java.util.List;

import es.udc.fi.PracticaVVS.contenidos.Contenido;
public class ServidorRespaldado extends Servidor {

	private Servidor respaldo;
	
	public ServidorRespaldado(String nombre, Servidor respaldo) {
		super(nombre);
		this.respaldo = respaldo;
	}

	public String alta() {
		// TODO Auto-generated method stub
		return null;
	}

	public void baja(String token) {
		// TODO Auto-generated method stub
		
	}

	public void agregar(Contenido contenido, String token) {
		// TODO Auto-generated method stub
		
	}

	public void eliminar(Contenido contenido, String token) {
		// TODO Auto-generated method stub
		
	}

	public List<Contenido> buscar(String subcadena, String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
