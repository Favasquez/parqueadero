package co.com.ceiba.parqueadero.util;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.entidades.VehiculoEntidad;

public class VehiculoConvertidor {
	
	public Vehiculo convertiraDomain(VehiculoEntidad vehiculoEntidad) {
		return new Vehiculo(vehiculoEntidad.getId(),
				vehiculoEntidad.getPlaca(),
				vehiculoEntidad.getTipo(),
				vehiculoEntidad.getCilindraje(),
				vehiculoEntidad.getParqueado());
	}
	
	public VehiculoEntidad convertiraEntity(Vehiculo vehiculo) {
		return new VehiculoEntidad(vehiculo.getId(),
				vehiculo.getPlaca(),
				vehiculo.getTipo(),
				vehiculo.getCilindraje(),
				vehiculo.getParqueado());
	}
}
