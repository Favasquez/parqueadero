package co.com.ceiba.parqueadero.validaciones;



import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.excepcion.ParqueaderoCustomException;
import co.com.ceiba.parqueadero.persistencia.VehiculoPersistencia;

@Service
public class RestriccionVehiculo {
	
	private static final String LETRA_A = "A";
	private static final String VEHICULO_YA_PARQUEADO = "El vehiculo ya se encuentra parqueado";
	private static final String PLACA_EN_DIA_NO_HABIL = "La placa ingresa en día no hábil";
	private static final String VEHICULO_NO_PARQUEADO = "El vehiculo no está en el parqueadero";
	private static final String NO_HAY_LOTES_DISPONIBLES = "No hay lotes disponibles";
	public static final String MOTO = "Moto";
	public static final String CARRO = "Carro";
	private static final int LUNES = 2;
	private static final int DOMINGO = 1;
	private static final long CANTIDAD_MAX_MOTOS = 10;
	private static final long CANTIDAD_MAX_CARROS = 20;

	@Autowired
	VehiculoPersistencia vehiculoPersistencia;
	
	public Vehiculo verificarIngresoVehiculo(Vehiculo vehiculo) {
		verificarPlaca(vehiculo.getPlaca(), calcularDiaDeSemana());
		if(!hayLotesDisponibles(vehiculo.getTipo()))
			throw new ParqueaderoCustomException(NO_HAY_LOTES_DISPONIBLES);
		
		Vehiculo vehiculoPersisted = vehiculoPersistencia.buscarPorPlaca(vehiculo.getPlaca());
		if(vehiculoPersisted == null)
			return vehiculo;
		else if(vehiculoPersisted.getParqueado())
			throw new ParqueaderoCustomException(VEHICULO_YA_PARQUEADO);
		
		vehiculo = vehiculoPersisted;
		return vehiculo;
	}	 
	
	public Vehiculo verificarSalidaVehiculo(String placa) {
	    Vehiculo vehiculoPersisted = vehiculoPersistencia.buscarPorPlaca(placa);
		if(vehiculoPersisted == null)
			throw new ParqueaderoCustomException(VEHICULO_NO_PARQUEADO);
		else if(!vehiculoPersisted.getParqueado())
			throw new ParqueaderoCustomException(VEHICULO_NO_PARQUEADO);
		
		return vehiculoPersisted;
	}

	
	public void verificarPlaca(String placa, int diaDeSemana) {
		if((placa.startsWith(LETRA_A) || placa.startsWith(LETRA_A.toLowerCase())) && (diaDeSemana != DOMINGO && diaDeSemana != LUNES )) 
			throw new ParqueaderoCustomException(PLACA_EN_DIA_NO_HABIL);
	}
	
	public int calcularDiaDeSemana() {
		Calendar fecha = Calendar.getInstance();
		return fecha.get(Calendar.DAY_OF_WEEK);
	}
	
	public boolean hayLotesDisponibles(String tipo) {
	    boolean esParqueado = true;
		long lotesDisponibles = vehiculoPersistencia.cantidadTipoVehiculo(tipo, esParqueado);
		if(tipo.equalsIgnoreCase(MOTO) && lotesDisponibles > CANTIDAD_MAX_MOTOS)
			return false;
		
		if(tipo.equalsIgnoreCase(CARRO) && lotesDisponibles > CANTIDAD_MAX_CARROS)
			return false;
		
		return true; 
	}
}

