package Util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

/**
 * Clase que reptresenta el emulador de paso del tiempo.
 * 
 * @author G10
 *
 */
public class EPT extends Observable implements Serializable {
	private static EPT instance = null;
	private GregorianCalendar fecha = new GregorianCalendar(2020, 00, 01);
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");

	private EPT() {

	}
	
	/* ----------------
	 * PATRON SINGLETON
	 * ----------------
	 */
	public static EPT getInstance() {
		if (instance == null)
			instance = new EPT();
		return instance;
	}
	
	public GregorianCalendar getFecha() {
		return fecha;
	}

	public String printFecha() {
		return formato.format(fecha.getTime());
	}

	/**
	 * Metodo que settea la instancia ya que esta es perdida en la persistencia por ser estatica.
	 * 
	 * @param ept Es el mismo EPT.
	 */
	public static void setInstance(EPT ept) {
		instance = ept;
	}

	/**
	 * Metodo que adelanta la fecha en un mes.
	 */
	public void avanzarMes() {
		this.fecha.add(GregorianCalendar.MONTH, 1);
		this.setChanged();
		this.notifyObservers(this.fecha);
	}
}
