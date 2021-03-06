package co.com.ceiba.parqueadero.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
import co.com.ceiba.parqueadero.entidades.VehiculoEntidad;
import co.com.ceiba.parqueadero.persistencia.VehiculoPersistencia;
import co.com.ceiba.parqueadero.repositoryjpa.VehiculoRepository;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoPersistenciaTest {

	private static final String PLACA = "XYZ123";
	private static final String TIPO_CARRO = "Carro";
	private static final String TIPO_MOTO = "Moto";
	private static final boolean PARQUEADO = true;
	 
	
	@InjectMocks
	VehiculoPersistencia vehiculoPersistencia;
	
	@Mock
	VehiculoRepository vehiculoRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void parquearVehiculoTest() {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withParqueado(PARQUEADO).build();
		when(vehiculoRepository.save(Mockito.any())).thenReturn(Mockito.any());
		
		vehiculoPersistencia.parquear(vehiculo);
		verify(vehiculoRepository).save(Mockito.any(VehiculoEntidad.class));
	}
	
	@Test
	public void traerPorPlacaTest() {
		
		//arrange
		VehiculoEntidad mock = Mockito.mock(VehiculoEntidad.class);
		when(vehiculoRepository.findByPlacaAllIgnoreCase(PLACA)).thenReturn(mock);
		
		//act
		Vehiculo result = vehiculoPersistencia.buscarPorPlaca(PLACA);
		
		//assert
		verify(vehiculoRepository).findByPlacaAllIgnoreCase(PLACA);
		assertNotNull(result);
	}
	
	@Test
	public void buscarPorPlacaYParqueadoTest() {
		
		//arrange
		VehiculoEntidad mock = Mockito.mock(VehiculoEntidad.class);
		when(vehiculoRepository.findByPlacaAllIgnoreCaseAndParqueado(PLACA, PARQUEADO)).thenReturn(mock);
		
		//act
		Vehiculo result = vehiculoPersistencia.buscarPorPlacaYParqueado(PLACA, PARQUEADO);
		
		//assert
		verify(vehiculoRepository).findByPlacaAllIgnoreCaseAndParqueado(PLACA, PARQUEADO);
		assertNotNull(result);
	}
	
	@Test
	public void cantidadTipoVehiculoTest() {
		
		//arrange
		long expected = 10;
		when(vehiculoRepository.countByTipoAllIgnoreCaseAndParqueado(TIPO_CARRO,PARQUEADO)).thenReturn(expected);
		
		//act
		long result = vehiculoPersistencia.cantidadTipoVehiculo(TIPO_CARRO, PARQUEADO);
		
		//assert
		verify(vehiculoRepository).countByTipoAllIgnoreCaseAndParqueado(TIPO_CARRO, PARQUEADO);
		assertEquals(expected, result);
	}
	
	@Test
	public void listarCarrosTest() {
		
		//arrange
		VehiculoEntidad mock = Mockito.mock(VehiculoEntidad.class);
		List<VehiculoEntidad> list = Arrays.asList(mock);
		when(vehiculoRepository.findByTipoAndParqueado(TIPO_CARRO, PARQUEADO)).thenReturn(list);
		
		//act
		List<Vehiculo> resultList = vehiculoPersistencia.listarCarros();
		
		//assert
		verify(vehiculoRepository).findByTipoAndParqueado(TIPO_CARRO, PARQUEADO);
		assertEquals(list.size(), resultList.size());
	}
	
	@Test
	public void listarMotosTest() {
		//arrange
		VehiculoEntidad mock = Mockito.mock(VehiculoEntidad.class);
		List<VehiculoEntidad> list = Arrays.asList(mock);
		when(vehiculoRepository.findByTipoAndParqueado(TIPO_MOTO, PARQUEADO)).thenReturn(list);
		
		//act
		List<Vehiculo> resultList = vehiculoPersistencia.listarMotos();
		
		//assert
		verify(vehiculoRepository).findByTipoAndParqueado(TIPO_MOTO, PARQUEADO);
		assertEquals(list.size(), resultList.size());
	}
	
	@Test
	public void retirarVehiculoTest() {
		
		//arrange
		VehiculoEntidad vehiculoEntidad = Mockito.mock(VehiculoEntidad.class);
		
		when(vehiculoRepository.findByPlacaAllIgnoreCase(PLACA)).thenReturn(vehiculoEntidad);
		when(vehiculoRepository.save(vehiculoEntidad)).thenReturn(vehiculoEntidad);
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withParqueado(true).build();

		//act
		vehiculoPersistencia.retirar(vehiculo);
		
		//assert
		verify(vehiculoRepository).save(vehiculoEntidad);
	}
}








