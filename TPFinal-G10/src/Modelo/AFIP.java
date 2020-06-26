package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import Util.Util;

/**
 * La clase AFIP es responsable de generar clones de facturas cada cierto tiempo dentro de la Empresa
 * 
 * @author G10
 *
 */

public class AFIP extends Observable implements Runnable {
	private ArrayList<Factura> facturasAFIP = new ArrayList<Factura>();
	private String reporte = null;
	private static AFIP instance;
	private boolean estaVisitando = false;

	private AFIP() {
	}
	
	/* ----------------
	 * PATRON SINGLETON
	 * ----------------
	 */
	public static AFIP getInstance() {
		AFIP respuesta;
		if (instance == null)
			instance = new AFIP();
		return instance;
	}

	public ArrayList<Factura> getFacturasAFIP() {
		return facturasAFIP;
	}

	public String getReporte() {
		return this.reporte;
	}

	/**
	 * El metodo run se encarga de adquirir el recurso compartido de la empresa y luego obtener los clones de las facturas de la Empresa
	 * Tiene un tiempo de espera de 1 min, es decir que cada 1 min va a aparecer la AFIP y va a solicitar el recurso compartido para 
	 * poder hacer la clonacion de todas las facturas. Al ser el metodo run() de un Thread, es ejecutado cuando se llama al metodo start();
	 */
	@Override
	public void run() {
		while (true) {
			Util.espera(60000);

			Empresa e = Empresa.getInstance();

			if (!estaVisitando) {
				e.visitaAFIP();
				this.reporte = "";
				Iterator<Titular> itTitulares = e.getTitulares().iterator();
				while (itTitulares.hasNext()) {
					Titular titular = itTitulares.next();
					Iterator<Factura> itFacturas = titular.getFacturas().iterator();
					while (itFacturas.hasNext()) {
						Factura factura = itFacturas.next();
						try {
							Factura clon = (Factura) factura.clone();
							this.facturasAFIP.add(clon);
							this.reporte += clon.getNombre() + ": " + clon.toString() + "\n";
						} catch (CloneNotSupportedException e1) {
							//el catch no realiza acciones, se llega si se intenta clonar una factura de titular juridico
						}
					}
				}

				setChanged();
				notifyObservers(this.reporte);
			}
		}
	}

	/**
	 * Este metodo verifica si el recurso ya fue adquirido por la misma AFIP, asi no vuelve a llamar y se superponen las pantallas de 
	 * AFIP si es que una no se cierra antes que aparezca la otra
	 * @param b true si ya esta tomado el recurso por la AFIP, false si no lo está.
	 */
	public void setEstaVisitando(boolean b) {
		this.estaVisitando = b;
	}

}
