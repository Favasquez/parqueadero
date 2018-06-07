package co.com.ceiba.parqueadero.testdatabuilder;

import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private static final int ID = 1;
	private static final String PLACA = "XYZ123";
	private static final String TIPO = "Moto";
	private static final int CILINDRAJE = 150;
	private static final boolean PARQUEADO = false;
	
	private int id;
	private String placa;
	private String tipo;
	private int cilindraje;
	private boolean parqueado;
	
	public VehiculoTestDataBuilder() {
		this.id = ID;
		this.placa = PLACA;
		this.tipo = TIPO;
		this.cilindraje = CILINDRAJE;
		this.parqueado = PARQUEADO;
	}
	
	public VehiculoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder withTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}
	
	public VehiculoTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public VehiculoTestDataBuilder withParqueado(boolean parqueado) {
		this.parqueado = parqueado;
		return this;
	}
	
	public Vehiculo build(){
		return new Vehiculo(this.id,this.placa,this.tipo,this.cilindraje,this.parqueado);
	}
	
}
