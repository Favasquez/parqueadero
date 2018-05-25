package co.com.ceiba.parqueadero.excepcion;

public class ParqueaderoExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParqueaderoExcepcion(String mensaje) {
		super(mensaje);
	}
}
