package co.com.ceiba.parqueadero.excepcion;

public class ParqueaderoCustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParqueaderoCustomException(String mensaje) {
		super(mensaje);
	}
}
