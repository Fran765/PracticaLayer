package ejercicio2;

import ejercicio2.dominio.portsout.NotificarCumpleaniosException;
import ejercicio2.dominio.portsout.SaludarPorCumpleanios;

public class FakeSaludarPorCumplea�os implements SaludarPorCumpleanios {

	private String destinatario;

	@Override
	public void notificar(String contactoDestino) throws NotificarCumpleaniosException {
		this.destinatario = contactoDestino;
	}

	String devolverDestinatario() {
		return destinatario;
	}

}
