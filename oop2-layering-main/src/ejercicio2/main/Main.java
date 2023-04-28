package ejercicio2.main;

import ejercicio2.dominio.model.DefaultSaludoEmpleado;
import ejercicio2.infraestructura.data.LeerEmpleadosDelLugarDeLectura;
import ejercicio2.infraestructura.mail.PorMailNotificarCumpleanios;

public class Main {

	public static void main(String[] args) {

		try {
			DefaultSaludoEmpleado saludos = new DefaultSaludoEmpleado(new PorMailNotificarCumpleanios(),
					new LeerEmpleadosDelLugarDeLectura("ArchivoDeEmpleados"));

			saludos.enviarNotificacion();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}