package ejercicio2.infraestructura.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ejercicio2.dominio.portsout.EmpleadosRecord;
import ejercicio2.dominio.portsout.LugarDeLectura;
import ejercicio2.dominio.portsout.NotificarCumpleaniosException;

public class LeerEmpleadosDelLugarDeLectura implements LugarDeLectura {

	private FileReader lector;
	private BufferedReader buffer;
	private String rutaArchivo;

	public LeerEmpleadosDelLugarDeLectura(String rutaArchivo) throws NotificarCumpleaniosException {

		Objects.requireNonNull(rutaArchivo);
		if (rutaArchivo.isEmpty())
			throw new NotificarCumpleaniosException("La ruta del archivo no puede estar vacia.");

		this.rutaArchivo = rutaArchivo;

	}

	@Override
	public List<EmpleadosRecord> listaDeEmpleados() throws NotificarCumpleaniosException {

		List<EmpleadosRecord> misEmpleados = new ArrayList<EmpleadosRecord>();
		try {
			File archivo = new File(rutaArchivo);
			FileReader lector = new FileReader(archivo);
			BufferedReader buffer = new BufferedReader(lector);
			String linea;
			while ((linea = buffer.readLine()) != null) {
				String[] partes = linea.split("\\, ");

				misEmpleados.add(new EmpleadosRecord(partes[0], partes[1], partes[2], partes[3]));
			}
		} catch (IOException e) {
			throw new NotificarCumpleaniosException("No se pudieron leer los empleados");
		} finally {

			try {
				buffer.close();
				lector.close();
			} catch (IOException e) {
				throw new NotificarCumpleaniosException("Error al cerrar el archivo de lectura");
			}
		}

		return misEmpleados;

	}

}
