	package ejercicio1.dominio.portsout;

public class RegistroException extends Exception{
	
	public RegistroException(Exception ex, String msg) {
	    super(msg, ex);
	  }

}
