package co.com.ceiba.parqueadero.dominio;

public class Vehiculo{
	
	private int id;
	private String placa;
	private String tipo;
	private int cilindraje;
	private boolean parqueado;

	
	public Vehiculo() { }
	
	public Vehiculo(int id, String placa, String tipo, int cilindraje, boolean parqueado) {
		super();
		this.id = id;
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.parqueado = parqueado;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public String getTipo() {
		return tipo;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	
	public boolean getParqueado() {
		return parqueado;
	}
}
