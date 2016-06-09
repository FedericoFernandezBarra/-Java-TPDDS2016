package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.*;
import ar.edu.dds.tpa.model.*;

public class CalculoDeCercaniaTest {
	Posicion posicionDeUsuario1;
	Posicion posicionDeUsuario2;
	Posicion posicionDeUsuario3;
	Posicion posicionDeUsuario4;
	ParadaDeColectivo bondi114;
	Rubro kioscoDeDiarios;
	LocalComercial unLocalDeDiarios1;
	Banco bancoPatagonia;
	CGP cgpDeFlores;
	List<Posicion> puntos;
	Poligono barrioDeFlores;

	@Before
	public void inicializar() {
		bondi114 = new ParadaDeColectivo("114", new Posicion(200.0006, 100.0));

		kioscoDeDiarios = new Rubro("Kiosco de diarios",2.00);
		unLocalDeDiarios1 = new LocalComercial("Diarin", new Posicion(100.000002, 50.0), kioscoDeDiarios);

		puntos = new ArrayList<Posicion>(Arrays.asList(new Posicion(10.0, 10.0), new Posicion(10.0, -10.0),
				new Posicion(-10.0, 10.0), new Posicion(-10.0, -10.0)));
		barrioDeFlores = new Poligono(puntos);
		
		cgpDeFlores = new CGP("CGPFlores", new Posicion(100.0, 5.0));
		cgpDeFlores.agregarZonaDeCobertura(barrioDeFlores);

		bancoPatagonia = new Banco("Banco Patagonia", new Posicion(100.000004, 50.0));

		posicionDeUsuario1 = new Posicion(100.000001, 50.0);
		posicionDeUsuario2 = new Posicion(200.0005, 100.0);
		posicionDeUsuario3 = new Posicion(5.0, 5.0);
		posicionDeUsuario4 = new Posicion(100.0, 100.0);
	}

	@Test
	public void elUsuario1EstaCercaDelBanco() {
		assertTrue(bancoPatagonia.estaCercaDe(posicionDeUsuario1));
	}

	@Test
	public void elUsuario1EstaCercaDelLocalDeDiarios() {
		assertTrue(unLocalDeDiarios1.estaCercaDe(posicionDeUsuario1));
	}

	@Test
	public void elUsuario2NoEstaCercaDeLaParadaDel114() {
		assertFalse(bondi114.estaCercaDe(posicionDeUsuario1));
	}

	@Test
	public void elUsuario3EstaCercaDeLaParadaDel114() {
		assertTrue(bondi114.estaCercaDe(posicionDeUsuario2));
	}

	@Test
	public void elUsuario3EstaCercaDelCGP() {
		assertTrue(cgpDeFlores.estaCercaDe(posicionDeUsuario3));
	}

	@Test
	public void elUsuario4NoEstaCercaDelCGP() {
		assertFalse(cgpDeFlores.estaCercaDe(posicionDeUsuario4));
	}
}