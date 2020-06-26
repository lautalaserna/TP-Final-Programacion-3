package Excepciones;

import Modelo.Titular;

/**
 * Excepcion para cuando se intenta crear un titular ya existente en el sistema
 * 
 * @author G10
 *
 */
public class TitularDuplicadoException extends Exception {
	private Titular t;

	public TitularDuplicadoException(Titular t) {
		this.t= t;
	}

	@Override
	public String getMessage() {
		return "El DNI: " + t.getDni() + " ya existe.";
	}

}
