package ejercicio2;

import java.util.List;

import ejercicio2.dominio.portsout.EmpleadosRecord;
import ejercicio2.dominio.portsout.LugarDeLectura;
import ejercicio2.dominio.portsout.NotificarCumpleaniosException;

public class FakeLugarDeLectura implements LugarDeLectura {

	private List<EmpleadosRecord> empleadosFake;

	void cargarEmpleados(List<EmpleadosRecord> empleados) {
		this.empleadosFake = empleados;
	}

	@Override
	public List<EmpleadosRecord> listaDeEmpleados() throws NotificarCumpleaniosException{
		return empleadosFake;
	}

}
