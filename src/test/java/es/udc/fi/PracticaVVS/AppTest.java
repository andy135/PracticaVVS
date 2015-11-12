package es.udc.fi.PracticaVVS;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fi.PracticaVVS.contenidos.Anuncio;
import es.udc.fi.PracticaVVS.contenidos.Cancion;
import es.udc.fi.PracticaVVS.contenidos.Contenido;
import es.udc.fi.PracticaVVS.contenidos.Emisora;
import es.udc.fi.PracticaVVS.servidores.ServidorSimple;
import es.udc.fi.PracticaVVS.servidores.ServidorRespaldado;
import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.Token;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private Token specialToken = new Token("Especial");
	private ServidorSimple servidorSimple = new ServidorSimple("Prueba", specialToken);
	private ServidorSimple respaldo = new ServidorRespaldado("Respaldado", specialToken, servidorSimple);

	// Tests Servidores
	
	@Test
	public void testAltaBaja() throws UnexistingTokenException {
		Token tokenCreado = servidorSimple.alta();
		assertTrue(servidorSimple.existeToken(tokenCreado));
		servidorSimple.baja(tokenCreado);
		assertFalse(servidorSimple.existeToken(tokenCreado));
	}

	@Test(expected = UnexistingTokenException.class)
	public void testBajaTokenErroneo() throws UnexistingTokenException {
		servidorSimple.baja(new Token());
	}
	
	@Test
	public void testObtenerNombre() {
		assertEquals("Prueba",servidorSimple.obtenerNombre());
		assertEquals("Respaldado",respaldo.obtenerNombre());
	}
	
	@Test
	public void testAgregarBuscar() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException {
		Contenido contenido = new Cancion();
		Token token = servidorSimple.alta();
		contenido.setTitulo("Cancion1");
		servidorSimple.agregar(contenido, specialToken);
		assertTrue(servidorSimple.buscar("Ca", token).contains(contenido));
	}
	
	@Test
	public void testAgregarBuscarVarios() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException {
		
		Token token = servidorSimple.alta();
		
		Contenido contenido = new Cancion();
		Contenido contenido1 = new Cancion();
		Contenido contenido2 = new Cancion();
		contenido.setTitulo("Cancion1");
		contenido1.setTitulo("Cancion2");
		contenido2.setTitulo("asd");		
		servidorSimple.agregar(contenido, specialToken);		
		servidorSimple.agregar(contenido1, specialToken);
		servidorSimple.agregar(contenido2, specialToken);
		
		assertTrue(servidorSimple.buscar("Ca", token).contains(contenido));
		assertTrue(servidorSimple.buscar("Ca", token).contains(contenido1));
		assertFalse(servidorSimple.buscar("Ca", token).contains(contenido2));
	}

	@Test
	public void testBuscarConPublicidad() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException {
		Contenido contenido = new Cancion();
		Token token = servidorSimple.alta();
		token.setCount(0);
		contenido.setTitulo("Cancion1");
		servidorSimple.agregar(contenido, specialToken);
		assertTrue(servidorSimple.buscar("Ca", token).size()==2);
	}
	
	@Test
	public void testBuscarEnRespaldo() throws UnexistingTokenException,
			CadenaErroneaException, UnexistingContenidoException {
		Contenido contenido = new Cancion();
		Token token = respaldo.alta();
		contenido.setTitulo("Cancion1");
		servidorSimple.agregar(contenido, specialToken);
		assertTrue(respaldo.buscar("Ca", token).contains(contenido));
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
	public void testEliminarContenidoErroneo()
			throws UnexistingTokenException,
			UnexistingContenidoException {
		Anuncio anuncio = new Anuncio();
		servidorSimple.eliminar(anuncio, specialToken);
	}

	//Test Contenidos
	@Test
	public void testObtenerTitulo(){
		Contenido c = new Anuncio();
		assertEquals(c.obtenerTitulo(),"PUBLICIDAD");
		c = new Cancion();
		c.setTitulo("HOLA");
		assertEquals(c.obtenerTitulo(),"HOLA");
		c = new Emisora();
		c.setTitulo("HOLA");
		assertEquals(c.obtenerTitulo(),"HOLA");
	}
	
	@Test
	public void testObtenerDuracion(){
		Contenido a = new Anuncio();
		assertEquals(a.obtenerDuracion(),5);
		Contenido c = new Cancion();
		c.setDuracion(3);
		assertEquals(c.obtenerDuracion(),3);
		Contenido e = new Emisora();
		e.agregar(c, null);
		e.agregar(a, null);
		assertEquals(e.obtenerDuracion(),8);
	}
	
	@Test
	public void testObtenerListaReproduccion(){
		Contenido a = new Anuncio();
		Contenido c = new Cancion();
		c.setDuracion(3);
		Contenido e = new Emisora();
		e.agregar(c, null);
		e.agregar(a, null);
		assertEquals(e.obtenerListaReproduccion().size(),2);
	}
	
	@Test
	public void testAgregarBuscarEliminarContenidoEnEmisora() throws UnexistingTokenException, CadenaErroneaException, UnexistingContenidoException{
		Contenido e = new Emisora();
		e.setTitulo("Emisora1");
		
		// Agregar
		
		Contenido c1 = new Cancion();
		c1.setDuracion(3);
		c1.setTitulo("Cancion 1");
		
		Contenido c2 = new Cancion();
		c2.setDuracion(4);
		c2.setTitulo("Cancion 2");
		
		e.agregar(c1, null);
		e.agregar(c2, c1);
		
		assertEquals(7,e.obtenerDuracion());
		
		// Buscar
		
		List<Contenido> r = e.buscar("nc");
		
		assertEquals(2,r.size());
		assertTrue(r.contains(c1));
		assertTrue(r.contains(c2));
		
		// Eliminar
		
		e.eliminar(c1);
		List<Contenido> r2 = e.buscar("nc");
		
		assertTrue(r2.size()==1);

	}

}
