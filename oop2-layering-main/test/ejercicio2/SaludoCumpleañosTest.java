package ejercicio2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ejercicio2.dominio.model.DefaultSaludoEmpleado;
import ejercicio2.dominio.portsin.DominioException;
import ejercicio2.dominio.portsout.EmpleadosRecord;

class SaludoCumpleañosTest {

	@Test
	void saludartest() {
		List<EmpleadosRecord> misEmpleadosFake = new ArrayList<EmpleadosRecord>();

		EmpleadosRecord empleado1 = new EmpleadosRecord("Young", "Angus", "1982/10/08", "angus@acdc.com");
		misEmpleadosFake.add(empleado1);
		EmpleadosRecord empleado2 = new EmpleadosRecord("Johnson", "Brian", "1975/09/11", "brian@acdc.com");
		misEmpleadosFake.add(empleado2);
		EmpleadosRecord empleado3 = new EmpleadosRecord("Juan", "Perez", LocalDate.now().toString(), "fran@gmail.com");
		misEmpleadosFake.add(empleado3);

		FakeLugarDeLectura registroEmpleadosFake = new FakeLugarDeLectura();
		registroEmpleadosFake.cargarEmpleados(misEmpleadosFake);

		FakeSaludarPorCumpleaños saludarFake = new FakeSaludarPorCumpleaños();

		DefaultSaludoEmpleado saludos = new DefaultSaludoEmpleado(saludarFake, registroEmpleadosFake);

		try {
			saludos.enviarNotificacion();

			assertEquals("fran@gmail.com", saludarFake.devolverDestinatario());

		} catch (DominioException e) {

			System.err.println("Excepcion de saludo: " + e.getMessage());
		}

	}

}
