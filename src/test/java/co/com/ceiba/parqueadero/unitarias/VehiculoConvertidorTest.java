package co.com.ceiba.parqueadero.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.entidades.VehiculoEntidad;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;
import co.com.ceiba.parqueadero.util.VehiculoConvertidor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoConvertidorTest {
	
	private VehiculoConvertidor vehiculoConvertidor;
	private Vehiculo vehiculo;
	private VehiculoEntidad vehiculoEntidad;
	
	
	
	@Before
	public void setUp() {
		vehiculoConvertidor = new VehiculoConvertidor();
	}
	 
	@Test
	public void convertirAEntidadTest() {
		//arrange
		vehiculo = new VehiculoTestDataBuilder().build();
		
		//act
		vehiculoEntidad = vehiculoConvertidor.convertiraEntity(vehiculo);
		
		//assert
		assertEquals(vehiculoEntidad.getPlaca(), vehiculo.getPlaca());
		assertEquals(vehiculoEntidad.getTipo(), vehiculo.getTipo());
		assertEquals(vehiculoEntidad.getCilindraje(), vehiculo.getCilindraje());
		assertNotEquals(vehiculoEntidad.getClass(), vehiculo.getClass());
	}
	
	@Test
	public void convertirADomianTest() {
		//arrange
		vehiculo = new VehiculoTestDataBuilder().build();
		vehiculoEntidad = vehiculoConvertidor.convertiraEntity(vehiculo);
				
		//act
		Vehiculo finalVehiculo = vehiculoConvertidor.convertiraDomain(vehiculoEntidad);
		
		//assert
		assertEquals(finalVehiculo.getPlaca(), vehiculoEntidad.getPlaca());
		assertEquals(finalVehiculo.getTipo(), vehiculoEntidad.getTipo());
		assertEquals(finalVehiculo.getCilindraje(), vehiculoEntidad.getCilindraje());
		assertNotEquals(finalVehiculo.getClass(), vehiculoEntidad.getClass());
	}
}
