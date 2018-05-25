package co.com.ceiba.parqueadero.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Factura;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.entidades.FacturaEntidad;
import co.com.ceiba.parqueadero.repositoryjpa.FacturaRepository;
import co.com.ceiba.parqueadero.repositoryjpa.VehiculoRepository;
import co.com.ceiba.parqueadero.util.FacturaConvertidor;
import co.com.ceiba.parqueadero.util.VigilanteCalculos;


@Service
public class FacturaPersistencia {
	
	@Autowired
	FacturaRepository facturaRepository;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Autowired
	VigilanteCalculos vigilanteCalculos;
	
	FacturaConvertidor facturaConvertidor = new FacturaConvertidor();
	
	public void registrarFactura(Vehiculo vehiculo) {
		FacturaEntidad facturaEntidad = new FacturaEntidad();
		facturaEntidad.setVehiculoEntidad(vehiculoRepository.findByPlacaAllIgnoreCase(vehiculo.getPlaca()));
		facturaEntidad.setFechaIngreso(new Date());
		facturaRepository.save(facturaEntidad);
	}
	
	public void registrarSalidaEnFactura(Vehiculo vehiculo) {
		FacturaEntidad facturaEntidad = facturaRepository.findBillToUpdate(vehiculo.getId());
		facturaEntidad.setFechaSalida(new Date());
		facturaEntidad.setCosto(vigilanteCalculos.cobrarTiempo(facturaConvertidor.convertiraDomain(facturaEntidad)));
		facturaRepository.save(facturaEntidad);
	}
	
	public List<Factura> list(){
		List<Factura> facturas = new ArrayList<>();
		facturaRepository.findAll().forEach(facturaEntidad -> 
				facturas.add(facturaConvertidor.convertiraDomain(facturaEntidad))
				);
		return facturas;
	}
}
