package es.udc.fi.PracticaVVS.servidores;

import java.util.List;

import es.udc.fi.PracticaVVS.contenidos.Contenido;
import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.DuracionErroneaCancionException;
import es.udc.fi.PracticaVVS.utiles.TituloErroneoException;
import es.udc.fi.PracticaVVS.utiles.Token;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;

public interface Servidor {
	public String obtenerNombre();
	public Token alta();
	public void baja(Token token) throws UnexistingTokenException;
	public void agregar(Contenido Contenido, Token token) throws UnexistingTokenException;
	public void eliminar(Contenido Contenido, Token token) throws UnexistingTokenException, UnexistingContenidoException;
	public List<Contenido> buscar(String subcadena, Token token) throws UnexistingTokenException, CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException;
}
