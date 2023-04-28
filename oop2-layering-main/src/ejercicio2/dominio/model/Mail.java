package ejercicio2.dominio.model;

import java.util.Objects;

import ejercicio2.dominio.portsin.DominioException;

public class Mail {

	private String mail;

	public Mail(String direccionMail) {

		Objects.requireNonNull(direccionMail);

		if (direccionMail.isEmpty())
			throw new DominioException("Debe ingresar un mail");

		if (!validarMail(direccionMail))
			throw new DominioException("Email debe ser válido");

		this.mail = direccionMail;
	}

	public String getMail() {
		return mail;
	}

	private boolean validarMail(String mail) {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return mail.matches(regex);
	}

}
