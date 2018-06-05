package co.com.ceiba.parqueadero.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Factura;
import co.com.ceiba.parqueadero.dominio.Precios;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.PreciosPersistencia;

@Service
public class VigilanteCalculos {
	
	@Autowired
	PreciosPersistencia preciosPersistencia;
	
	private static final double INCREMENTO = 2000D;
	private static final long MIN_HORAS = 9L;
	private static final long HORAS_DIA = 24L;
	private static final String TIPO_CARRO = "Carro";
	private static final int CILINDRAJE_CERO = 0;
	private static final int LIMITE_CILINDRAJE = 500;
	
	public double cobrarTiempo(Factura factura) {
		
		Precios precios = preciosPersistencia.traerPrecios();
		double horas = calcularHoras(factura.getFechaIngreso(), factura.getFechaSalida());
		Vehiculo vehiculo = factura.getVehiculo();
		if(vehiculo.getTipo().equalsIgnoreCase(TIPO_CARRO)) {
			return calcularCosto(horas,precios.getValorHoraCarro(),precios.getValorDiaCarro(),CILINDRAJE_CERO);
		}
		
		return calcularCosto(horas,precios.getValorHoraMoto(),precios.getValorDiaMoto(),factura.getVehiculo().getCilindraje());
	}
	
	public double calcularHoras(Date inicio, Date fin) {
		
		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFin = Calendar.getInstance();
		fechaInicio.setTime(inicio);
		fechaFin.setTime(fin);
		double diferenciaEnMiliSegundos = (double)(fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis());
		
		return diferenciaEnMiliSegundos / (60*60*1000);//return difference in hours
	}
	
	public double calcularCosto(double horas, long precioHora, long precioDia, int cilindraje) {
		
		double restoHoras = 0;
		int dias = 0;
		double recargo = (cilindraje > LIMITE_CILINDRAJE) ? INCREMENTO : 0;
		dias = (int)(horas / HORAS_DIA);
		restoHoras = Math.ceil(((horas / HORAS_DIA) % 1)*24);
		double costoPorDia = (double) dias * precioDia;
		double costoPorHora = (restoHoras < MIN_HORAS)? restoHoras * precioHora : precioDia;
		return costoPorDia + costoPorHora + recargo;
	}
	
}
