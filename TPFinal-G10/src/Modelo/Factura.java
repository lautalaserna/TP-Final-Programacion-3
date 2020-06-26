package Modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Esta clase representa una factura de un titular, poseen informacion del
 * titular a la que pertenece, y ademas pueden estar "paga" o "no paga".
 * 
 * @author G10
 *
 */
public class Factura implements Serializable, Cloneable {
	private String fechaActual;
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
	private int id;
	private String nombre;
	private int dni;
	private String tipo;
	private String formaDePago;
	private ArrayList<String> detalles = new ArrayList<String>();
	private double costoBase;
	private double costoFinal;
	private String recargo = null;
	private boolean isPaga = false;

	public Factura(Titular titular, GregorianCalendar fecha) {
		this.fechaActual = formato.format(fecha.getTime());
		this.id = titular.getNroIdentacion();
		this.nombre = titular.getNombre();
		this.dni = titular.getDni();
		this.tipo = titular.getTipo();
		this.formaDePago = titular.getFormaDePago();

		Iterator<IContratacion> it = titular.getContrataciones().iterator();
		while (it.hasNext()) {
			IContratacion contratacion = it.next();
			detalles.add(
					contratacion.getDomicilio() + ": " + contratacion.getDetalle() + " $" + contratacion.getCosto());
		}
		this.costoBase = titular.getCostoBase();
		this.costoFinal = titular.getCostoFinal();
		if (titular.getEstado().toString().equals("Moroso")) {
			this.costoFinal *= 1.3;
			this.recargo="RECARGO DE 30% POR ESTADO MOROSO";
		}
	}

	public double getCostoFinal() {
		return this.costoFinal;
	}

	public void setCostoFinal(double costoFinal) {
		this.costoFinal = costoFinal;
	}

	/**
	 * Este metodo se encarga de mostrar en la factura si esta tiene un recargo del
	 * 30% por estado moroso
	 * 
	 * @param recargo String que indica si la factura a pagar tiene un recargo del
	 *                30%
	 */
	public void setRecargo(String recargo) {
		this.recargo = recargo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getDNI() {
		return this.dni;
	}

	public boolean isPaga() {
		return isPaga;
	}

	public void pagar() {
		this.isPaga = true;
	}

	/**
	 * Este metodo arma el detalle de la factura segun el estado del titular y las
	 * contrataciones que tenga
	 * 
	 * @return String con el detalle de la factura
	 */
	public String detalleFactura() {
		String respuesta = null;

		respuesta = "Fecha: " + fechaActual + "\n\n";

		respuesta += "Cliente N°:" + id + "\n";

		respuesta += "Titular: " + nombre + "\n";

		respuesta += "DNI: " + dni + "\n";

		respuesta += "Tipo: " + tipo + "\n";

		respuesta += "Forma de pago: " + formaDePago + "\n\n";

		respuesta += "Contrataciones: \n\n";

		Iterator<String> it = detalles.iterator();
		String detalle = null;
		while (it.hasNext()) {
			detalle = it.next();
			respuesta += "     • " + detalle + "\n\n";
		}
		respuesta += "\n\n";

		respuesta += "COSTO BASE: $" + redondeo(costoBase) + "\n\n";

		if (recargo != null)
			respuesta += this.recargo + "\n\n";

		respuesta += "COSTO FINAL: $" + redondeo(costoFinal) + "\n";

		return respuesta;
	}
	
	/**
	 * Metodo para darle formato al Costo.
	 * 
	 * @param n Un costo de tipo Double.
	 * @return El costo con formato x,xx.
	 */
	private double redondeo(Double n) {
		DecimalFormat formato = new DecimalFormat("#.##");
		return Double.valueOf(formato.format(n));
	}

	@Override
	public String toString() {
		String respuesta = fechaActual;

		if (isPaga)
			respuesta += " - PAGA";
		else
			respuesta += " - FALTA PAGAR";

		return respuesta;
	}

	/**
	 * Metodo que devuelve el clon de una factura.
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		if(this.tipo.equals("Fisico")) {
			Factura clon = null;

			clon = (Factura) super.clone();
			clon.detalles = (ArrayList<String>) this.detalles.clone();

			return clon;
		}else {
			throw new CloneNotSupportedException("Imposible clonar la factura de un titular juridico");
		}
	}

}
