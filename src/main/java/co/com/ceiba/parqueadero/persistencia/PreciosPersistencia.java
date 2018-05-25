package co.com.ceiba.parqueadero.persistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Precios;
import co.com.ceiba.parqueadero.entidades.PreciosEntidad;
import co.com.ceiba.parqueadero.repositoryjpa.PreciosRepository;
import co.com.ceiba.parqueadero.util.PreciosConvertidor;

@Service
public class PreciosPersistencia {
	
	@Autowired
	PreciosRepository preciosRepository;
	
	PreciosConvertidor convertidor = new PreciosConvertidor();
	
	private static final int ID_PRECIOS = 1;
	public void establecerPrecios(Precios precios) {
		
		PreciosEntidad precioEntidad = convertidor.convertiraEntity(precios);
		preciosRepository.save(precioEntidad);
	}
	
	public Precios traerPrecios() {
		PreciosEntidad preciosEntidad = preciosRepository.findById(ID_PRECIOS);
		
		return convertidor.convertiraDomain(preciosEntidad);
	}
}
