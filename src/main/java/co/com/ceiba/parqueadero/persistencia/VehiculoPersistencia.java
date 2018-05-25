package co.com.ceiba.parqueadero.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.entidades.VehiculoEntidad;
import co.com.ceiba.parqueadero.repositoryjpa.VehiculoRepository;
import co.com.ceiba.parqueadero.util.VehiculoConvertidor;

@Service
public class VehiculoPersistencia {
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	VehiculoConvertidor vehiculoConvertidor = new VehiculoConvertidor();
	
	public void parquear(Vehiculo vehiculo) {
		
		VehiculoEntidad vehiculoEntidad = vehiculoConvertidor.convertiraEntity(vehiculo);
		vehiculoEntidad.setParqueado(true);
		vehiculoRepository.save(vehiculoEntidad);
	}
	
	public Vehiculo buscarPorPlaca(String placa) {
		VehiculoEntidad vehiculoEntidad = vehiculoRepository.findByPlacaAllIgnoreCase(placa);
		return vehiculoEntidad != null ? vehiculoConvertidor.convertiraDomain(vehiculoEntidad):null;
	}
	
	public VehiculoEntidad buscarPorPlacaYParqueado(String placa, boolean esParqueado) {
		VehiculoEntidad vehiculoEntidad = vehiculoRepository.findByPlacaAllIgnoreCaseAndParqueado(placa, esParqueado);
		return vehiculoEntidad != null ? vehiculoEntidad:null;
	}
	
	public List<Vehiculo> list() {
		List<Vehiculo> motos = new ArrayList<>();
		vehiculoRepository.findAll().forEach(moto -> 
			motos.add(vehiculoConvertidor.convertiraDomain(moto))
		);
		return motos;
	}
	
	public void retirar(Vehiculo vehiculo) {
		VehiculoEntidad vehiculoEntidad = vehiculoRepository.findByPlacaAllIgnoreCase(vehiculo.getPlaca());
		vehiculoEntidad.setParqueado(false);
		vehiculoRepository.save(vehiculoEntidad);
	}
}
