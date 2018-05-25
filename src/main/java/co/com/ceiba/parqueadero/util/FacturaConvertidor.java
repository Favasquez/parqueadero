package co.com.ceiba.parqueadero.util;

import co.com.ceiba.parqueadero.dominio.Factura;
import co.com.ceiba.parqueadero.entidades.FacturaEntidad;

public class FacturaConvertidor {
	
	VehiculoConvertidor vehiculoConvertidor = new VehiculoConvertidor();
	
	public Factura convertiraDomain(FacturaEntidad facturaEntidad) {
		return new Factura(facturaEntidad.getId(),
				vehiculoConvertidor.convertiraDomain(facturaEntidad.getVehiculoEntidad()),
				facturaEntidad.getFechaIngreso(),
				facturaEntidad.getFechaSalida(),
				facturaEntidad.getCosto());
	}
	
	public FacturaEntidad convertiraEntity(Factura factura) {
		return new FacturaEntidad(factura.getId(),
				vehiculoConvertidor.convertiraEntity(factura.getVehiculo()),
				factura.getFechaIngreso(),
				factura.getFechaSalida(),
				factura.getCosto());
	}
}
