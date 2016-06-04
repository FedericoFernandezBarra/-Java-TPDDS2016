package ar.edu.dds.tpa.adapter.model;

import java.util.List;

public class ServicioDTO {
	private String nombreServicio;
	private List<RangoServicioDTO> rangosServicioDTO;
	
	public String getNombreServicio() {
		return nombreServicio;
	}
	
	public List<RangoServicioDTO> getRangosServicioDTO() {
		return rangosServicioDTO;
	}
}