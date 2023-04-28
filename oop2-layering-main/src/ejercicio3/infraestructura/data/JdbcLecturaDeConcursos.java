package ejercicio3.infraestructura.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ejercicio3.dominio.portsout.ConcursoRecordOut;
import ejercicio3.dominio.portsout.InfraestructuraException;
import ejercicio3.dominio.portsout.LecturaDeConcursos;

public class JdbcLecturaDeConcursos implements LecturaDeConcursos {

	private Conn connStr;
	private Statement stmt = null;
	private ResultSet rs = null;

	public JdbcLecturaDeConcursos(String connStr) {

		this.connStr = new Conn(connStr);
	}

	@Override
	public List<ConcursoRecordOut> consultarConcursos() throws InfraestructuraException {

		List<ConcursoRecordOut> concursosDisponibles = new ArrayList<ConcursoRecordOut>();
		try {
			Connection dbConn = this.connStr.open();

			String consulta = "select * from concurso";
			stmt = dbConn.createStatement();

			rs = stmt.executeQuery(consulta);

			while (rs.next()) {
				ConcursoRecordOut nuevo = new ConcursoRecordOut(rs.getString("id_concurso"), rs.getString("nombre"),
						rs.getString("fecha_inicio"), rs.getString("fecha_fin"));

				concursosDisponibles.add(nuevo);
			}
		} catch (SQLException e) {
			throw new InfraestructuraException(e, "Hubo un error en la consulta a la base.");
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e1) {
				throw new InfraestructuraException(e1, "No se puedieron cerrar correctamente las concexiones de la base.");
			}

		}
		return concursosDisponibles;
	}

}
