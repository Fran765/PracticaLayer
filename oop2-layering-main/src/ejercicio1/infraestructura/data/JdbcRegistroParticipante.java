package ejercicio1.infraestructura.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ejercicio1.dominio.portsout.Registro;
import ejercicio1.dominio.portsout.RegistroException;

public class JdbcRegistroParticipante implements Registro {

	private Conn connStr;

	public JdbcRegistroParticipante(String connStr) {
		this.connStr = new Conn(connStr);
	}

	@Override
	public void registrarParticipante(String nombre, String telefono, String region) throws RegistroException {
		try {
			Connection dbConn = this.connStr.open();

			PreparedStatement st = dbConn
					.prepareStatement("insert into participantes(nombre, telefono, region) values(?,?,?)");

			st.setString(1, nombre);
			st.setString(2, telefono);
			st.setString(3, region);

			st.executeUpdate();
			st.close();

		} catch (SQLException e) {
			throw new RegistroException(e, "No ha podido registrarse el participante.");
		}
	}

}
