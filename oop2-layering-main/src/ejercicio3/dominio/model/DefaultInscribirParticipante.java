package ejercicio3.dominio.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ejercicio3.dominio.portsin.ConcursoRecordIn;
import ejercicio3.dominio.portsin.DominioException;
import ejercicio3.dominio.portsin.InscribirParticipante;
import ejercicio3.dominio.portsin.ParticipanteRecordIn;
import ejercicio3.dominio.portsout.ConcursoRecordOut;
import ejercicio3.dominio.portsout.InfraestructuraException;
import ejercicio3.dominio.portsout.LecturaDeConcursos;
import ejercicio3.dominio.portsout.RegistroInscripcion;

public class DefaultInscribirParticipante implements InscribirParticipante {

	private List<ConcursoRecordIn> listaConcursoRecord;
	private RegistroInscripcion direccion;
	private LecturaDeConcursos direccionDeConcursosAlmacenados;

	public DefaultInscribirParticipante(RegistroInscripcion direccion,
			LecturaDeConcursos direccionDeConcursosAlmacenados) {

		Objects.requireNonNull(direccion);
		this.direccion = direccion;
		this.direccionDeConcursosAlmacenados = direccionDeConcursosAlmacenados;
		this.listaConcursoRecord = new ArrayList<ConcursoRecordIn>();
	}

	@Override
	public void saveInscription(ParticipanteRecordIn participante, ConcursoRecordIn concurso) {

		Participante nuevo = new Participante(participante.apellido(), participante.nombre(), participante.dni(),
				participante.telefono(), participante.mail(), concurso.idConcurso());

		Concurso seleccionado = new Concurso(concurso.idConcurso(), concurso.nombre(),
				concurso.fechaInicioInscripcion(), concurso.fechaFinInscripcion());

		try {
			direccion.inscribir(nuevo.getApellido(), nuevo.getNombre(), nuevo.getTelefono(), nuevo.getMail(),
					seleccionado.idConcurso());
		} catch (InfraestructuraException e) {
			throw new DominioException("No se pudo inscribir el participante.");
		}

	}

	@Override
	public List<ConcursoRecordIn> todosLosConcursos() {

		List<ConcursoRecordOut> disponibles = new ArrayList<ConcursoRecordOut>();

		try {

			disponibles = direccionDeConcursosAlmacenados.consultarConcursos();
			cargarConcursosDisponibles(disponibles);

		} catch (InfraestructuraException e) {
			throw new DominioException("Hubo problemas con el archivo de concursos");
		}

		return listaConcursoRecord;
	}

	private void cargarConcursosDisponibles(List<ConcursoRecordOut> disponibles) {

		for (ConcursoRecordOut record : disponibles) {
			ConcursoRecordIn concurso = new ConcursoRecordIn(record.idConcurso(), record.nombre(),
					record.fechaInicioInscripcion(), record.fechaFinInscripcion());
			if (validarFechasDeConcurso(concurso))
				listaConcursoRecord.add(concurso);
		}

	}

	private boolean validarFechasDeConcurso(ConcursoRecordIn concurso) {
		LocalDate fechaApertura = LocalDate.parse(concurso.fechaInicioInscripcion(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		LocalDate fechaCierre = LocalDate.parse(concurso.fechaFinInscripcion(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		LocalDate fechaActual = LocalDate.now();

		return (fechaActual.isAfter(fechaApertura) && fechaActual.isBefore(fechaCierre));

	}

}
