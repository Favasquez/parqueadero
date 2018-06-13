package co.com.ceiba.parqueadero.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "precios")
public class PreciosEntidad {
	
	
	@Id
	@NotNull
	@Column(unique = true)
	private int id;
	
	@NotNull
	@Column
	private long valorHoraCarro;
	
	@NotNull
	@Column
	private long valorDiaCarro;
	
	@NotNull
	@Column
	private long valorHoraMoto;
	
	@NotNull
	@Column
	private long valorDiaMoto;
	
	public PreciosEntidad() {
		
	}
	
	public PreciosEntidad(int id, long valorHoraCarro, long valorDiaCarro, long valorHoraMoto, long valorDiaMoto) {
		super();
		this.id = id;
		this.valorHoraCarro = valorHoraCarro;
		this.valorDiaCarro = valorDiaCarro;
		this.valorHoraMoto = valorHoraMoto;
		this.valorDiaMoto = valorDiaMoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getValorHoraCarro() {
		return valorHoraCarro;
	}

	public void setValorHoraCarro(long valorHoraCarro) {
		this.valorHoraCarro = valorHoraCarro;
	}

	public long getValorDiaCarro() {
		return valorDiaCarro;
	}

	public void setValorDiaCarro(long valorDiaCarro) {
		this.valorDiaCarro = valorDiaCarro;
	}

	public long getValorHoraMoto() {
		return valorHoraMoto;
	}

	public void setValorHoraMoto(long valorHoraMoto) {
		this.valorHoraMoto = valorHoraMoto;
	}

	public long getValorDiaMoto() {
		return valorDiaMoto;
	}

	public void setValorDiaMoto(long valorDiaMoto) {
		this.valorDiaMoto = valorDiaMoto;
	}
}
