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
	private Long valorHoraCarro;
	
	@NotNull
	@Column
	private Long valorDiaCarro;
	
	@NotNull
	@Column
	private Long valorHoraMoto;
	
	@NotNull
	@Column
	private Long valorDiaMoto;
	
	public PreciosEntidad() {
		
	}
	
	public PreciosEntidad(int id, Long valorHoraCarro, Long valorDiaCarro, Long valorHoraMoto, Long valorDiaMoto) {
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

	public Long getValorHoraCarro() {
		return valorHoraCarro;
	}

	public void setValorHoraCarro(Long valorHoraCarro) {
		this.valorHoraCarro = valorHoraCarro;
	}

	public Long getValorDiaCarro() {
		return valorDiaCarro;
	}

	public void setValorDiaCarro(Long valorDiaCarro) {
		this.valorDiaCarro = valorDiaCarro;
	}

	public Long getValorHoraMoto() {
		return valorHoraMoto;
	}

	public void setValorHoraMoto(Long valorHoraMoto) {
		this.valorHoraMoto = valorHoraMoto;
	}

	public Long getValorDiaMoto() {
		return valorDiaMoto;
	}

	public void setValorDiaMoto(Long valorDiaMoto) {
		this.valorDiaMoto = valorDiaMoto;
	}
}
