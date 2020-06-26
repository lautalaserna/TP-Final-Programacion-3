package Excepciones;

/**
 * @author Grupo 10
 * 
 * Esta clase representa una excepcion lanzada cuando se intenta realizar una accion invalida.
 */
public class AccionInvalidaException extends Exception {
	
	public AccionInvalidaException(String arg0) {
		super(arg0);
	}
	
}
