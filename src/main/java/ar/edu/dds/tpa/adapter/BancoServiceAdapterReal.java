package ar.edu.dds.tpa.adapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.dds.tpa.adapter.model.BancoDelServicio;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Servicio;

public class BancoServiceAdapterReal implements BancoServiceAdapter {
	
	@Override
	public List<Banco> obtenerBancosDeJSON(String bancosEnJSON) {
		List<Banco> bancos = new ArrayList<Banco>();
		List<BancoDelServicio> bancosDelServicio = new ArrayList<BancoDelServicio>();
		Type listaDeBancos = new TypeToken<List<BancoDelServicio>>() {}.getType();
		Gson gson = new Gson();
		bancosDelServicio = gson.fromJson(bancosEnJSON, listaDeBancos);
		bancosDelServicio.stream().forEach(unBancoDelServicio -> bancos.add(parsearBancoDelServicioABanco(unBancoDelServicio)));
		return bancos;
	}
	
	public Banco parsearBancoDelServicioABanco(BancoDelServicio bancoDelServicio) {
		Banco banco = new Banco(bancoDelServicio.getBanco(), new Posicion(bancoDelServicio.getX(), bancoDelServicio.getY()));
		bancoDelServicio.getServicios().stream().forEach(unServicio -> banco.agregarServicio(new Servicio(unServicio)));
		return banco;
	}
}