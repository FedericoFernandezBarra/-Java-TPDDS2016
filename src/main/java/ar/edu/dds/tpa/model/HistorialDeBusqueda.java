package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistorialDeBusqueda {
	List<Busqueda> busquedasRealizadas;

	public HistorialDeBusqueda() {
		busquedasRealizadas = new ArrayList<Busqueda>();
	}

	public void agregarBusqueda(Busqueda unaBusqueda) {
		busquedasRealizadas.add(unaBusqueda);
	}

	public List<LocalDate> fechasDeBusquedas() {
		return busquedasRealizadas.stream().map(unaBusqueda -> unaBusqueda.getFechaDeBusqueda()).distinct()
				.collect(Collectors.toList());
	}

	public List<String> textosBuscados() {
		return busquedasRealizadas.stream().map(unaBusqueda -> unaBusqueda.getTextoBuscado())
				.collect(Collectors.toList());
	}

	public int cantidadDeResultadosTotales() {
		return busquedasRealizadas.stream().mapToInt(unaBusqueda -> unaBusqueda.getCantidadDeResultados()).sum();
	}

	public long cantidadDeBusquedasEnUnaFecha(LocalDate unaFecha) {
		return busquedasRealizadas.stream().filter(unaBusqueda -> unaBusqueda.getFechaDeBusqueda().equals(unaFecha))
				.count();
	}

	public List<Integer> resultadosParcialesPorBusqueda(String textoBuscado) {
		return busquedasRealizadas.parallelStream()
				.filter(unaBusqueda -> unaBusqueda.getTextoBuscado().equals(textoBuscado))
				.map(unaBusquedaFiltrada -> (Integer) unaBusquedaFiltrada.getCantidadDeResultados())
				.collect(Collectors.toList());
	}
}