package co.com.ceiba.parqueadero.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Vehiculo")
@Table(name = "vehiculos")
public class VehiculoEntidad {
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(unique = true)
	private int id;
	
	@NotNull
	@Column
	private String placa;
	
	@Column
	private String tipo;
	
	@NotNull
	@Column
	private int cilindraje;
	
	@NotNull
	@Column
	private boolean parqueado;
	

	public VehiculoEntidad() {
		
	}
	public VehiculoEntidad(int id, String placa, String tipo, int cilindraje, boolean parqueado) {
		this.id = id;
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.parqueado = parqueado;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	public boolean getParqueado() {
		return parqueado;
	}
	
	public void setParqueado(boolean parqueado) {
		this.parqueado = parqueado;
	}	
}
