package es.udc.fi.PracticaVVS.servidores;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import es.udc.fi.PracticaVVS.contenidos.Anuncio;
import es.udc.fi.PracticaVVS.contenidos.Contenido;
import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.Token;
import es.udc.fi.PracticaVVS.utiles.UnexistingContenidoException;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;

public class ServidorSimple implements Servidor {

	private String nombre;
	private final Token specialToken;
	private List<Contenido> contenidos;
	private List<Token> activeTokens;

	public ServidorSimple(String nombre, Token specialToken) {
		super();
		this.nombre = nombre;
		this.specialToken = specialToken;
		this.contenidos = new ArrayList<Contenido>();
		this.activeTokens = new ArrayList<Token>();
	}

	public String obtenerNombre() {
		return nombre;
	}

	public boolean existeToken(Token token) {
		return activeTokens.contains(token);
	}

	public Token alta() {
		Token token = new Token();
		String cadena = new SessionIdentifierGenerator().nextSessionId();
		token.setToken(cadena);
		token.setCount(10);
		activeTokens.add(token);
		return token;
	}

	public void baja(Token token) throws UnexistingTokenException {
		if (activeTokens.contains(token)) {
			activeTokens.remove(token);
		} else {
			throw new UnexistingTokenException();
		}
	}

	public void agregar(Contenido contenido, Token token)
			throws UnexistingTokenException {
		if (token.equals(specialToken)) { // Comprueba que el token sea el
											// correcto
			contenidos.add(contenido);
		} else {
			throw new UnexistingTokenException();
		}
	}

	public void eliminar(Contenido contenido, Token token)
			throws UnexistingTokenException, UnexistingContenidoException {

		if (token.equals(specialToken)) { // Comprueba que el token sea el
											// correcto
			if (contenidos.contains(contenido)) { // Comprueba que el contenido
													// exista
				contenidos.remove(contenido);
			} else {
				throw new UnexistingContenidoException();
			}
		} else {
			throw new UnexistingTokenException();
		}
	}

	public List<Contenido> buscar(String subcadena, Token token)
			throws UnexistingTokenException, CadenaErroneaException {
		ArrayList<Contenido> list = new ArrayList<Contenido>();
		if (subcadena == null) {
			throw new CadenaErroneaException();
		}
		if (activeTokens.contains(token)) { // Comprobamos que el token existe
			for (Contenido c : contenidos) { // Buscamos el contenido deseado
				if (c.obtenerTitulo().contains(subcadena)) {
					list.add(c);
				}
			}
			if (token.getCount() <= 0) {// Anadimos publicidad de ser necesario
				for (int i = 0; i < list.size(); i += 3) {
					if (i < list.size()) {
						list.add(new Anuncio());
					}
				}
			}
			// Modificamos el token
			activeTokens.remove(token);
			token.setCount(token.getCount() - 1);
			activeTokens.add(token);
		} else {
			throw new UnexistingTokenException();
		}
		return list;
	}

	private final class SessionIdentifierGenerator {
		private SecureRandom random = new SecureRandom();

		public String nextSessionId() {
			return new BigInteger(130, random).toString(32);
		}
	}

}
