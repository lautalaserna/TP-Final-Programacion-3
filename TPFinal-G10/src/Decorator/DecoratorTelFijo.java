package Decorator;

import java.io.Serializable;

import Modelo.IContratacion;

public class DecoratorTelFijo extends DecoratorContratacion{

	public DecoratorTelFijo(IContratacion encapsulado) {
		super(encapsulado);
	}

	@Override
	public String getDetalle() {
		return super.getDetalle() + " + Telefono Fijo";
	}

	@Override
	public double getCosto() {
		return super.getCosto() + 200;
	}
	
}
