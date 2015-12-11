package es.udc.fi.PracticaVVS.servidores;

import java.util.ArrayList;
import java.util.List;

import es.udc.fi.PracticaVVS.contenidos.Contenido;
import es.udc.fi.PracticaVVS.utiles.CadenaErroneaException;
import es.udc.fi.PracticaVVS.utiles.Token;
import es.udc.fi.PracticaVVS.utiles.UnexistingTokenException;
public class ServidorRespaldado extends ServidorSimple {

	private final Servidor respaldo;
	private Token respaldoToken;
	
	public ServidorRespaldado(String nombre, Token specialToken, ServidorSimple respaldo) {
		super(nombre, specialToken);
		this.respaldo = respaldo;
	}


	@Override
	public List<Contenido> buscar(String subcadena, Token token) throws UnexistingTokenException, CadenaErroneaException {
		ArrayList<Contenido> list = (ArrayList<Contenido>) super.buscar(subcadena, token);
		if(list.isEmpty() && respaldo!=null){
			if(respaldoToken==null||respaldoToken.getCount()==0){
				respaldoToken = respaldo.alta();
			}
			list = (ArrayList<Contenido>) respaldo.buscar(subcadena, respaldoToken);
		}
		return list;
	}
	
}
