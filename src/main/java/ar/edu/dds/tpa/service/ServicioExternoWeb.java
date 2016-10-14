package ar.edu.dds.tpa.service;

import ar.edu.dds.tpa.properties.Propiedades;

public abstract class ServicioExternoWeb {
	private Propiedades propiedades;
	private boolean soportaCache;
	
	public ServicioExternoWeb() {
		propiedades = new Propiedades("resources/ServiciosExternos.properties");
	}
	
	public Propiedades propiedades() {
		return propiedades;
	}
	
	public boolean soportaCache() {
		return soportaCache;
	}
}