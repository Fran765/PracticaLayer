package ejercicio1.dominio.portsout;

public interface Registro {
	
	public void registrarParticipante(String nombre, String telefono, String region) throws RegistroException;

}
