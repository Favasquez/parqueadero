package co.com.ceiba.parqueadero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Factura;
import co.com.ceiba.parqueadero.dominio.Precios;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.FacturaPersistencia;
import co.com.ceiba.parqueadero.persistencia.PreciosPersistencia;
import co.com.ceiba.parqueadero.persistencia.VehiculoPersistencia;
import co.com.ceiba.parqueadero.validaciones.RestriccionVehiculo;


@Service
public class VigilanteService {
	
	@Autowired
	RestriccionVehiculo restriccionVehiculo;
	
	@Autowired
	VehiculoPersistencia vehiculoPersistencia;
	
	@Autowired
	FacturaPersistencia facturaPersistencia;
	
	@Autowired
	PreciosPersistencia preciosPersistencia;
	
	public void ingresarVehiculo(Vehiculo vehiculo) {
    	vehiculo = restriccionVehiculo.verificarIngresoVehiculo(vehiculo);
    	vehiculoPersistencia.parquear(vehiculo);
    	facturaPersistencia.registrarFactura(vehiculo);
	}
	
	public List<Vehiculo> reporteVehiculos(){
		return vehiculoPersistencia.list();
	}
	
	public void retirarVehiculo(String placa) {
    	Vehiculo vehiculo = restriccionVehiculo.verificarSalidaVehiculo(placa);
    	vehiculoPersistencia.retirar(vehiculo);
    	facturaPersistencia.registrarSalidaEnFactura(vehiculo);
	}
	
    public String ingresarPrecios(Precios precios) {
    	preciosPersistencia.establecerPrecios(precios);
    	return "Ingresado con éxito";
    }
    
    public List<Factura> registros(){
    	return facturaPersistencia.list();
    }
}
