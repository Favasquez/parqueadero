package co.com.ceiba.parqueadero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.excepcion.ParqueaderoExcepcion;
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
	private RestriccionVehiculo restriccionVehiculo;
	
	@Before
	public void setUp() {
		restriccionVehiculo = new RestriccionVehiculo();
	}

	@Test
	public void verificarPlacaConALunes() {
		//arrange

		//act
		try {
			restriccionVehiculo.verificarPlaca(PLACA_CON_A, LUNES);
		}catch(ParqueaderoExcepcion e){
			//No exception can be thrown
			fail();
		}
	}
	
	@Test
	public void verificarPlacaSinALunes() {
		//arrange
		
		try {
			restriccionVehiculo.verificarPlaca(PLACA_SIN_A, LUNES);
		}catch(ParqueaderoExcepcion e){
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
		}catch(ParqueaderoExcepcion e) {
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
		}catch(ParqueaderoExcepcion e) {
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
		}catch(ParqueaderoExcepcion e) {
			assertEquals(PLACA_EN_DIA_NO_HABIL, e.getMessage());
		}
	}
	
}
