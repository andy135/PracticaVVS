package es.udc.fi.PracticaVVS.utiles;

public class Token {

	private String token;
	private int count;

	public Token(String token) {
		super();
		this.token = token;
	}
	
	public Token(){
		
	}
	
	public String getToken() {
		return token;
	}	

	public void setToken(String token) {
		this.token = token;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
