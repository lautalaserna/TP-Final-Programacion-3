package Decorator;

import java.io.Serializable;

import Modelo.IContratacion;

public class DecoratorTv extends DecoratorContratacion{

	public DecoratorTv(IContratacion encapsulado) {
		super(encapsulado);
	}

	@Override
	public String getDetalle() {
		return super.getDetalle() + " + TV-Cable";
	}

	@Override
	public double getCosto() {
		return super.getCosto() + 250;
	}
	
	
}
