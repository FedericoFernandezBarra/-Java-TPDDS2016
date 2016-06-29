package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
	private List<PuntoDeInteres> puntosDeInteres;

	public Mapa() {
		puntosDeInteres = new ArrayList<PuntoDeInteres>();
	}

	public void agregarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.add(unPuntoDeInteres);
	}

	public void sacarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.remove(unPuntoDeInteres);
	}

	public void modificarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado) {
		puntosDeInteres.remove(unPuntoDeInteres);
		puntosDeInteres.add(puntoDeInteresModificado);
	}

	public List<PuntoDeInteres> obtenerPuntosDeInteres() {
		return puntosDeInteres;
	}
}