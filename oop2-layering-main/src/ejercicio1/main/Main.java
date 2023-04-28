package ejercicio1.main;

import ejercicio1.dominio.model.DefaultRegistroDeParticipante;
import ejercicio1.infraestructura.data.JdbcRegistroParticipante;
import ejercicio1.infraestructura.ui.AgregarParticianteView;

public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new AgregarParticianteView(new DefaultRegistroDeParticipante(
							new JdbcRegistroParticipante("jdbc:mysql://localhost:3306/oop2-e1")));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

}
