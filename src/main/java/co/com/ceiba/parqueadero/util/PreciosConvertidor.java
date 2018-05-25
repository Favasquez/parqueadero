package co.com.ceiba.parqueadero.util;

import co.com.ceiba.parqueadero.dominio.Precios;
import co.com.ceiba.parqueadero.entidades.PreciosEntidad;

public class PreciosConvertidor {
	
	public Precios convertiraDomain(PreciosEntidad precios) {
		return new Precios(1,
				precios.getValorHoraCarro(),
				precios.getValorDiaCarro(),
				precios.getValorHoraMoto(),
				precios.getValorDiaMoto());

	}
	
	public PreciosEntidad convertiraEntity(Precios precios) {
		return new PreciosEntidad(1,
				precios.getValorHoraCarro(),
				precios.getValorDiaCarro(),
				precios.getValorHoraMoto(),
				precios.getValorDiaMoto());
	}
}
