package ejercicio3.dominio.model;

import java.util.Objects;

import ejercicio3.dominio.portsin.DominioException;


public class Mail {
	
	private String mail;

	public Mail(String direccionMail) {

		Objects.requireNonNull(direccionMail);

		if (!checkEmail(direccionMail))
			throw new DominioException("Email debe ser válido.");

		this.mail = direccionMail;
	}

	public String getMail() {
		return mail;
	}
	
	private boolean checkEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

}
