package ejercicio2.infraestructura.mail;

import java.util.Objects;
import java.util.Properties;

import ejercicio2.dominio.portsout.SaludarPorCumpleanios;
import ejercicio2.dominio.portsout.NotificarCumpleaniosException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class PorMailNotificarCumpleanios implements SaludarPorCumpleanios {

	private static String ASUNTO = "Saludo de feliz cumpleños.";

	private static String MSJ = "¡Feliz cumpleaños!";

	@Override
	public void notificar(String contactoDestino) throws NotificarCumpleaniosException {

		Objects.requireNonNull(contactoDestino);

		String from = "jakartafrom@example.com"; // Direccion del que envia

		final String username = "0d27d2e44d1f90";
		final String password = "f222bac5da2b08";

		// provide Mailtrap's host address
		String host = "sandbox.smtp.mailtrap.io";

		// configure Mailtrap's SMTP server details
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "2525");

		// create the Session object
		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(contactoDestino));
			message.setSubject(this.ASUNTO); // Asunto del mail
			message.setText(this.MSJ); // Mensaje del mail
			Transport.send(message);
			System.out.println("Email Message Sent Successfully");
		} catch (MessagingException e) {
			throw new NotificarCumpleaniosException("El mail no pudo ser enviado con exito.");
		}

	}

}
