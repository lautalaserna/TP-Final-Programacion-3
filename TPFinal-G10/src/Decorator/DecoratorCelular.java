package Decorator;

import java.io.Serializable;

import Modelo.IContratacion;

public class DecoratorCelular extends DecoratorContratacion{

	public DecoratorCelular(IContratacion encapsulado) {
		super(encapsulado);
	}

	@Override
	public String getDetalle() {
		return super.getDetalle() + " + Celular";
	}

	@Override
	public double getCosto() {
		return super.getCosto() + 300;
	}
	
}
