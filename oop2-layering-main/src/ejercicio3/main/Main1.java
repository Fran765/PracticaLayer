package ejercicio3.main;

import ejercicio3.dominio.model.DefaultInscribirParticipante;
import ejercicio3.infraestructura.data.JdbcLecturaDeConcursos;
import ejercicio3.infraestructura.data.JdbcRegistroInscripcion;
import ejercicio3.infraestructura.ui.InscribirEnConcursoView;

public class Main1 {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new InscribirEnConcursoView(new DefaultInscribirParticipante(
							new JdbcRegistroInscripcion("jdbc:mysql://localhost:3306/oop2-e3"),
									new JdbcLecturaDeConcursos("jdbc:mysql://localhost:3306/oop2-e3")));

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

	}

}
