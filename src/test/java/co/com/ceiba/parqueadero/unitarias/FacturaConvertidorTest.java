package co.com.ceiba.parqueadero.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.dominio.Factura;
import co.com.ceiba.parqueadero.entidades.FacturaEntidad;
import co.com.ceiba.parqueadero.testdatabuilder.FacturaTestDataBuilder;
import co.com.ceiba.parqueadero.util.FacturaConvertidor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacturaConvertidorTest {
	
	private FacturaConvertidor facturaConvertidor;
	private Factura facturaDomain;
	private FacturaEntidad facturaEntidad;
	
	@Before
	public void setUp() {
		facturaConvertidor = new FacturaConvertidor();
	}
	
	@Test
	public void convertirAEntityTest() {
		//arrange
		facturaDomain = new FacturaTestDataBuilder().build();
		
		//act
		facturaEntidad = facturaConvertidor.convertiraEntity(facturaDomain);
		
		//assert
		assertEquals(facturaDomain.getCosto(), facturaEntidad.getCosto(), 0.0);
		assertEquals(facturaDomain.getVehiculo().getPlaca(), facturaEntidad.getVehiculoEntidad().getPlaca());
		assertNotEquals(facturaDomain.getClass(), facturaEntidad.getClass());
	}
	
	@Test
	public void convertiADomainTest() {
		//arrange
		facturaDomain = new FacturaTestDataBuilder().build();
		facturaEntidad = facturaConvertidor.convertiraEntity(facturaDomain);
		
		//act
		facturaDomain = facturaConvertidor.convertiraDomain(facturaEntidad);
		
		//assert
		assertEquals(facturaEntidad.getCosto(), facturaEntidad.getCosto(),0.0);
		assertEquals(facturaEntidad.getVehiculoEntidad().getPlaca(), facturaDomain.getVehiculo().getPlaca());
	}
}
