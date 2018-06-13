package co.com.ceiba.parqueadero.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity(name = "Facturas")
@Table(name="facturas")
public class FacturaEntidad {
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(unique=true)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
	private VehiculoEntidad vehiculoEntidad;
	
	@NotNull
	@Column
	private Date fechaIngreso;
	
	@Column
	private Date fechaSalida;
	
	@Column
	private double costo;
	
	public FacturaEntidad() {
		
	}
	public FacturaEntidad(int id, VehiculoEntidad vehiculoEntidad, Date fechaIngreso, Date fechaSalida, double costo) {
		super();
		this.id = id;
		this.vehiculoEntidad = vehiculoEntidad;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.costo = costo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public VehiculoEntidad getVehiculoEntidad() {
		return vehiculoEntidad;
	}
	
	public void setVehiculoEntidad(VehiculoEntidad vehiculoEntidad) {
		this.vehiculoEntidad = vehiculoEntidad;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public double getCosto() {
		return costo;
	}
	
	public void setCosto(double costo) {
		this.costo = costo;
	}
}
