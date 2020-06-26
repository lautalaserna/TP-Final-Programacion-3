package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import Excepciones.TitularDuplicadoException;
import Util.EPT;

/**
 * @author Grupo 10
 * 
 * Clase que representa la Empresa que contiene todos los titulares con sus contrataciones y facturas.
 */
public class Empresa extends Observable implements Serializable{
	public static Empresa instance = null;
	private ArrayList<Titular> titulares = new ArrayList<Titular>();
	private transient boolean bloqueado = false;

	private Empresa() {

	}
	
	/* ----------------
	 * PATRON SINGLETON
	 * ----------------
	 */
	
	public static Empresa getInstance() {
		if (instance == null)
			instance = new Empresa();
		return instance;
	}
	
	/**
	 * Este metodo vuelve a settear la instancia de la empresa, ya que es un atributo estatico y no se persiste.
	 * @param e es la Empresa misma
	 */
	public static void setInstance(Empresa e) {
		instance = e;
	}
	
	public ArrayList<Titular> getTitulares(){
		return this.titulares;
	}
	
	public boolean isBloqueado() {
		return bloqueado;
	}
	
	public void setBloqueado(boolean bloqueoAFIP) {
		this.bloqueado = bloqueoAFIP;
	}

	/**
	 * Este metodo permite agregar un titular a la empresa. <br>
	 * 
	 * <b>pre:</b> La empresa existe (no es null). <br>
	 * <b>post:</b> Se añade un nuevo titular al ArrayList. <br>
	 * 
	 * @param titular Es el titular que será agregado, este no debe ser null
	 * @throws TitularDuplicadoException lanza una excepcion cuando el DNI del titular ingresado ya existe en el sistema
	 */
	public void addTitular(Titular titular) throws TitularDuplicadoException{
		if(!existeTitular(titular))
			this.titulares.add(titular);
		else
			throw new TitularDuplicadoException(titular);
		this.liberaBloqueo();
	}
	
	/**
	 * Busca si el titular existe en el sistema.
	 * 
	 * @param titular Es el titular a buscar.
	 * @return true si esta, false si no esta.
	 */
	private boolean existeTitular(Titular titular) {
		boolean rta = false;
		Iterator<Titular> it = titulares.iterator();
		while(it.hasNext() && !rta) {
			Titular t = it.next();
			if(titular.equals(t)) {
				rta = true;
			}
		}
		return rta;
	}

	/**
	 * Este metodo adquiere el bloqueo de la Empresa para simular la concurrencia. Es llamado al intentar agregar un Titular 
	 */
	public synchronized void adquiereBloqueo(){
		while(bloqueado) {
			try {
				setChanged();
				notifyObservers("Reporte de AFIP en proceso");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.setBloqueado(true);
	}
	
	
	/**
	 * Este metodo adquiere el bloqueo de la Empresa para simular la coconcurrencia. Es llamado cada 1 min por la AFIP.
	 */
	public synchronized void visitaAFIP() {
		while(bloqueado) {
			try {
				setChanged();
				notifyObservers("AFIP esta esperando");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.setBloqueado(true);
		AFIP.getInstance().setEstaVisitando(true);
	}

	/**
	 * Este metodo se encarga de liberar el bloqueo de la concurrencia, ya sea por el Titular o por la AFIP.
	 */
	public synchronized void liberaBloqueo() {
		this.setBloqueado(false);
		AFIP.getInstance().setEstaVisitando(false);
		notifyAll();
	}
	
	/**
	 * Este metodo se encarga de volver a settear los Observers del EPT, es decir, todos los titulares vuelven a observar al EPT.
	 * Es necesario ya que al leer de la persistencia se perdian las referencias y no eran notificados correctamente los observers.
	 */
	public void setObservers() {
		Iterator<Titular> it = this.titulares.iterator();
		while(it.hasNext()) {
			Titular titular = it.next();
			titular.recordarObserver(EPT.getInstance());
		}
	}

	/**
	 * Se encarga de eliminar un titular de la empresa
	 * 
	 * <b>pre:</b> La empresa existe (no es null), al igual que el titular <br>
	 * <b>post:</b> Se elimina el titular pasado por el index del ArrayList. <br>
	 * 
	 * @param index La posicion del titular en el arrayList (mayor que 0 y menor que la long. del ArrayList)
	 */
	public void eliminarTitular(int index) {
		this.getTitulares().remove(index);
	}
	
	
	
	
	
	
	
	
}
