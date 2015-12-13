package es.udc.fi.PracticaVVS;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import es.udc.fi.PracticaVVS.contenidos.Anuncio;
import es.udc.fi.PracticaVVS.contenidos.Cancion;
import es.udc.fi.PracticaVVS.contenidos.Contenido;
import es.udc.fi.PracticaVVS.contenidos.Emisora;
import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.DuracionErroneaCancionException;
import es.udc.fi.PracticaVVS.utiles.TituloErroneoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;

public class ContenidoTest {

	//Tests valores frontera para Titulo y Duración
	@Test
	public void testObtenerTitulo() throws Exception {
		Contenido c = new Anuncio();
		assertEquals(c.obtenerTitulo(), "PUBLICIDAD");
		c = new Cancion("HOLA", 4);
		assertEquals(c.obtenerTitulo(), "HOLA");
		c = new Emisora("HOLA");
		assertEquals(c.obtenerTitulo(), "HOLA");
	}
	
	@Test(expected=TituloErroneoException.class)
	public void testObtenerTituloNull() throws Exception{
		Contenido c;
		c = new Cancion(null, 4);
		assertEquals(c.obtenerTitulo(), null);
		c = new Emisora(null);
		assertEquals(c.obtenerTitulo(), null);
	}

	@Test
	public void testObtenerDuracion() throws Exception {
		Contenido a = new Anuncio();
		assertEquals(a.obtenerDuracion(), 5);
		Contenido c = new Cancion("H", 3);
		assertEquals(c.obtenerDuracion(), 3);
		Contenido e = new Emisora("E");
		e.agregar(c, null);
		e.agregar(a, null);
		assertEquals(e.obtenerDuracion(), 8);
	}

	@Test(expected=DuracionErroneaCancionException.class)
	public void testCancionDuracionNegativa()throws Exception {
		Contenido c = new Cancion("Cancion 1", -5);
	}

	@Test
	public void testCancionDuracionPositiva()throws Exception {
		Contenido c = new Cancion("Cancion 1", 5);
		assertEquals(c.obtenerDuracion(), 5);
	}

	@Test(expected = DuracionErroneaCancionException.class)
	public void testCancionDuracionErronea()throws Exception {
		new Cancion("Cancion 1", -5);
	}
	
	//Fin Tests valores frontera para Titulo y Duración
	
	@Test
	public void testObtenerListaReproduccionCancionAnuncio()
			throws Exception {
		Contenido a = new Anuncio();
		assertTrue(a.obtenerListaReproduccion().contains(a));
		
		Contenido c = new Cancion("P", 5);
		assertTrue(c.obtenerListaReproduccion().contains(c));		
	}
	
	@Test
	public void testObtenerListaReproduccionEmisora()
			throws Exception {
		Contenido a = new Anuncio();		
		Contenido c = new Cancion("P", 5);
		Contenido e = new Emisora("E");
		
		e.agregar(a, null);
		e.agregar(c, null);
		
		assertTrue(e.obtenerListaReproduccion().contains(c));
		assertTrue(e.obtenerListaReproduccion().contains(a));
		assertEquals(e.obtenerListaReproduccion().size(),2);		
	}
	

	//Tests Valores frontera para el metodo buscar de Canciones y Anuncios
	
	@Test(expected=CadenaErroneaException.class)
	public void testBuscarCadenaNullAnuncio() throws CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException{
		Contenido a = new Anuncio();
		a.buscar(null);
	}
	
	@Test(expected=CadenaErroneaException.class)
	public void testBuscarCadenaNullCancion() throws CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException{
		Contenido c = new Cancion("hola", 5);
		c.buscar(null);
	}
	
	@Test
	public void testBuscarCadenaVacia() throws CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException{
		Contenido a = new Anuncio();
		assertTrue(a.buscar("").contains(a));
		
		Contenido c = new Cancion("hola",5);
		assertTrue(c.buscar("").contains(c));
		
	}
	
	@Test
	public void testBuscarCadenaPerteneciente() throws CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException{
		Contenido a = new Anuncio();
		assertTrue(a.buscar("UBLI").contains(a));
		
		Contenido c = new Cancion("hola",5);
		assertTrue(c.buscar("ol").contains(c));
		
	}
	
	@Test
	public void testBuscarCadenaNoPerteneciente() throws CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException{
		Contenido a = new Anuncio();
		assertEquals(a.buscar("ol").size(),0);
		
		Contenido c = new Cancion("hola",5);
		assertEquals(c.buscar("UBLI").size(),0);
		
	}
	
	//Fin Tests Valores frontera para el metodo buscar de Canciones y Anuncios
	
	//Tests Valores frontera para el metodo buscar de Emisoras
	
