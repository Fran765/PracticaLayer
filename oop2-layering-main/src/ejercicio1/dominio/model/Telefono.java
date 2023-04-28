package ejercicio1.dominio.model;

import java.util.Objects;

import ejercicio1.dominio.portsin.DominioException;

public class Telefono {
	
	private String telefono;
	
	public Telefono(String telefono) {
		
		Objects.requireNonNull(telefono);
		
		if(telefono.isEmpty())
			throw new DominioException("Debe cargar un telefono");
		
		if(!validarTelefono(telefono))
			throw new DominioException("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
		
		this.telefono = telefono;
	}
	
	
	
	public String telefono() {
		return telefono;
	}



	private boolean validarTelefono(String telefono) {
		String regex = "\\d{4}-\\d{6}";
		return telefono.matches(regex);
	}

}
