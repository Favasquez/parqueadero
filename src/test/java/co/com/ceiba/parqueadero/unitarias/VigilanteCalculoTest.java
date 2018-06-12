package co.com.ceiba.parqueadero.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.dominio.Factura;
import co.com.ceiba.parqueadero.dominio.Precios;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.PreciosPersistencia;
import co.com.ceiba.parqueadero.testdatabuilder.FacturaTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.PreciosTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;
import co.com.ceiba.parqueadero.util.VigilanteCalculos;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VigilanteCalculoTest {
	
	private static final long PRECIO_HORA_MOTO = 500;
	private static final long PRECIO_DIA_MOTO = 4000;
	
	@InjectMocks
	VigilanteCalculos vigilanteCalculos;
	
	@Mock
	PreciosPersistencia preciosPersistencia;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void cobrarTiempoCarroTest() {
		//arrange
		Calendar fechaIngreso = Calendar.getInstance();
		Calendar fechaSalida = Calendar.getInstance();
		fechaIngreso.set(2018, 1, 1, 0, 0, 0);
		fechaSalida.set(2018, 1, 30, 12, 0, 0);
		
		Vehiculo vehiculoCarro = new VehiculoTestDataBuilder().build();
		Precios precios = new PreciosTestDataBuilder().build();
		Factura factura = new FacturaTestDataBuilder().
				withVehiculo(vehiculoCarro).
				withFechaIngreso(fechaIngreso.getTime()).
				withFechaSalida(fechaSalida.getTime()).build();
		
		when(preciosPersistencia.traerPrecios()).thenReturn(precios);
		double expected = 240_000;
		//act
		double costo = vigilanteCalculos.cobrarTiempo(factura);
		
		//assert
		assertEquals(expected, costo, 0.0);
		verify(preciosPersistencia).traerPrecios();
	}
	
	@Test
	public void cobrarTiempoMotoTest() {
		//arrange
		Calendar fechaIngreso = Calendar.getInstance();
		Calendar fechaSalida = Calendar.getInstance();
		fechaIngreso.set(2018, 1, 1, 0, 0, 0);
		fechaSalida.set(2018, 1, 30, 12, 0, 0);
		
		Precios precios = new PreciosTestDataBuilder().build();
		
		Vehiculo vehiculoCarro = new VehiculoTestDataBuilder().
				withTipo("Moto").
				withCilindraje(150).build();
		
		Factura factura = new FacturaTestDataBuilder().
				withVehiculo(vehiculoCarro).
				withFechaIngreso(fechaIngreso.getTime()).
				withFechaSalida(fechaSalida.getTime()).build();
				
		double expected = 120_000;
		when(preciosPersistencia.traerPrecios()).thenReturn(precios);
		//act
		double costo = vigilanteCalculos.cobrarTiempo(factura);
		
		//assert
		assertEquals(expected, costo, 0.0);
		verify(preciosPersistencia).traerPrecios();
	}
	
	@Test
	public void calcularHorasTest() {
		
		//arrange
		Calendar fechaIngreso = Calendar.getInstance();
		Calendar fechaSalida = Calendar.getInstance();
		fechaIngreso.set(2018, 5, 23, 10, 10, 0);
		fechaSalida.set(2018, 5, 24, 10, 10, 0);
		double horasEsperadas = 24d;
		
		//act
		double horas = vigilanteCalculos.calcularHoras(fechaIngreso.getTime(), fechaSalida.getTime());
		
		//assert 
		assertEquals(horasEsperadas, horas, 0.00001);
	}
	
	@Test
	public void calcularHorasTestEnFalse() {
		//arrange
		Calendar fechaIngreso = Calendar.getInstance();
		Calendar fechaSalida = Calendar.getInstance();
		fechaIngreso.set(2018, 5, 23, 10, 10);
		fechaSalida.set(2018,5,24,10,10);
		double horasEsperadasFalse = 25d;
		
		//act
		double horas = vigilanteCalculos.calcularHoras(fechaIngreso.getTime(), fechaSalida.getTime());
		
		//assert
		assertNotEquals(horasEsperadasFalse, horas);
	}
	
	@Test
	public void calcularCostoMoto500() {
		//arrange
		double horas = 5d; 
		int cilindraje = 500;
		double costoEsperado = 2500d;
	
		//act
		double resultado = vigilanteCalculos.calcularCosto(horas, PRECIO_HORA_MOTO, PRECIO_DIA_MOTO, cilindraje);
		
		//assert
		assertEquals(costoEsperado,resultado,0.0);
	}
	
	@Test
	public void calcularCostoMoto600() {
		//arrange
		double horas = 34; 
		int cilindraje = 600;
		double costoEsperado = 10000d;
 
		//act
		double resultado = vigilanteCalculos.calcularCosto(horas, PRECIO_HORA_MOTO, PRECIO_DIA_MOTO, cilindraje);
		
		//assert
		assertEquals(costoEsperado,resultado,0.0);
	}
	 
	@Test
	public void calcularCostoMoto600_2() {
		//arrange
		double horas = 49; 
		int cilindraje = 600;
		double costoEsperado = 10500d;

		//act
		double resultado = vigilanteCalculos.calcularCosto(horas, PRECIO_HORA_MOTO, PRECIO_DIA_MOTO, cilindraje);
		
		//assert
		assertEquals(costoEsperado,resultado,0.0);
	}
	
	@Test
	public void calcularOchoHorasYMinutos() {
		
		//arrange
		double horas = 8.01;
		long precioDia = 8000;
		long precioHora = 1000;
		int cilindraje = 0;
		
		//act
		double result = vigilanteCalculos.calcularCosto(horas, precioHora, precioDia, cilindraje);
		double expected = 8000;
		
		//assert
		assertEquals(result,expected,0.0);
	}
}

	
	
	
	