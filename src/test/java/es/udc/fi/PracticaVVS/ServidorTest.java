package es.udc.fi.PracticaVVS;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.udc.fi.PracticaVVS.contenidos.Anuncio;
import es.udc.fi.PracticaVVS.contenidos.Cancion;
import es.udc.fi.PracticaVVS.contenidos.Contenido;
import es.udc.fi.PracticaVVS.servidores.ServidorRespaldado;
import es.udc.fi.PracticaVVS.servidores.ServidorSimple;
import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.DuracionErroneaCancionException;
import es.udc.fi.PracticaVVS.utiles.Token;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;

public class ServidorTest {
	private Token specialToken = new Token("Especial");
	private ServidorSimple servidorSimple = new ServidorSimple("Prueba",
			specialToken);
	private ServidorSimple respaldo = new ServidorRespaldado("Respaldado",
			specialToken, servidorSimple);

	// Tests Servidores

	@Test
	public void testAltaBaja() throws UnexistingTokenException {
		Token tokenCreado = servidorSimple.alta();
		assertTrue(servidorSimple.existeToken(tokenCreado));
		servidorSimple.baja(tokenCreado);
		assertFalse(servidorSimple.existeToken(tokenCreado));
	}

	// Inicio Cobertura de instrucciones
	
	@Test(expected = UnexistingTokenException.class)
	public void testBajaTokenErroneo() throws UnexistingTokenException {
		servidorSimple.baja(new Token());
	}

	@Test(expected = UnexistingTokenException.class)
	public void testAgregarTokenErroneo() throws UnexistingTokenException {
		servidorSimple.agregar(null, new Token());
	}

	@Test(expected = CadenaErroneaException.class)
	public void testBuscarCadenaErronea() throws UnexistingTokenException,
			CadenaErroneaException {
		servidorSimple.buscar(null, new Token());
	}

	@Test(expected = UnexistingTokenException.class)
	public void testBuscarTokenErroneo() throws UnexistingTokenException,
			CadenaErroneaException {
		servidorSimple.buscar("PUBLICIDAD", new Token());
	}

	@Test(expected = UnexistingTokenException.class)
	public void testEliminarTokenErroneo() throws UnexistingTokenException,
			UnexistingContenidoException {
		servidorSimple.eliminar(null, new Token());
	}

	@Test(expected = UnexistingContenidoException.class)
	public void testEliminarContenidoErroneo() throws UnexistingTokenException,
			UnexistingContenidoException {
		Anuncio anuncio = new Anuncio();
		servidorSimple.eliminar(anuncio, specialToken);
	}
	
	@Test
	public void testBuscarConPublicidad() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException,
			DuracionErroneaCancionException {
		Contenido Contenido = new Cancion("Cancion1", 3);
		Token token = servidorSimple.alta();
		token.setCount(0);
		servidorSimple.agregar(Contenido, specialToken);
		assertTrue(servidorSimple.buscar("Ca", token).size() == 2);
	}
	
	// Fin Cobertura de instrucciones
	
	@Test
	public void testObtenerNombre() {
		assertEquals("Prueba", servidorSimple.obtenerNombre());
		assertEquals("Respaldado", respaldo.obtenerNombre());
	}
	
	//public void testAgregarYBuscar eliminado

	@Test
	public void testAgregarBuscarVarios() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException,
			DuracionErroneaCancionException {

		Token token = servidorSimple.alta();

		Contenido Contenido = new Cancion("Cancion1", 1);
		Contenido contenido1 = new Cancion("Cancion2", 1);
		Contenido contenido2 = new Cancion("asd", 1);
		servidorSimple.agregar(Contenido, specialToken);
		servidorSimple.agregar(contenido1, specialToken);
		servidorSimple.agregar(contenido2, specialToken);

		assertTrue(servidorSimple.buscar("Ca", token).contains(Contenido));
		assertTrue(servidorSimple.buscar("Ca", token).contains(contenido1));
		assertFalse(servidorSimple.buscar("Ca", token).contains(contenido2));
	}	

	@Test
	public void testBuscarEnRespaldo() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException,
			DuracionErroneaCancionException {
		Contenido Contenido = new Cancion("Cancion1", 3);
		Token token = respaldo.alta();
		servidorSimple.agregar(Contenido, specialToken);
		assertTrue(respaldo.buscar("Ca", token).contains(Contenido));
	}	

}
