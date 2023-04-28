package ejercicio1.dominio.model;

import java.util.Objects;

import ejercicio1.dominio.portsin.DominioException;

public class Participante {

	private String nombre;
	private Telefono telefono;
	private Region region;

	public Participante(String nombre, String telefono, String region) {

		Objects.requireNonNull(nombre);
		
		if (nombre.isEmpty())
			throw new DominioException("Debe cargar un nombre");

		this.nombre = nombre;
		this.telefono = new Telefono (telefono);
		this.region = new Region(region);
	}

	public String nombre() {
		return nombre;
	}

	public String telefono() {
		return telefono.telefono();
	}

	public String region() {
		return region.region();
	}

}
