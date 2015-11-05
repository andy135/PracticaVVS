package es.udc.fi.PracticaVVS.servidores;

import java.util.List;

import es.udc.fi.PracticaVVS.contenidos.Contenido;

public interface InterfazServidor {
	public String obtenerNombre();
	public String alta();
	public void baja(String token);
	public void agregar(Contenido contenido, String token);
	public void eliminar(Contenido contenido, String token);
	public List<Contenido> buscar(String subcadena, String token);
}
