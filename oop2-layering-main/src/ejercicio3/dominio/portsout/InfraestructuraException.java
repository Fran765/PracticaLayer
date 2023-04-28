package ejercicio3.dominio.portsout;

public class InfraestructuraException extends Exception {

	public InfraestructuraException(Exception ex, String msg) {
		super(msg, ex);
	}

}
