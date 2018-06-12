package co.com.ceiba.parqueadero.testdatabuilder;

import co.com.ceiba.parqueadero.dominio.Precios;

public class PreciosTestDataBuilder {
	
	private static final int ID = 1;
	private static final long VALOR_HORA_CARRO = 1000;
	private static final long VALOR_DIA_CARRO = 8000;
	private static final long VALOR_HORA_MOTO = 500;
	private static final long VALOR_DIA_MOTO = 4000;
	
	private int id;
	private long valorHoraCarro;
	private long valorDiaCarro;
	private long valorHoraMoto;
	private long valorDiaMoto;
	
	public PreciosTestDataBuilder() {
		this.id = ID;
		this.valorHoraCarro = VALOR_HORA_CARRO;
		this.valorDiaCarro = VALOR_DIA_CARRO;
		this.valorHoraMoto = VALOR_HORA_MOTO;
		this.valorDiaMoto = VALOR_DIA_MOTO;
	}
	
	public PreciosTestDataBuilder withValorHoraCarro(long valor) {
		this.valorHoraCarro = valor;
		return this;
	}
	
	public PreciosTestDataBuilder withValorDiaCarro(long valor) {
		this.valorDiaCarro = valor;
		return this;
	}
	
	public PreciosTestDataBuilder withValorHoraMoto(long valor) {
		this.valorHoraMoto = valor;
		return this;
	}
	
	public PreciosTestDataBuilder withValorDiaMoto(long valor) {
		this.valorDiaMoto = valor;
		return this;
	}
	
	public Precios build() {
		return new Precios(this.id,this.valorHoraCarro,this.valorDiaCarro,this.valorHoraMoto,this.valorDiaMoto);
	}
}
