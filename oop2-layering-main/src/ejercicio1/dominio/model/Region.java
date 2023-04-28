package ejercicio1.dominio.model;

import java.util.List;
import java.util.Objects;

import ejercicio1.dominio.portsin.DominioException;

public class Region {

	private static List<String> regionesDisponibles = List.of("US", "China", "Europa");
	private String region;

	public Region(String region) {
		Objects.requireNonNull(region);

		if (region.isEmpty())
			throw new DominioException("El campo Region no puede estar vacio.");

		if (!regionesDisponibles.contains(region))
			throw new DominioException("Region desconocida. Las conocidas son: China, US, Europa");

		this.region = region;
	}

	public String region() {
		return region;
	}

}
