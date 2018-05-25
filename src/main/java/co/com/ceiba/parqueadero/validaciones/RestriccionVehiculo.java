package co.com.ceiba.parqueadero.validaciones;



import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.excepcion.ParqueaderoExcepcion;
import co.com.ceiba.parqueadero.persistencia.VehiculoPersistencia;

@Service
public class RestriccionVehiculo {
	

	@Autowired
	VehiculoPersistencia vehiculoPersistencia;
	
	
	private static final String LETRA_A = "A";
	private static final String VEHICULO_YA_PARQUEADO = "El vehiculo ya se encuentra parqueado";
	private static final String PLACA_EN_DIA_NO_HABIL = "La placa ingresa en día no hábil";
	private static final String VEHICULO_NO_PARQUEADO = "El vehiculo no está en el parqueadero";
	private static final int LUNES = 2;
	private static final int DOMINGO = 1;
	
	
	public Vehiculo verificarIngresoVehiculo(Vehiculo vehiculo) {
		verificarPlaca(vehiculo.getPlaca(), calcularDiaDeSemana());
		Vehiculo vehiculoPersisted = vehiculoPersistencia.buscarPorPlaca(vehiculo.getPlaca());
		
		if(vehiculoPersisted == null)
			return vehiculo;
		else if(vehiculoPersisted.getParqueado())
			throw new ParqueaderoExcepcion(VEHICULO_YA_PARQUEADO);
		else
			vehiculo = vehiculoPersisted;
		
		return vehiculo;
	}
	
	public Vehiculo verificarSalidaVehiculo(Vehiculo vehiculo) {
		
	    Vehiculo vehiculoPersisted = vehiculoPersistencia.buscarPorPlaca(vehiculo.getPlaca());
		if(vehiculoPersisted == null)
			throw new ParqueaderoExcepcion(VEHICULO_NO_PARQUEADO);
		else if(!vehiculoPersisted.getParqueado())
			throw new ParqueaderoExcepcion(VEHICULO_NO_PARQUEADO);
		
		return vehiculoPersisted;
	}
	
	public void verificarPlaca(String placa, int diaDeSemana) {
		
		if((placa.startsWith(LETRA_A) || placa.startsWith(LETRA_A.toLowerCase())) && (diaDeSemana != DOMINGO && diaDeSemana != LUNES )) 
			throw new ParqueaderoExcepcion(PLACA_EN_DIA_NO_HABIL);

	}
	
	public int calcularDiaDeSemana() {
		Calendar fecha = Calendar.getInstance();
		return fecha.get(Calendar.DAY_OF_WEEK);
	}
	
}





