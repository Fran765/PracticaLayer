package ejercicio1.dominio.model;

import ejercicio1.dominio.portsin.DominioException;
import ejercicio1.dominio.portsin.ParticipanteRecord;
import ejercicio1.dominio.portsin.RegistroDeParticipante;
import ejercicio1.dominio.portsout.Registro;
import ejercicio1.dominio.portsout.RegistroException;

public class DefaultRegistroDeParticipante implements RegistroDeParticipante{
	
	private Registro lugarDeRegistro;
	
	public DefaultRegistroDeParticipante(Registro lugarDeRegistro) {

		this.lugarDeRegistro = lugarDeRegistro;
	}

	@Override
	public void registrar(ParticipanteRecord participante){
		
		
		Participante nuevo = new Participante(participante.nombre(), participante.telefono(), participante.region());
		
		try {
			lugarDeRegistro.registrarParticipante(nuevo.nombre(), nuevo.telefono(), nuevo.region());
		} catch (RegistroException e) {
			throw new DominioException("No se pudo registrar al participante.");
		}
		
	}

}
