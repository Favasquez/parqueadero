package co.com.ceiba.parqueadero.vigilantecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.dominio.Precios;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.FacturaPersistencia;
import co.com.ceiba.parqueadero.persistencia.PreciosPersistencia;
import co.com.ceiba.parqueadero.persistencia.VehiculoPersistencia;
import co.com.ceiba.parqueadero.util.VigilanteCalculos;
import co.com.ceiba.parqueadero.validaciones.RestriccionVehiculo;


@RestController
public class Vigilante {
	
	
	@Autowired
	VehiculoPersistencia vehiculoPersistencia;
	
	@Autowired
	PreciosPersistencia preciosPersistencia;
	
	@Autowired
	VigilanteCalculos vigilanteCalculo;
	
	@Autowired 
	RestriccionVehiculo restriccionVehiculo;
	
	@Autowired
	FacturaPersistencia facturaPersistencia;
	
	@GetMapping("/hola")
	public @ResponseBody String hola(@RequestParam String name){
		return "<h1>Hola mundo!!!</h1>";
	}
	
    @PostMapping("/ingresarvehiculo")
    public void ingresarVehiculo(@RequestBody Vehiculo vehiculo) {
    	vehiculo = restriccionVehiculo.verificarIngresoVehiculo(vehiculo);
    	vehiculoPersistencia.parquear(vehiculo);
    	facturaPersistencia.registrarFactura(vehiculo);
    }
    
    @RequestMapping(value = "/vehiculosregistrados", method = RequestMethod.GET)
    public @ResponseBody List<Vehiculo> reporteVehiculos(){
    	return vehiculoPersistencia.list();
    }

    @PostMapping("/retirarvehiculo")
    public @ResponseBody String retirarVehiculo(@RequestBody Vehiculo vehiculo) {
    	vehiculo = restriccionVehiculo.verificarSalidaVehiculo(vehiculo);
    	vehiculoPersistencia.retirar(vehiculo);
    	facturaPersistencia.registrarSalidaEnFactura(vehiculo);
    	return "Retirado";
    }
    
    @PostMapping("/ingresarprecios")
    public @ResponseBody String ingresarPrecios(@RequestBody Precios precios) {
    	preciosPersistencia.establecerPrecios(precios);
    	return "Ingresado con éxito";
    }

}



