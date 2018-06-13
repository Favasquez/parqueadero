package co.com.ceiba.parqueadero.unitarias;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.dominio.Precios;
import co.com.ceiba.parqueadero.entidades.PreciosEntidad;
import co.com.ceiba.parqueadero.testdatabuilder.PreciosTestDataBuilder;
import co.com.ceiba.parqueadero.util.PreciosConvertidor;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PreciosConvertidorTest {

	private PreciosConvertidor preciosConvertidor;
	private Precios preciosDomain;
	private PreciosEntidad preciosEntidad;
	
	@Before
	public void setUp() {
		preciosConvertidor = new PreciosConvertidor();
	}
	
	@Test
	public void convertirAEntityTest() {
		//arrange
		preciosDomain = new PreciosTestDataBuilder().build();
		
		//act
		preciosEntidad = preciosConvertidor.convertiraEntity(preciosDomain);
		
		//assert
		assertEquals(preciosDomain.getValorDiaCarro(),preciosEntidad.getValorDiaCarro());
		assertEquals(preciosDomain.getValorDiaMoto(),preciosEntidad.getValorDiaMoto());
		assertEquals(preciosDomain.getValorHoraCarro(),preciosEntidad.getValorHoraCarro());
		assertEquals(preciosDomain.getValorHoraMoto(), preciosEntidad.getValorHoraMoto());
	}
	
	@Test
	public void convertirADomainTest() {
		//arrange
		preciosDomain = new PreciosTestDataBuilder().build();
		preciosEntidad = preciosConvertidor.convertiraEntity(preciosDomain);
		
		//act
		preciosDomain = preciosConvertidor.convertiraDomain(preciosEntidad);
		
		//assert
		assertEquals(preciosEntidad.getValorDiaCarro(), preciosDomain.getValorDiaCarro());
		assertEquals(preciosEntidad.getValorDiaMoto(), preciosDomain.getValorDiaMoto());
		assertEquals(preciosEntidad.getValorHoraCarro(), preciosDomain.getValorHoraCarro());
		assertEquals(preciosEntidad.getValorHoraMoto(), preciosDomain.getValorHoraMoto());
	}
}
