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
import es.udc.fi.PracticaVVS.utiles.Token;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;

public class ServidorTest {
	private Token specialToken = new Token("Especial");
	//private ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
	//private ServidorSimple respaldo = new ServidorRespaldado("Respaldado",specialToken, servidorSimple);
	
	@Test
	public void testObtenerNombre() {
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		ServidorSimple respaldo = new ServidorRespaldado("Respaldado",specialToken, servidorSimple);
		assertEquals("Prueba", servidorSimple.obtenerNombre());
		assertEquals("Respaldado", respaldo.obtenerNombre());
	}
	
	@Test
	public void testExisteToken(){
		ServidorSimple servidorSimple = new ServidorSimple("Prueba", specialToken);
		Token t = servidorSimple.alta();
		assertTrue(servidorSimple.existeToken(t));
		assertFalse(servidorSimple.existeToken(new Token()));
	}
	
	@Test
	public void testAltaBajaExisteToken() throws UnexistingTokenException{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba", specialToken);
		Token t = servidorSimple.alta();
		assertTrue(servidorSimple.existeToken(t));
		servidorSimple.baja(t);
		assertFalse(servidorSimple.existeToken(t));
	}
	
	@Test(expected=UnexistingTokenException.class)
	public void testBajaUnexistingToken() throws UnexistingTokenException{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba", specialToken);
		servidorSimple.baja(new Token());
	}

	// Inicio Cobertura de instrucciones

	@Test(expected = UnexistingTokenException.class)
	public void testAgregarTokenErroneo() throws Exception {
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		Contenido c = new Cancion("hola",5);
		servidorSimple.agregar(c, new Token());
	}
	
	@Test(expected = UnexistingTokenException.class)
	public void testAgregarTokenNull() throws Exception {
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		Contenido c = new Cancion("hola",5);
		servidorSimple.agregar(c, null);
	}
	
	@Test
	public void testAgregar() throws Exception {
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		Contenido c = new Cancion("hola",5);
		servidorSimple.agregar(c, specialToken);
	}
	
	@Test(expected=UnexistingContenidoException.class)
	public void testAgregarContenidoNull() throws Exception {
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		servidorSimple.agregar(null, specialToken);
	}

	@Test(expected = CadenaErroneaException.class)
	public void testBuscarCadenaErronea() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		servidorSimple.buscar(null, new Token());
	}

	@Test(expected = UnexistingTokenException.class)
	public void testBuscarTokenErroneo() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		servidorSimple.buscar("PUBLICIDAD", new Token());
	}

	@Test
	public void testBuscarTokenVacio() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		Contenido Contenido = new Cancion("Cancion1", 3);
		Token token = servidorSimple.alta();
		token.setCount(0);
		servidorSimple.agregar(Contenido, specialToken);
		assertTrue(servidorSimple.buscar("Ca", token).size() == 2);
	}
	
	@Test
	public void testAgregarBuscarVarios() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);		
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
	
	@Test(expected = UnexistingTokenException.class)
	public void testEliminarTokenErroneo() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		Anuncio anuncio = new Anuncio();
		servidorSimple.eliminar(anuncio, new Token());
	}
	
	@Test(expected = UnexistingTokenException.class)
	public void testEliminarTokenNull() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		Anuncio anuncio = new Anuncio();
		servidorSimple.eliminar(anuncio, null);
	}

	@Test(expected = UnexistingContenidoException.class)
	public void testEliminarContenidoErroneo() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);
		Anuncio anuncio = new Anuncio();
		servidorSimple.eliminar(anuncio, specialToken);
	}
	
	@Test
	public void testEliminar() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);		
		Token token = servidorSimple.alta();

		Contenido contenido = new Cancion("Cancion1", 1);
		servidorSimple.agregar(contenido, specialToken);
		servidorSimple.eliminar(contenido, specialToken);
		
		assertFalse(servidorSimple.buscar("Ca", token).contains(contenido));
	}
	
	//Tests servidores respaldo
	
	@Test
	public void testBuscarRespaldoNull() throws Exception{
		ServidorSimple respaldado = new ServidorRespaldado("Respaldado",specialToken, null);
		Contenido contenido = new Cancion("Cancion1", 1);
		respaldado.agregar(contenido, specialToken);
		assertEquals(respaldado.buscar("Ca", respaldado.alta()).size(),1);
		assertEquals(respaldado.buscar("po", respaldado.alta()).size(),0);
	}
	
	@Test
	public void testAgregarBuscarVariosRespaldado() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);		
		ServidorSimple respaldado = new ServidorRespaldado("Respaldado",specialToken, servidorSimple);
		Token token = respaldado.alta();

		Contenido contenido = new Cancion("Cancion1", 1);
		Contenido contenido1 = new Cancion("Cancion2", 1);
		Contenido contenido2 = new Cancion("asd", 1);
		servidorSimple.agregar(contenido, specialToken);
		respaldado.agregar(contenido1, specialToken);
		servidorSimple.agregar(contenido2, specialToken);

		assertFalse(respaldado.buscar("Ca", token).contains(contenido));
		assertTrue(respaldado.buscar("Ca", token).contains(contenido1));
		assertFalse(respaldado.buscar("Ca", token).contains(contenido2));
	}	
	
	@Test
	public void testAgregarBuscarVariosRespaldadoVacio() throws Exception{
		ServidorSimple servidorSimple = new ServidorSimple("Prueba",specialToken);		
		ServidorSimple respaldado = new ServidorRespaldado("Respaldado",specialToken, servidorSimple);
		Token token = respaldado.alta();

		Contenido contenido = new Cancion("Cancion1", 1);
		Contenido contenido1 = new Cancion("Cancion2", 1);
		Contenido contenido2 = new Cancion("asd", 1);
		servidorSimple.agregar(contenido, specialToken);
		servidorSimple.agregar(contenido1, specialToken);
		servidorSimple.agregar(contenido2, specialToken);

		assertTrue(respaldado.buscar("Ca", token).contains(contenido));
		assertTrue(respaldado.buscar("Ca", token).contains(contenido1));
		assertFalse(respaldado.buscar("Ca", token).contains(contenido2));
	}

}
