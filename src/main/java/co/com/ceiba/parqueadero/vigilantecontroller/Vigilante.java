package co.com.ceiba.parqueadero.vigilantecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.dominio.Factura;
import co.com.ceiba.parqueadero.dominio.Precios;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.service.VigilanteService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Vigilante {
	
	@Autowired
	VigilanteService vigilanteService;
	
	@GetMapping("/hola")
	public @ResponseBody String hola(@RequestParam String name){
		return "Hola "+name+"!!!";
	}

    @PostMapping("/ingresarvehiculo")
    public void ingresarVehiculo(@RequestBody Vehiculo vehiculo) {
    	vigilanteService.ingresarVehiculo(vehiculo);
    }
    
    @RequestMapping(value = "/vehiculosregistrados/carros", method = RequestMethod.GET)
    public @ResponseBody List<Vehiculo> reportarCarros(){
    	return vigilanteService.reporteVehiculosCarros();
    }
    
    @RequestMapping(value = "/vehiculosregistrados/motos", method = RequestMethod.GET)
    public @ResponseBody List<Vehiculo> reportarMotos(){
    	return vigilanteService.reporteVehiculosMotos();
    }

    @PostMapping("/retirarvehiculo")
    public void retirarVehiculo(@RequestBody String placa) {
    	vigilanteService.retirarVehiculo(placa);    	
    }
    
    @PostMapping("/ingresarprecios")
    public void ingresarPrecios(@RequestBody Precios precios) {
    	vigilanteService.ingresarPrecios(precios);
    }
    
    @GetMapping("/registros")
    public List<Factura> registros(){
    	return vigilanteService.registros();
    }
}



