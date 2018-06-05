package co.com.ceiba.parqueadero.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExcepcionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ParqueaderoCustomException.class)
	
	public ResponseEntity<Object> manejarExcepcion(ParqueaderoCustomException ex, WebRequest request){
		
		String bodyOfResponse = "An error has ocurred.\n" + ex.getMessage();
		return new ResponseEntity<>(bodyOfResponse, HttpStatus.CONFLICT);
	}
}