	@Test(expected=CadenaErroneaException.class)
	public void testBuscarCadenaNullEmisora() throws CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException{
		Contenido e = new Emisora("holi");
		e.buscar(null);
	}
	
	@Test
	public void testBuscarCadenaVaciaEmisora() throws CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException, UnexistingContenidoException{
		Contenido e = new Emisora("holi");
		Contenido a = new Anuncio();
		e.agregar(a, null);
		assertTrue(e.buscar("").contains(a));
	}
	
	@Test
	public void testBuscarCadenaPertenecienteEmisora() throws CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException, UnexistingContenidoException{
		Contenido e = new Emisora("holi");
		Contenido a = new Anuncio();
		Contenido c = new Cancion("saca",5);
		e.agregar(a, null);
		e.agregar(c, null);
		assertTrue(e.buscar("UB").contains(a));
		assertFalse(e.buscar("UB").contains(c));
	}
	
	@Test
	public void testBuscarCadenaNoPertenecienteEmisora() throws CadenaErroneaException, DuracionErroneaCancionException, TituloErroneoException{
		Contenido e = new Emisora("holi");
		Contenido a = new Anuncio();
		Contenido c = new Cancion("saca",5);
		assertFalse(e.buscar("UB").contains(a));
		assertFalse(e.buscar("sa").contains(c));		
	}
	
	//Fin Tests Valores frontera para el metodo buscar de Emisoras
	
	//Tests Valores frontera para el metodo agregar de Emisoras
	
	@Test
	public void testAgregarTodoNull() throws DuracionErroneaCancionException, TituloErroneoException{
		Emisora e = new Emisora("E");
	}
	
	@Test(expected=UnexistingContenidoException.class)
	public void testAgregarTodoContenidoNull() throws DuracionErroneaCancionException, TituloErroneoException, UnexistingContenidoException{
		Emisora e = new Emisora("E");
		e.agregar(null, null);
	}
	
	@Test
	public void testAgregarTodoPredecesorNull() throws DuracionErroneaCancionException, TituloErroneoException, UnexistingContenidoException{
		Emisora e = new Emisora("E");
		Anuncio a = new Anuncio();
		e.agregar(a, null);
		assertTrue(e.obtenerListaReproduccion().get(0).equals(a));
	}
	
	@Test
	public void testAgregarTodoPredecesorExistente() throws DuracionErroneaCancionException, TituloErroneoException, UnexistingContenidoException{
		Emisora e = new Emisora("E");
		Anuncio a = new Anuncio();
		Anuncio pred = new Anuncio();
		e.agregar(pred, null);
		e.agregar(a, pred);
		assertTrue(e.obtenerListaReproduccion().get(e.obtenerListaReproduccion().indexOf(pred)+1).equals(a));
	}
	
	@Test
	public void testAgregarTodoPredecesorInexistente() throws DuracionErroneaCancionException, TituloErroneoException, UnexistingContenidoException{
		Emisora e = new Emisora("E");
		Anuncio a = new Anuncio();
		Anuncio pred = new Anuncio();
		e.agregar(a, pred);
		assertTrue(e.obtenerListaReproduccion().get(0).equals(a));
	}
	
	//Fin Tests Valores frontera para el metodo agregar de Emisoras
	
	//Tests Valores frontera para el metodo eliminar de Emisoras
	
	@Test(expected=UnexistingContenidoException.class)
	public void eliminarNull() throws Exception{
		Emisora e = new Emisora("E");
		e.eliminar(null);
	}
	
	@Test
	public void eliminarExistente() throws Exception{
		Emisora e = new Emisora("E");
		Anuncio a = new Anuncio();
		e.agregar(a, null);
		assertEquals(e.obtenerListaReproduccion().size(),1);
		e.eliminar(a);
		assertEquals(e.obtenerListaReproduccion().size(),0);
	}
	
	@Test(expected=UnexistingContenidoException.class)
	public void eliminarInexistente() throws Exception{
		Emisora e = new Emisora("E");
		Anuncio a = new Anuncio();
		e.eliminar(a);
	}
	
	//Fin Tests Valores frontera para el metodo eliminar de Emisoras
	
	//Tests integridad duracion emisoras al agregar y eliminar
	
	@Test
	public void integridadDuracion()throws Exception{
		Emisora e = new Emisora("E");
		Anuncio a = new Anuncio();
		Cancion c = new Cancion("h",8);
		assertEquals(e.obtenerDuracion(),0);
		e.agregar(a, null);
		assertEquals(e.obtenerDuracion(),5);
		e.agregar(c, null);
		assertEquals(e.obtenerDuracion(),13);
		e.eliminar(a);
		assertEquals(e.obtenerDuracion(),8);
		e.eliminar(c);
		assertEquals(e.obtenerDuracion(),0);
	}
	
	//Fin Tests integridad duracion emisoras al agregar y eliminar
	
}
