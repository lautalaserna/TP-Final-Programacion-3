package Modelo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import Excepciones.AccionInvalidaException;
import Excepciones.DomicilioRepetidoException;
import State.IState;
import State.Moroso;
import State.SinContratacion;

/**
 * @author Grupo 10
 * 
 *         Esta clase representa los titulares de tipo fisico en la empresa.
 *
 */
public class TitularFisico extends Titular implements IState, Observer {
	private IState estado = null;

	public TitularFisico(String nombre, int dni, String tipoDePago, Observable o) {
		super(nombre, dni, tipoDePago, o);
		this.estado = new SinContratacion(this);
	}

	public void setEstado(IState estado) {
		this.estado = estado;
	}

	@Override
	public String getTipo() {
		return "Fisico";
	}

	/**
	 * Este metodo permite calcular el costo final de la factura de un titular
	 * fisico. <br>
	 * 
	 * <b>pre:</b> El titular existe (no es null). <br>
	 * <b>post:</b> Se retorna el valor del costo con su respectivo
	 * descuento/incremento. <br>
	 */
	@Override
	public double getCostoFinal() {
		double respuesta = this.getCostoBase();

		if (this.formaDePago.equals("Efectivo"))
			respuesta -= respuesta * 0.20;
		else if (this.formaDePago.equals("Cheque"))
			respuesta += respuesta * 0.10;

		return respuesta;
	}

	/**
	 * Metodo que agrega una contratacion. Delega al estado la tarea de hacer una
	 * contratacion.
	 */
	@Override
	public void addContratacion(IContratacion c) throws DomicilioRepetidoException, AccionInvalidaException {
		this.estado.addContratacion(c);
	}

	/**
	 * Metodo que da de baja una contratacion. Delega al estado la tarea de dar de
	 * baja.
	 */
	@Override
	public void darDeBaja(int index) throws AccionInvalidaException {

		this.estado.darDeBaja(index);
	}

	/**
	 * Metodo que paga una factura. Delega al estado la tarea pagar la factura.
	 *
	 * @param index es el identificador de la factura a pagar, siempre es correcto.
	 */
	@Override
	public void pagarFactura(int index) throws AccionInvalidaException {

		this.estado.pagarFactura(index);
	}

	/**
	 * Metodo que se ejecuta al ser notificado por el EPT, genera la factura cuando
	 * se avanza de mes.
	 */
	@Override
	public void update(Observable o, Object fecha) {
		if (this.observado != o)
			throw new IllegalArgumentException();
		else {
			GregorianCalendar f = (GregorianCalendar) fecha;
			generarFactura(f);
			if(!this.estado.toString().equals("Moroso"))
				compruebaEstado();
		}
	}

	/**
	 * Metodo que comprueba si el estado de un titular cambia a moroso
	 */
	private void compruebaEstado() {
		if (this.facturasPendientes() >= 2) {
			this.estado = new Moroso(this);
			this.actualizaCostos();
		}

	}

	/**
	 * Metodo que actualiza el valor del costo final cuando el estado es moroso
	 */
	public void actualizaCostos() {
		Iterator<Factura> itFacturas = this.getFacturas().iterator();
		while (itFacturas.hasNext()) {
			Factura factura = itFacturas.next();
			if (!factura.isPaga()) {
				factura.setCostoFinal(factura.getCostoFinal() * 1.3);
				factura.setRecargo("RECARGO DE 30% POR ESTADO MOROSO");
			}
		}
	}

	@Override
	public String getEstado() {
		return estado.toString();
	}
	
	
}
