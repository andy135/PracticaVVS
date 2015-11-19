package es.udc.fi.PracticaVVS;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import es.udc.fi.PracticaVVS.contenidos.Anuncio;
import es.udc.fi.PracticaVVS.contenidos.Cancion;
import es.udc.fi.PracticaVVS.contenidos.Contenido;
import es.udc.fi.PracticaVVS.contenidos.Emisora;
import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.DuracionErroneaCancionException;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;

public class ContenidoTest {
	// Test Contenidos
	@Test
	public void testObtenerTitulo() throws DuracionErroneaCancionException {
		Contenido c = new Anuncio();
		assertEquals(c.obtenerTitulo(), "PUBLICIDAD");
		c = new Cancion("HOLA", 4);
		assertEquals(c.obtenerTitulo(), "HOLA");
		c = new Emisora("HOLA");
		assertEquals(c.obtenerTitulo(), "HOLA");
	}

	@Test
	public void testObtenerDuracion() throws DuracionErroneaCancionException {
		Contenido a = new Anuncio();
		assertEquals(a.obtenerDuracion(), 5);
		Contenido c = new Cancion("H", 3);
		assertEquals(c.obtenerDuracion(), 3);
		Contenido e = new Emisora("E");
		e.agregar(c, null);
		e.agregar(a, null);
		assertEquals(e.obtenerDuracion(), 8);
	}

	@Test
	public void testObtenerListaReproduccion()
			throws DuracionErroneaCancionException {
		Contenido a = new Anuncio();
		Contenido c = new Cancion("HOLA", 3);
		Contenido e = new Emisora("E");
		e.agregar(c, null);
		e.agregar(a, null);
		assertEquals(e.obtenerListaReproduccion().size(), 2);
	}

	@Test
	public void testAgregarBuscarEliminarContenidoEnEmisora()
			throws UnexistingTokenException, CadenaErroneaException,
			UnexistingContenidoException, DuracionErroneaCancionException {
		Contenido e = new Emisora("Emisora1");

		// Agregar

		Contenido c1 = new Cancion("Cancion 1", 3);

		Contenido c2 = new Cancion("Cancion 2", 4);

		e.agregar(c1, null);
		e.agregar(c2, c1);

		assertEquals(7, e.obtenerDuracion());

		// Buscar

		List<Contenido> r = e.buscar("nc");

		assertEquals(2, r.size());
		assertTrue(r.contains(c1));
		assertTrue(r.contains(c2));

		// Eliminar

		e.eliminar(c1);
		List<Contenido> r2 = e.buscar("nc");

		assertTrue(r2.size() == 1);

	}

	// Inicio Pruebas de Valores Frontera: Duración Canción

	@Test
	public void testCancionDuracionNegativa()
			throws DuracionErroneaCancionException {
		Contenido c = new Cancion("Cancion 1", -5);
		assertEquals(c.obtenerDuracion(), 5);
	}

	@Test
	public void testCancionDuracionPositiva()
			throws DuracionErroneaCancionException {
		Contenido c = new Cancion("Cancion 1", 5);
		assertEquals(c.obtenerDuracion(), 5);
	}

	@Test(expected = DuracionErroneaCancionException.class)
	public void testCancionDuracionErronea()
			throws DuracionErroneaCancionException {
		new Cancion("Cancion 1", 0);		
	}
	
	// Fin Pruebas de Valores Frontera: Duración Canción
}
