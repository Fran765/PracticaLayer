package ejercicio2.dominio.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import ejercicio2.dominio.portsin.DominioException;

public class Empleado {

	private String nombre;
	private String apellido;
	private LocalDate fechaCumpleanio;
	private Mail mail;

	public Empleado(String nombre, String apellido, String fechaCumpleanios, String mail) {

		Objects.requireNonNull(nombre);
		Objects.requireNonNull(apellido);
		Objects.requireNonNull(fechaCumpleanios);

		if (nombre.isEmpty() || apellido.isEmpty() || fechaCumpleanios.isEmpty())
			throw new DominioException("Faltan datos para crear el empleado.");

		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaCumpleanio = LocalDate.parse(fechaCumpleanios, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		this.mail = new Mail(mail);

	}

	public String nombre() {
		return nombre;
	}

	public String apellido() {
		return apellido;
	}

	public LocalDate fechaCumpleanio() {
		return fechaCumpleanio;
	}

	public String mail() {
		return mail.getMail();
	}

}
