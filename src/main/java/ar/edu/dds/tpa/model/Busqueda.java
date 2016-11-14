package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity
public class Busqueda {
	
	@Id
	private ObjectId id;

	private Terminal terminal;

	private String textoBuscado;

	private Set<PuntoDeInteres> puntosDeInteresEncontrados;

	private LocalDate fechaDeBusqueda;

	private Double tiempoDeRespuesta;

	public Busqueda() {
		puntosDeInteresEncontrados = new HashSet<PuntoDeInteres>();
	}

	public Busqueda(Terminal terminal, String textoBuscado, List<PuntoDeInteres> puntosDeInteresEncontrados,
			LocalDate fechaDeBusqueda, Double tiempoDeRespuesta) {
		this.terminal = terminal;
		this.textoBuscado = textoBuscado;
		this.puntosDeInteresEncontrados = new HashSet<PuntoDeInteres>(puntosDeInteresEncontrados);
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.tiempoDeRespuesta = tiempoDeRespuesta;
	}


	public Terminal getTerminal() {
		return terminal;
	}

	public String getTextoBuscado() {
		return textoBuscado;
	}

	public Set<PuntoDeInteres> getPuntosDeInteresEncontrados() {
		return puntosDeInteresEncontrados;
	}

	public int getCantidadDeResultados() {
		return puntosDeInteresEncontrados.size();
	}

	public LocalDate getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}

	public Double getTiempoDeRespuesta() {
		return tiempoDeRespuesta;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
}