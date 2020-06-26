package Modelo;

import java.io.Serializable;

/**
 * Esta clase representa una contratacion que puede pertenecer a un titular dentro de la empresa, tiene atributos como el domicilio
 * asociado a la contratacion, el costo de la contratacion y un detalle respecto a la misma.
 * 
 * @author G10
 *
 */
public abstract class Contratacion implements IContratacion, Serializable {
	protected String domicilio;
	protected String detalle;
	protected double costo;

	public Contratacion(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public double getCosto() {
		return costo;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	@Override
	public String toString() {
		return domicilio;
	}
}