package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import Excepciones.AccionInvalidaException;
import Excepciones.DomicilioRepetidoException;
import Factory.ContratacionFactory;
import Util.EPT;

/**
 * @author Grupo 10
 * 
 *Esta clase representa los titulares de la Empresa, los cuales pueden ser de tipo Fisico o Juridico.
 *
 */
public abstract class Titular implements Observer, Serializable {
	protected int nroIdentacion;
	protected String nombre;
	protected int dni;
	protected String formaDePago;
	protected ArrayList<IContratacion> contrataciones = new ArrayList<IContratacion>();
	protected ArrayList<Factura> facturas = new ArrayList<Factura>();
	protected Observable observado;

	public Titular(String nombre, int dni, String formaDePago, Observable o) {
		this.nombre = nombre;
		this.dni = dni;
		this.formaDePago = formaDePago;
		this.nroIdentacion = Empresa.getInstance().getTitulares().size();
		this.observado = o;
		this.observado.addObserver(this);
	}
	
	public abstract String getEstado();
	
	public ArrayList<Factura> getFacturas() {

		return facturas;
	}

	public int getNroIdentacion() {
		return nroIdentacion;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDni() {
		return dni;
	}

	public String getFormaDePago() {
		return formaDePago;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Titular other = (Titular) obj;
		if (dni != other.dni)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dni;
		return result;
	}

	/**
	 * Este metodo cambia el tipo de pago del titular.<br>
	 * 
	 * <b>pre:</b> El titular existe (no es null). <br>
	 * <b>post:</b> Se acambia el tipo de pago. <br>
	 * 
	 * @param formaDePago Es el tipo de pago nuevo, puede ser "Efectico", "Cheque" o "Tarjeta"
	 */
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	public ArrayList<IContratacion> getContrataciones() {
		return contrataciones;
	}
	
	/**
	 * Metodo llamado por la empresa para volver a settear el observado.
	 * 
	 * @param o es el EPT el cual se debe volver a settear para no perder la referencia en la persistencia.
	 */
	public void recordarObserver(Observable o) {
		this.observado = o;
		this.observado.addObserver(this);
	}
	
	/**
	 * Este metodo permite calcular el costo base total que debera pagar un Titular. <br>
	 * 
	 * <b>pre:</b> El titular existe (no es null). <br>
	 * <b>post:</b> Se retorna el valor total de la suma de cada factura de contratacion. <br>
	 * 
	 * @return Retorna el costo base de la factura de un Titular
	 */
	public double getCostoBase() {
		double respuesta = 0;
		Iterator<IContratacion> it = this.contrataciones.iterator();
		while (it.hasNext()) {
			IContratacion contratacion = it.next();
			respuesta += contratacion.getCosto();
		}

		return respuesta;
	}

	public abstract String getTipo();

	public abstract double getCostoFinal();

	public void generarFactura(GregorianCalendar fecha) {
		if( !this.contrataciones.isEmpty() ) {
			Factura factura = new Factura(this, fecha);
			this.facturas.add(factura);
		}
	}

	@Override
	public String toString() {

		return nroIdentacion + ") " + nombre + " - " + dni + " (" + this.getTipo() + ")";
	}

	public void darDeBaja(int index) throws AccionInvalidaException {

		this.contrataciones.remove(index);
	}

	public void pagarFactura(int index) throws AccionInvalidaException {

		this.facturas.get(index).pagar();
	}

	/**
	 *Este metodo agrega una IContratacion al ArrayList de IContrataciones.
	 *
	 *@param c Es la contratacion que agregara al ArrayList (distinta de null)
	 */
	public void addContratacion(IContratacion c) throws DomicilioRepetidoException, AccionInvalidaException {
		this.contrataciones.add(c);
	}

	/**
	 * Metodo que recorre las facturas de un titular y cuenta la cantidad que faltan pagar.
	 * 
	 * @return es la cantidad de facturas pendientes.
	 */
	public int facturasPendientes() {
		int respuesta = 0;
		Iterator<Factura> itfacturas = this.getFacturas().iterator();
		while (itfacturas.hasNext()) {
			Factura factura = itfacturas.next();
			if (!factura.isPaga())
				respuesta++;
		}

		return respuesta;
	}
	
}
