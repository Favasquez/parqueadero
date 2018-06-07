package co.com.ceiba.parqueadero.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.excepcion.ParqueaderoCustomException;
import co.com.ceiba.parqueadero.persistencia.VehiculoPersistencia;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;
import co.com.ceiba.parqueadero.validaciones.RestriccionVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestriccionVehiculoTest {
	
	private static final String PLACA_CON_A = "ABC123";
	private static final String PLACA_SIN_A = "XYZ123";
	private static final String PLACA_EN_DIA_NO_HABIL = "La placa ingresa en día no hábil";
	private static final int DOMINGO = 1;
	private static final int LUNES = 2;
	private static final int MIERCOLES = 4;
	
	@InjectMocks
	RestriccionVehiculo restriccionVehiculo;
	
	@Mock
	VehiculoPersistencia vehiculoPersistencia;	

	@Test
	public void verificarIngresoVehiculoTest() {
		//arrange
		final String TIPO = RestriccionVehiculo.CARRO;
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		when(vehiculoPersistencia.buscarPorPlaca(vehiculo.getPlaca()))
			.thenReturn(vehiculo);
		
		//act
		Vehiculo sameVehiculo = vehiculoPersistencia.buscarPorPlaca(vehiculo.getPlaca());
		
		assertEquals(vehiculo,sameVehiculo);
		verify(vehiculoPersistencia).buscarPorPlaca(vehiculo.getPlaca());
	}
	
	@Test
	public void hayLotesDisponiblesMotoTest() {
		//arrange
		final String TIPO = RestriccionVehiculo.MOTO;
		final boolean ES_PARQUEADO = true;
		final long INFERIOR_A_LIMITE = 9;
		VehiculoTestDataBuilder vehiculoTDB = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTDB.withTipo(TIPO).build();
		
		when(vehiculoPersistencia.cantidadTipoVehiculo(vehiculo.getTipo(), ES_PARQUEADO)).thenReturn(INFERIOR_A_LIMITE);
		
		//act
		boolean result = restriccionVehiculo.hayLotesDisponibles(TIPO);
		
		//assert
		verify(vehiculoPersistencia).cantidadTipoVehiculo(TIPO,ES_PARQUEADO);
		assertTrue(result);
	}
	
	@Test
	public void falseHayLotesDisponiblesMotoTest() {
		//arrange
		final String TIPO = RestriccionVehiculo.MOTO;
		final boolean ES_PARQUEADO = true;
		final long SUPERIOR_A_LIMITE = 11;
		VehiculoTestDataBuilder vehiculoTDB = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTDB.withTipo(TIPO).build();
		
		when(vehiculoPersistencia.cantidadTipoVehiculo(vehiculo.getTipo(), ES_PARQUEADO)).thenReturn(SUPERIOR_A_LIMITE);
		
		//act
		boolean result = restriccionVehiculo.hayLotesDisponibles(TIPO);
		
		//assert
		verify(vehiculoPersistencia).cantidadTipoVehiculo(TIPO,ES_PARQUEADO);
		assertFalse(result);
	}
	
	@Test
	public void hayLotesDisponiblesCarroTest() {
		//arrange
		
		final String TIPO = RestriccionVehiculo.CARRO;
		final boolean ES_PARQUEADO = true;
		final long INFERIOR_A_LIMITE = 9;
		VehiculoTestDataBuilder vehiculoTDB = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTDB.withTipo(TIPO).build();
		
		when(vehiculoPersistencia.cantidadTipoVehiculo(vehiculo.getTipo(), ES_PARQUEADO)).thenReturn(INFERIOR_A_LIMITE);
		
		//act
		boolean result = restriccionVehiculo.hayLotesDisponibles(TIPO);
		
		//assert
		verify(vehiculoPersistencia).cantidadTipoVehiculo(TIPO,ES_PARQUEADO);
		assertTrue(result);
	}
	
	@Test
	public void falseHayLotesDisponiblesCarroTest() {
		//arrange
		final String TIPO = RestriccionVehiculo.CARRO;
		final boolean ES_PARQUEADO = true;
		final long SUPERIOR_A_LIMITE = 21;
		VehiculoTestDataBuilder vehiculoTDB = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTDB.withTipo(TIPO).build();
		
		when(vehiculoPersistencia.cantidadTipoVehiculo(vehiculo.getTipo(), ES_PARQUEADO)).thenReturn(SUPERIOR_A_LIMITE);
		
		//act
		boolean result = restriccionVehiculo.hayLotesDisponibles(TIPO);
		
		//assert
		verify(vehiculoPersistencia).cantidadTipoVehiculo(TIPO,ES_PARQUEADO);
		assertFalse(result);
	}
	
	@Test
	public void verificarPlacaConALunes() {
		//arrange

		//act
		try {
			restriccionVehiculo.verificarPlaca(PLACA_CON_A, LUNES);
		}catch(ParqueaderoCustomException e){
			//No exception can be thrown
			fail();
		}
	}
	
	@Test
	public void verificarPlacaSinALunes() {
		//arrange
		
		try {
			restriccionVehiculo.verificarPlaca(PLACA_SIN_A, LUNES);
		}catch(ParqueaderoCustomException e){
			//No exception can be thrown
			fail();
		}
	}
	
	@Test
	public void  verificarPlacaConADomingo() {
		//arrange
		
		//act
		try {
			restriccionVehiculo.verificarPlaca(PLACA_CON_A, DOMINGO);
		}catch(ParqueaderoCustomException e) {
			//No exception can be thrown
			fail();
		}
	}
	
	@Test
	public void verificarPlacaPlacaSinADomingo() {
		//arrange
		
		//act
		try {
			restriccionVehiculo.verificarPlaca(PLACA_SIN_A, DOMINGO);
		}catch(ParqueaderoCustomException e) {
			//No exception can be thrown
			fail();
		}
	}
	
	@Test
	public void verificarPlacaConANoLunesNiDomingo() {
		//arrange
		
		//act
		try {
			restriccionVehiculo.verificarPlaca(PLACA_CON_A, MIERCOLES);
			fail();
		}catch(ParqueaderoCustomException e) {
			assertEquals(PLACA_EN_DIA_NO_HABIL, e.getMessage());
		}
	}
}
