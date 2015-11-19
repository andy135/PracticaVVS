package es.udc.fi.PracticaVVS;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.udc.fi.PracticaVVS.contenidos.Anuncio;
import es.udc.fi.PracticaVVS.contenidos.Cancion;
import es.udc.fi.PracticaVVS.contenidos.Contenido;
import es.udc.fi.PracticaVVS.contenidos.Emisora;
import es.udc.fi.PracticaVVS.servidores.ServidorRespaldado;
import es.udc.fi.PracticaVVS.servidores.ServidorSimple;
import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.DuracionErroneaCancionException;
import es.udc.fi.PracticaVVS.utiles.Token;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;

public class IntegracionTest {

	private Token specialToken = new Token("Especial");
	private ServidorSimple servidorSimple = new ServidorSimple("Prueba",
			specialToken);
	private ServidorSimple respaldo = new ServidorRespaldado("Respaldado",
			specialToken, servidorSimple);

	//Inicio Tests Particiones Equivalentes: Contenido
	
	@Test
	public void testAgregarYBuscarCancion() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException, DuracionErroneaCancionException {
		Contenido Contenido = new Cancion("Cancion1", 1);
		Token token = servidorSimple.alta();
		servidorSimple.agregar(Contenido, specialToken);
		assertTrue(servidorSimple.buscar("Ca", token).contains(Contenido));		
	}
	
	@Test
	public void testAgregarYBuscarAnuncio() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException {
		Contenido Contenido = new Anuncio();
		Token token = servidorSimple.alta();
		servidorSimple.agregar(Contenido, specialToken);
		assertTrue(servidorSimple.buscar("PU", token).contains(Contenido));		
	}
	
	@Test
	public void testAgregarYBuscarEmisora() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException {
		Contenido Contenido = new Emisora("Europa FM");
		Token token = servidorSimple.alta();
		servidorSimple.agregar(Contenido, specialToken);
		assertTrue(servidorSimple.buscar("FM", token).contains(Contenido));		
	}
	
	//Fin Tests Particiones Equivalentes: Contenido	
}
