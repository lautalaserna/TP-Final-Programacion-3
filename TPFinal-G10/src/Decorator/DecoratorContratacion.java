package Decorator;

import java.io.Serializable;

import Modelo.IContratacion;

public abstract class DecoratorContratacion implements IContratacion, Serializable{
	protected IContratacion encapsulado;

	/**Decorator abstracto de una IContratacion
	 * @param encapsulado es el encapsulado que se esta decorando
	 */
	public DecoratorContratacion(IContratacion encapsulado) {
		this.encapsulado = encapsulado;
	}

	@Override
	public String getDetalle() {
		return encapsulado.getDetalle();
	}

	@Override
	public double getCosto() {
		return encapsulado.getCosto();
	}

	@Override
	public String getDomicilio() {
		return encapsulado.getDomicilio();
	}
	
	@Override
	public String toString() {
		return this.getDomicilio();
	}
}
