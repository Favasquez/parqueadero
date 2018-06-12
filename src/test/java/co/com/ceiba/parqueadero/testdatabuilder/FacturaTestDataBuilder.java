package co.com.ceiba.parqueadero.testdatabuilder;

import java.util.Date;

import co.com.ceiba.parqueadero.dominio.Factura;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class FacturaTestDataBuilder {
	
	private static final int ID = 1;
	private static final Vehiculo VEHICULO = new VehiculoTestDataBuilder().build();
	private static final Date FECHA_INGRESO = new Date();
	private static final Date FECHA_SALIDA = new Date();
	private static final double COSTO = 0;
	
	private int id;
	private Vehiculo vehiculo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private double costo;
	
	public FacturaTestDataBuilder() {
		this.id = ID;
		this.vehiculo = VEHICULO;
		this.fechaIngreso = FECHA_INGRESO;
		this.fechaSalida = FECHA_SALIDA;
		this.costo = COSTO;
	}
	 
	public FacturaTestDataBuilder withVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}
	
	public FacturaTestDataBuilder withFechaIngreso(Date fecha) {
		this.fechaIngreso = fecha;
		return this;
	}
	
	public FacturaTestDataBuilder withFechaSalida(Date fecha) {
		this.fechaSalida = fecha;
		return this;
	}
	
	public FacturaTestDataBuilder withCosto(double costo) {
		this.costo = costo;
		return this;
	}
	
	public Factura build() {
		return new Factura(this.id, this.vehiculo, this.fechaIngreso, this.fechaSalida, this.costo);
	}
}
