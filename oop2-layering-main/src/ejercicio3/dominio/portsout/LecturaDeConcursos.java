package ejercicio3.dominio.portsout;

import java.util.List;

public interface LecturaDeConcursos {

	public List<ConcursoRecordOut> consultarConcursos() throws InfraestructuraException;

}
