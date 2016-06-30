package ar.edu.dds.tpa.model;

import java.util.List;

public class ServicioPuntosDeInteresBajados_Impostor extends ServicioPuntosDeInteresBajados {

	private Boolean fueLlamado = false;
	
	public Boolean getFueLlamado() {
		return fueLlamado;
	}

	public String getPuntosDeInteresJSON(){
		return this.puntosDeInteresJSON;
	}
	
	public void setPuntosDeInteresJSON(String json){
		this.puntosDeInteresJSON = json;
	}
	
	@Override
	public List<BajaPuntoDeInteres> obtenerPuntosABajar() {
		// TODO Auto-generated method stub
		fueLlamado = true;
		return super.obtenerPuntosABajar();
	}
	
}
