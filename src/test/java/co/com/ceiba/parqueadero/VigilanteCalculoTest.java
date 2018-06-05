package co.com.ceiba.parqueadero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.util.VigilanteCalculos;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VigilanteCalculoTest {
	
	@Autowired
	private VigilanteCalculos vigilanteCalculos;
	
	private static final long PRECIO_HORA_MOTO = 500;
	private static final long PRECIO_DIA_MOTO = 4000;
	private static final long PRECIO_HORA_CARRO = 1000;
	private static final long PRECIO_DIA_CARRO = 8000;
	
	@Test
	public void calcularHorasTest() {
		
		//arrange
		Calendar fechaIngreso = Calendar.getInstance();
		Calendar fechaSalida = Calendar.getInstance();
		fechaIngreso.set(2018, 5, 23, 10, 10);
		fechaSalida.set(2018,5,24,10,10);
		double horasEsperadas = 24d;
		
		//act
		double horas = vigilanteCalculos.calcularHoras(fechaIngreso.getTime(), fechaSalida.getTime());
		
		//assert
		assertEquals(horasEsperadas, horas, 0.0);
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

	
	
	
	