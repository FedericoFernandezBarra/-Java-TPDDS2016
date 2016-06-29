package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.adapter.BuscadorExterno;
import ar.edu.dds.tpa.observer.BusquedaObserver;

public class Buscador {
	private Mapa mapaLocal;
	private List<BuscadorExterno> buscadoresExternos;
	private List<BusquedaObserver> observadoresDeBusqueda;
	private List<Busqueda> historialDeBusqueda;

	public Buscador(Mapa unMapa) {
		mapaLocal = unMapa;
		buscadoresExternos = new ArrayList<BuscadorExterno>();
		observadoresDeBusqueda = new ArrayList<BusquedaObserver>();
		historialDeBusqueda = new ArrayList<Busqueda>();
	}

	public void registrarBusqueda(Usuario usuario, String textoBuscado, int cantidadDeResultados,
			LocalDateTime tiempoDeInicio, LocalDateTime tiempoDeFin) {
		double duracionDeBusqueda = ChronoUnit.SECONDS.between(tiempoDeInicio, tiempoDeFin);
		Busqueda busquedaRealizada = new Busqueda(usuario, textoBuscado, cantidadDeResultados, LocalDate.now(),
				duracionDeBusqueda);
		historialDeBusqueda.add(busquedaRealizada);

	}

	public List<PuntoDeInteres> buscar(String unaFrase, Usuario unUsuario) {
		LocalDateTime comienzoDeBusqueda = LocalDateTime.now();
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();

		puntosDeInteresEncontrados.addAll(buscarLocalmente(obtenerPalabrasDeUnaFrase(unaFrase)));
		puntosDeInteresEncontrados.addAll(buscarExternamente(obtenerPalabrasDeUnaFrase(unaFrase)));

		registrarBusqueda(unUsuario, unaFrase, cantidadDeResultadosDeUnaBusqueda(puntosDeInteresEncontrados),
				comienzoDeBusqueda, LocalDateTime.now());
		return puntosDeInteresEncontrados;
	}

	public List<String> obtenerPalabrasDeUnaFrase(String unaFrase) {
		return Arrays.asList(unaFrase.split(" "));
	}

	public List<PuntoDeInteres> buscarLocalmente(List<String> palabrasDeBusqueda) {
		List<PuntoDeInteres> puntosDeInteresEncontradosLocalmente = new ArrayList<PuntoDeInteres>();
		puntosDeInteresEncontradosLocalmente.addAll(mapaLocal.obtenerPuntosDeInteres().stream()
				.filter(unPuntoDeInteres -> palabrasDeBusqueda.stream()
						.anyMatch(unaPalabra -> unPuntoDeInteres.contienePalabraClave(unaPalabra)))
				.collect(Collectors.toList()));
		return puntosDeInteresEncontradosLocalmente;
	}

	public List<PuntoDeInteres> buscarExternamente(List<String> palabrasDeBusqueda) {
		List<PuntoDeInteres> puntosDeInteresEncontradosExternamente = new ArrayList<PuntoDeInteres>();
		buscadoresExternos.stream().forEach(unBuscador -> puntosDeInteresEncontradosExternamente
				.addAll(unBuscador.buscarPuntosDeInteresDelServicio(palabrasDeBusqueda)));
		return puntosDeInteresEncontradosExternamente;
	}

	public int cantidadDeResultadosDeUnaBusqueda(List<PuntoDeInteres> resultados) {
		return resultados.size();
	}

	public void agregarBuscadorExterno(BuscadorExterno unBuscadorExterno) {
		buscadoresExternos.add(unBuscadorExterno);
	}
	
	public void agregarObservadorDeBusqueda(BusquedaObserver unObservadorDeBusqueda) {
		observadoresDeBusqueda.add(unObservadorDeBusqueda);
	}
}