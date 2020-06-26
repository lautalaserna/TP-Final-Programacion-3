package Modelo;

import java.util.GregorianCalendar;
import java.util.Observable;

/**
 * @author Grupo 10
 * 
 * Esta clase representa los titulares de tipo juridico en la empresa.
 *
 */
public class TitularJuridico extends Titular {

	public TitularJuridico(String nombre, int dni, String tipoDePago, Observable o) {
		super(nombre, dni, tipoDePago,o);
	}

	@Override
	public String getTipo() {
		return "Juridico";
	}
	
	/**
	 * Este metodo permite calcular el costo final de la factura de un titular juridico. <br>
	 * 
	 * <b>pre:</b> El titular existe (no es null). <br>
	 * <b>post:</b> Se retorna el valor del costo con su respectivo descuento/incremento. <br>
	 */
	@Override
	public double getCostoFinal() {
		double respuesta = this.getCostoBase();

		if (this.formaDePago.equals("Efectivo"))
			respuesta -= respuesta * 0.10;
		else if (this.formaDePago.equals("Cheque"))
			respuesta += respuesta * 0.15;
		else if (this.formaDePago.equals("Tarjeta"))
			respuesta += respuesta * 0.20;

		return respuesta;
	}

	/**
	 *Metodo que se ejecuta al ser notificado por el EPT, genera la factura cuando se avanza de mes.
	 */
	@Override
	public void update(Observable o, Object fecha) {
		if (this.observado != o)
			throw new IllegalArgumentException();
		else {
			GregorianCalendar f = (GregorianCalendar) fecha;
			generarFactura(f);
		}
	}

	@Override
	public String getEstado() {
		
		return "...";
	}

}
