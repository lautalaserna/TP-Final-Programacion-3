package Excepciones;

/**
 * @author Grupo 10
 * 
 * Esta clase representa una excepcion lanzada cuando se intenta instaciar un domicilio con direccion repetida.
 */
public class DomicilioRepetidoException extends Exception {
	private String direcDuplicada;
	
	public DomicilioRepetidoException(String arg0, String direccion) {
		super(arg0);
		this.direcDuplicada=direccion;
	}

	public String getDirecDuplicada() {
		return direcDuplicada;
	}
}
