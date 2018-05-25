package co.com.ceiba.parqueadero.dominio;

public class Precios {
	private int id;
	private long valorHoraCarro;
	private long valorDiaCarro;
	private long valorHoraMoto;
	private long valorDiaMoto;

	public Precios() {
		
	}

	public Precios(int id, long valorHoraCarro, long valorDiaCarro, long valorHoraMoto, long valorDiaMoto) {
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

	public long getValorHoraCarro() {
		return valorHoraCarro;
	}

	public long getValorDiaCarro() {
		return valorDiaCarro;
	}

	public long getValorHoraMoto() {
		return valorHoraMoto;
	}

	public long getValorDiaMoto() {
		return valorDiaMoto;
	}
}
