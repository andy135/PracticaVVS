package es.udc.fi.PracticaVVS;

import static org.junit.Assert.*;

import org.junit.Test;

import es.udc.fi.PracticaVVS.contenidos.Anuncio;
import es.udc.fi.PracticaVVS.servidores.Servidor;
import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.Token;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private Token specialToken = new Token("Especial");
	private Servidor servidor = new Servidor("Prueba", specialToken);

	// Tests Alta

	@Test
	public void testAlta() {
		Token tokenCreado = servidor.alta();
		assertTrue(servidor.existeToken(tokenCreado));
	}

	// Tests Baja

	@Test
	public void testBaja() throws UnexistingTokenException {
		Token tokenCreado = servidor.alta();
		servidor.baja(tokenCreado);
		assertFalse(servidor.existeToken(tokenCreado));
	}

	@Test(expected = UnexistingTokenException.class)
	public void testBajaTokenErroneo() throws UnexistingTokenException {
		servidor.baja(new Token());
	}

	// Tests Agregar

	@Test
	public void testAgregarBuscarEliminar() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException {
		Anuncio anuncio = new Anuncio();
		Token tokenCreado = servidor.alta();
		servidor.agregar(anuncio, specialToken);
		assertEquals(1, servidor.buscar("PUBLICIDAD", tokenCreado).size());
		servidor.eliminar(anuncio, specialToken);
		servidor.baja(tokenCreado);
	}

	@Test(expected = UnexistingTokenException.class)
	public void testAgregarTokenErroneo() throws UnexistingTokenException {
		servidor.agregar(null, new Token());
	}

	// Tests Buscar

	@Test(expected = CadenaErroneaException.class)
	public void testBuscarCadenaErronea() throws UnexistingTokenException,
			CadenaErroneaException {
		servidor.buscar(null, new Token());
	}

	@Test(expected = UnexistingTokenException.class)
	public void testBuscarTokenErroneo() throws UnexistingTokenException,
			CadenaErroneaException {
		servidor.buscar("PUBLICIDAD", new Token());
	}

	// Tests Eliminar

	@Test(expected = UnexistingTokenException.class)
	public void testEliminarTokenErroneo() throws UnexistingTokenException,
			UnexistingContenidoException {
		servidor.eliminar(null, new Token());
	}
	
	@Test(expected = UnexistingContenidoException.class)
	public void testEliminarContenidoErroneo()
			throws UnexistingTokenException,
			UnexistingContenidoException {
		Anuncio anuncio = new Anuncio();
		servidor.eliminar(anuncio, specialToken);
	}

}
