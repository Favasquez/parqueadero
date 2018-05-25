package co.com.ceiba.parqueadero.dominio;

import java.util.Date;

public class Factura {
	
	private int id;
	private Vehiculo vehiculo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private double costo;
	
	public Factura() {
		
	}
	
	public Factura(int id, Vehiculo vehiculo, Date fechaIngreso, Date fechaSalida, double costo) {
		super();
		this.id = id;
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.costo = costo;
	}

	public int getId() {
		return id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public double getCosto() {
		return costo;
	}
	
}
