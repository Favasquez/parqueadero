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
	
	private static final String CARRO = "Carro";
	private static final String MOTO = "Moto";
	private static final boolean PARQUEADO = true;
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
	
	public Vehiculo buscarPorPlacaYParqueado(String placa, boolean esParqueado) {
		VehiculoEntidad vehiculoEntidad = vehiculoRepository.findByPlacaAllIgnoreCaseAndParqueado(placa, esParqueado);
		return vehiculoEntidad != null ? vehiculoConvertidor.convertiraDomain(vehiculoEntidad):null;
	}
	
	public long cantidadTipoVehiculo(String tipo, boolean esParqueado) {
		return vehiculoRepository.countByTipoAllIgnoreCaseAndParqueado(tipo, esParqueado);
	}
	
	public List<Vehiculo> listarCarros() {
		List<Vehiculo> vehiculos = new ArrayList<>();
		vehiculoRepository.findByTipoAndParqueado(CARRO, PARQUEADO).forEach(vehiculo -> {
				vehiculos.add(vehiculoConvertidor.convertiraDomain(vehiculo));}
		);
		return vehiculos;
	}
	
	public List<Vehiculo> listarMotos(){
		List<Vehiculo> vehiculos = new ArrayList<>();
		vehiculoRepository.findByTipoAndParqueado(MOTO, PARQUEADO).forEach(vehiculo -> {
				vehiculos.add(vehiculoConvertidor.convertiraDomain(vehiculo));}
		);
		return vehiculos;
	}
	
	public void retirar(Vehiculo vehiculo) {
		VehiculoEntidad vehiculoEntidad = vehiculoRepository.findByPlacaAllIgnoreCase(vehiculo.getPlaca());
		vehiculoEntidad.setParqueado(false);
		vehiculoRepository.save(vehiculoEntidad);
	}
}
