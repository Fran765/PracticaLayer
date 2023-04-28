package ejercicio2.dominio.model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ejercicio2.dominio.portsin.DominioException;
import ejercicio2.dominio.portsin.SaludarEmpleado;
import ejercicio2.dominio.portsout.EmpleadosRecord;
import ejercicio2.dominio.portsout.LugarDeLectura;
import ejercicio2.dominio.portsout.NotificarCumpleaniosException;
import ejercicio2.dominio.portsout.SaludarPorCumpleanios;

public class DefaultSaludoEmpleado implements SaludarEmpleado {

	private List<Empleado> listaEmpleados;
	private SaludarPorCumpleanios medioNotificacion;
	private LugarDeLectura direccion;

	public DefaultSaludoEmpleado(SaludarPorCumpleanios medioNotificacion, LugarDeLectura direccion) {

		Objects.requireNonNull(medioNotificacion);
		Objects.requireNonNull(direccion);

		this.medioNotificacion = medioNotificacion;
		this.direccion = direccion;
		this.listaEmpleados = new ArrayList<Empleado>();
	}

	@Override
	public void enviarNotificacion() throws IOException {

		llenarListaDeEmpleados(direccion.listaDeEmpleados());

		for (Empleado empleado : this.listaEmpleados) {

			saludo(empleado);
		}

	}

	private void saludo(Empleado empleado) {
		if (esElCumpleaños(empleado.fechaCumpleanio())) {

			try {
				medioNotificacion.notificar(empleado.mail());
			} catch (NotificarCumpleaniosException e) {
				throw new DominioException(e.getMessage());
			}
		}
	}

	private void llenarListaDeEmpleados(List<EmpleadosRecord> empleados) {

		for (EmpleadosRecord empleadoR : empleados) {
			Empleado nuevo = new Empleado(empleadoR.nombre(), empleadoR.apellido(), empleadoR.fechaCumpleaños(),
					empleadoR.mail());
			this.listaEmpleados.add(nuevo);
		}

	}

	private boolean esElCumpleaños(LocalDate fecha) {

		LocalDate today = LocalDate.now();

		return (today.getDayOfMonth() == fecha.getDayOfMonth() && today.getMonth() == fecha.getMonth());

	}
}
