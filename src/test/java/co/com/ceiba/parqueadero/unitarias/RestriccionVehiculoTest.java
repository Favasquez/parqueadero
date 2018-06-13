package co.com.ceiba.parqueadero.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
	private static final String VEHICULO_YA_PARQUEADO = "El vehiculo ya se encuentra parqueado";
	private static final int DOMINGO = 1;
	private static final int LUNES = 2;
	private static final int MIERCOLES = 4;
	
	@InjectMocks
	RestriccionVehiculo restriccionVehiculo;
	
	@Mock
	VehiculoPersistencia vehiculoPersistencia;
	
	
	

	@Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void verificarIngresoVehiculoTest() {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		when(vehiculoPersistencia.buscarPorPlaca(vehiculo.getPlaca()))
			.thenReturn(vehiculo);
		
		//act
		Vehiculo sameVehiculo = restriccionVehiculo.verificarIngresoVehiculo(vehiculo);
		
		//assert
		assertEquals(vehiculo,sameVehiculo);
		verify(vehiculoPersistencia).buscarPorPlaca(vehiculo.getPlaca());
	}
	
//	@Test
//	public void verificarIngresoCarroSinLotes() {
//		
//		//arrange
//		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
//		RestriccionVehiculo restriccionVehiculoMock = Mockito.mock(RestriccionVehiculo.class);
//		
//		doNothing().when(restriccionVehiculoMock).verificarPlaca(Mockito.eq(PLACA_SIN_A),Mockito.anyInt());
//		when(restriccionVehiculoMock.hayLotesDisponibles(TIPO_CARRO)).thenReturn(false);
//		
//		//act
//		try {
//			restriccionVehiculoMock.verificarIngresoVehiculo(vehiculo);
//			fail();
//		}catch(ParqueaderoCustomException ex) {
//			assertEquals(NO_HAY_LOTES_DISPONIBLES, ex.getMessage());
//		}
//		
//		
//		
//		
//	}
	
	@Test
	public void verificarIngresoVehiculoParqueado() {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withParqueado(true).build();
		
		when(vehiculoPersistencia.buscarPorPlaca(vehiculo.getPlaca()))
			.thenReturn(vehiculo);
		 
		try {
			restriccionVehiculo.verificarIngresoVehiculo(vehiculo);
			fail();
		}catch(Exception e){
			assertEquals(VEHICULO_YA_PARQUEADO, e.getMessage());
		}
	}
	
	@Test
	public void verificarSalidaVehiculoTest() {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withParqueado(true).build();
		
		when(vehiculoPersistencia.buscarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);
		
		//act
		Vehiculo sameVehiculo = restriccionVehiculo.verificarSalidaVehiculo(vehiculo.getPlaca());
		
		//assert
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
