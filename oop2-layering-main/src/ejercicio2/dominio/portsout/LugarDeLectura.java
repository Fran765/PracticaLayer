package ejercicio2.dominio.portsout;

import java.io.IOException;
import java.util.List;

public interface LugarDeLectura {
	
	public List<EmpleadosRecord> listaDeEmpleados() throws IOException;

}
