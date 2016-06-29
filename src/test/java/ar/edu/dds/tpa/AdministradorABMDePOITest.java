package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.*;

public class AdministradorABMDePOITest {
	Administrador administrador;
	Mapa mapa;
	ParadaDeColectivo paradaDel7;
	Banco bancoSantanderRio;
	Buscador buscador;

	@Before
	public void inicializar() {
		administrador = new Administrador(null);
		mapa = new Mapa();
		paradaDel7 = new ParadaDeColectivo("parada del colectivo linea 7", null);
		bancoSantanderRio = new Banco("Banco Santander Rio Sucursal Ramos Mejia", null);
		buscador = new Buscador(mapa);
	}
	
	@Test
	public void altaDePuntoDeInteres() {
		administrador.agregarPuntoDeInteres(bancoSantanderRio, mapa);
		
		Assert.assertTrue(buscador.buscar("santander").contains(bancoSantanderRio));
	}
	
	@Test
	public void bajaDePuntoDeInteres() {
		administrador.agregarPuntoDeInteres(paradaDel7, mapa);
		administrador.sacarPuntoDeInteres(paradaDel7, mapa);
		
		Assert.assertFalse(buscador.buscar("7").contains(paradaDel7));
	}
	
	@Test
	public void modificacionDePuntoDeInteres() {
		administrador.agregarPuntoDeInteres(paradaDel7, mapa);
		ParadaDeColectivo paradaDel7Modificada = new ParadaDeColectivo("terminal de bondi 7", null);
		administrador.modificarPuntoDeInteres(paradaDel7, paradaDel7Modificada, mapa);
		
		Assert.assertTrue(buscador.buscar("bondi").contains(paradaDel7Modificada) &&
				!buscador.buscar("colectivo").contains(paradaDel7));
	}
}